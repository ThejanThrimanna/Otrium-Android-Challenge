package com.thejan.otrium_android.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejan.otrium_android.R
import com.thejan.otrium_android.config.ProfileView
import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable
import com.thejan.otrium_android.databinding.ActivityMainBinding
import com.thejan.otrium_android.presenter.ProfileActivityPresenter
import com.thejan.otrium_android.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ProfileActivity : BaseActivity(), ProfileView {

    var binding: ActivityMainBinding? = null
    lateinit var mainBinding: ActivityMainBinding
    private lateinit var pinedAdapter: RepositoryAdapter
    private lateinit var topRepoAdapter: RepositoryAdapter
    private lateinit var starredAdapter: RepositoryAdapter

    private var pinedList: MutableList<RepositoryTable> = mutableListOf()
    private var topRepoList: MutableList<RepositoryTable> = mutableListOf()
    private var starredList: MutableList<RepositoryTable> = mutableListOf()

    @Inject
    lateinit var profilePresenter: ProfileActivityPresenter

    override fun performInjection() {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        setUpLayout()
    }

    private fun setUpLayout() {
        profilePresenter.attachView(this, mainBinding)
        profilePresenter.getProfile()

        pinedAdapter = RepositoryAdapter(pinedList)
        rvPined.adapter = pinedAdapter
        rvPined.layoutManager = LinearLayoutManager(this)

        topRepoAdapter = RepositoryAdapter(topRepoList)
        rvTop.adapter = topRepoAdapter
        rvTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        starredAdapter = RepositoryAdapter(starredList)
        rvStarred.adapter = starredAdapter
        rvStarred.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.detachView()
    }

    override fun refreshView(
        user: List<UserTable>?,
        pinedList: List<RepositoryTable>,
        topRepoList: List<RepositoryTable>,
        starredRepoList: List<RepositoryTable>
    ) {
        this.runOnUiThread(Runnable {

            if(user!!.isNotEmpty()){
                profilePresenter.setImageUrl(ivProfile, user[0].image)
            }

            this.pinedList.clear()
            this.pinedList.addAll(pinedList)
            this.pinedAdapter.notifyDataSetChanged()

            this.topRepoList.clear()
            this.topRepoList.addAll(topRepoList)
            this.topRepoAdapter.notifyDataSetChanged()

            this.starredList.clear()
            this.starredList.addAll(starredRepoList)
            this.starredAdapter.notifyDataSetChanged()
        })
    }

}