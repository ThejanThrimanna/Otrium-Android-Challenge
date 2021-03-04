package com.thejan.otrium_android.presenter

import android.widget.Toast
import com.thejan.otrium_android.GitHubApplication
import com.thejan.otrium_android.config.Events
import com.thejan.otrium_android.config.RxBus
import com.thejan.otrium_android.repo.ProfileRepository
import com.thejan.otrium_android.view.ProfileView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class ProfileActivityPresenter @Inject internal constructor(
    private val profileRepository: ProfileRepository,
    val bus: RxBus
) {

    private var profileView: ProfileView? = null

    fun attachView(view: ProfileView) {
        profileView = view
        bus.register(this)
    }

    fun detachView() {
        profileView = null
        bus.unregister(this)
    }

    fun getProfile() {
        profileRepository.getProfile()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetALLJobsSuccessfulEvent(event: Events.GetSuccessUserEvent) {
        profileView?.refreshView(event.user)
    }
}