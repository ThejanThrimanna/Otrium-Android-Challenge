package com.thejan.otrium_android.view.activity

import android.net.PlatformVpnProfile
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thejan.otrium_android.R
import com.thejan.otrium_android.UserQuery
import com.thejan.otrium_android.presenter.ProfileActivityPresenter
import com.thejan.otrium_android.view.ProfileView
import javax.inject.Inject

class ProfileActivity : BaseActivity(), ProfileView {

    @Inject
    lateinit var profilePresenter: ProfileActivityPresenter

    override fun performInjection() {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setUpLayout()
    }

    private fun setUpLayout() {
        profilePresenter.attachView(this)
        profilePresenter.getProfile()
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.detachView()
    }

    override fun refreshView(user: UserQuery.Viewer) {
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }
}