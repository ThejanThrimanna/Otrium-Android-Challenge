package com.thejan.otrium_android.presenter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.thejan.otrium_android.GitHubApplication
import com.thejan.otrium_android.R
import com.thejan.otrium_android.config.Events
import com.thejan.otrium_android.config.ProfileView
import com.thejan.otrium_android.config.RxBus
import com.thejan.otrium_android.database.GitHubDatabase
import com.thejan.otrium_android.databinding.ActivityMainBinding
import com.thejan.otrium_android.helper.util.PINED_REPO
import com.thejan.otrium_android.helper.util.STARED_REPO
import com.thejan.otrium_android.helper.util.TOP_REPO
import com.thejan.otrium_android.repo.ProfileRepository
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


class ProfileActivityPresenter @Inject internal constructor(
    private val profileRepository: ProfileRepository,
    val bus: RxBus,
    private val database: GitHubDatabase
) {

    private var profileView: ProfileView? = null
    private var binding: ActivityMainBinding? = null

    fun attachView(
        view: ProfileView,
        binding: ActivityMainBinding?
    ) {
        profileView = view
        this.binding = binding
        bus.register(this)
    }

    fun detachView() {
        profileView = null
        bus.unregister(this)
    }

    fun getProfile() {
        profileRepository.getProfile()
    }

    private fun loadData() {

        Thread {
            val user = database.userDAO()!!.getAll()
            if (!user.isNullOrEmpty()) {
                binding!!.user = user[0]
                binding!!.imageUrl = user[0].image
                binding!!.followers = "${user[0].followers} "+ GitHubApplication.instance.getString(
                    R.string.followers)
                binding!!.following = "${user[0].following} "+ GitHubApplication.instance.getString(
                    R.string.following)
            }

            profileView?.refreshView(user,
                database.reposDAO()!!.getByType(PINED_REPO)!!, database.reposDAO()!!.getByType(
                    TOP_REPO
                )!!, database.reposDAO()!!.getByType(STARED_REPO)!!
            )
        }.start()
    }


    @BindingAdapter("profileImage")
    fun setImageUrl(view: ImageView, imageUrl: String?) {
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }

    @BindingAdapter(value = ["setImageUrl"])
    fun ImageView.bindImageUrl(url: String?) {
        if (url != null && url.isNotBlank()) {

            Picasso.get()
                .load(url)
                .into(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetALLJobsSuccessfulEvent(event: Events.GetSuccessUserEvent) {
        loadData()
    }
}