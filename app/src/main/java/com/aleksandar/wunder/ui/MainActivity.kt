package com.aleksandar.wunder.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aleksandar.wunder.App
import com.aleksandar.wunder.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent!!.inject(this)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        initViewPager()
    }

    /**
     * Initializing the ViewPager
     */
    private fun initViewPager() {
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        main_view_pager.adapter = mainPagerAdapter
        main_tab_layout.setupWithViewPager(main_view_pager)
    }
}
