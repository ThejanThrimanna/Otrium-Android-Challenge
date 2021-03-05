package com.thejan.otrium_android.repo

import androidx.room.RoomDatabase
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.thejan.otrium_android.UserQuery
import com.thejan.otrium_android.config.Events
import com.thejan.otrium_android.config.RxBus
import com.thejan.otrium_android.config.SharedPref
import com.thejan.otrium_android.database.GitHubDatabase
import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable
import com.thejan.otrium_android.helper.util.DAY_MILLISECONDS
import com.thejan.otrium_android.helper.util.PINED_REPO
import com.thejan.otrium_android.helper.util.STARED_REPO
import com.thejan.otrium_android.helper.util.TOP_REPO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ProfileRepository @Inject internal constructor(
    private val bus: RxBus,
    private val apolloClient: ApolloClient,
    private val database: GitHubDatabase
) {

    /**
     * When the Last update time greater than 24h, data will be refresh
     */
    fun getProfile() {
        val cal = Calendar.getInstance()
        val lastUpdated = SharedPref.getLong(SharedPref.LAST_UPDATED, 0)
        if (cal.timeInMillis - lastUpdated > DAY_MILLISECONDS) {
            Rx2Apollo.from(apolloClient.query(UserQuery()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ handleSuccessfulResponse(it) },
                    { handleErrorResponse(it) })
        } else {
            bus.post(Events.GetSuccessUserEvent())
        }
    }

    private fun handleSuccessfulResponse(response: Response<UserQuery.Data>) {
        if (response.hasErrors()) return
        response.data()?.toObject()

        val cal = Calendar.getInstance()
        SharedPref.saveLong(SharedPref.LAST_UPDATED, cal.timeInMillis)
    }

    private fun handleErrorResponse(error: Throwable) {
        error.printStackTrace()
    }

    private fun UserQuery.Data.toObject() {
        val source = this
        Thread {
            database.userDAO()!!.deleteAll()
            database.reposDAO()!!.deleteAll()
            val userTable = UserTable()
            userTable.name = source.viewer.name
            userTable.image = source.viewer.avatarUrl as String
            userTable.company = source.viewer.company
            userTable.email = source.viewer.email
            database.userDAO()!!.insert(userTable)


            for (element in source.viewer.starredRepositories.edges!!) {
                val repo = element!!.node
                val calendar = Calendar.getInstance()
                val repositoryTable = RepositoryTable()
                repositoryTable.image = source.viewer.avatarUrl
                repositoryTable.name = repo.name
                repositoryTable.stars = repo.stargazers.totalCount

                var lang = ""
                for (i in repo.languages!!.nodes!!.indices) {
                    lang += if (lang.isEmpty()) {
                        repo.languages!!.nodes!![i]!!.name
                    } else {
                        ", " + repo.languages!!.nodes!![i]!!.name
                    }
                }

                repositoryTable.languages = lang
                repositoryTable.description = repo.description
                repositoryTable.type = STARED_REPO
                repositoryTable.companyName = source.viewer.company
                repositoryTable.id = calendar.timeInMillis

                database.reposDAO()!!.insert(repositoryTable)
            }

            for (element in source.viewer.repositories.edges!!) {
                val repo = element!!.node
                val calendar = Calendar.getInstance()
                val repositoryTable = RepositoryTable()
                repositoryTable.image = source.viewer.avatarUrl
                repositoryTable.name = repo!!.name
                repositoryTable.companyName = source.viewer.company
                repositoryTable.stars = repo.stargazers.totalCount

                var lang = ""
                for (i in repo.languages!!.nodes!!.indices) {
                    lang += if (lang.isEmpty()) {
                        repo.languages!!.nodes!![i]!!.name
                    } else {
                        ", " + repo.languages!!.nodes!![i]!!.name
                    }
                }

                repositoryTable.languages = lang
                repositoryTable.description = repo.description
                repositoryTable.type = TOP_REPO
                repositoryTable.id = calendar.timeInMillis
                database.reposDAO()!!.insert(repositoryTable)
            }

            for (element in source.viewer.pinnedItems.edges!!) {
                val repo = element!!.node!!.asRepository
                val calendar = Calendar.getInstance()
                val repositoryTable = RepositoryTable()
                repositoryTable.image = source.viewer.avatarUrl
                repositoryTable.name = repo!!.name
                repositoryTable.companyName = source.viewer.company
                repositoryTable.stars = repo.stargazers.totalCount

                var lang = ""
                for (i in repo.languages!!.nodes!!.indices) {
                    lang += if (lang.isEmpty()) {
                        repo.languages!!.nodes!![i]!!.name
                    } else {
                        ", " + repo.languages!!.nodes!![i]!!.name
                    }
                }

                repositoryTable.languages = lang
                repositoryTable.description = repo.description
                repositoryTable.type = PINED_REPO
                repositoryTable.id = calendar.timeInMillis
                database.reposDAO()!!.insert(repositoryTable)
            }

            bus.post(Events.GetSuccessUserEvent(source.viewer))
        }.start()
    }
}
