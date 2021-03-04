package com.thejan.otrium_android.repo

import android.widget.Toast
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.thejan.otrium_android.GitHubApplication
import com.thejan.otrium_android.UserQuery
import com.thejan.otrium_android.config.Events
import com.thejan.otrium_android.config.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileRepository @Inject internal constructor(
    val bus: RxBus,
    private val apolloClient: ApolloClient
) {

    fun getProfile() {
        Rx2Apollo.from(apolloClient.query(UserQuery()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ handleSuccessfulResponse(it) },
                { handleErrorResponse(it) })
    }

    private fun handleSuccessfulResponse(response: Response<UserQuery.Data>) {
        if (response.hasErrors()) return

        val user = response.data()?.toObject()

        user?.let {
            bus.post(Events.GetSuccessUserEvent(it))
        }
    }

    private fun handleErrorResponse(error: Throwable) {
        error.printStackTrace()
    }

    private fun UserQuery.Data.toObject(): UserQuery.Viewer {
        val source = this
        return source.viewer
    }
}
