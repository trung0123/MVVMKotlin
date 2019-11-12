package com.example.askbekotlin.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.askbekotlin.R
import com.example.askbekotlin.databinding.ActivityMainBinding
import com.example.askbekotlin.ui.base.BaseVMActivity
import com.example.askbekotlin.utils.KeyboardUtils
import kotlinx.android.synthetic.main.abs_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_navigation.*

class MainActivity : BaseVMActivity<MainViewModel>() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()

        changeBottomColor(1)
        eventAddBackStackFragment()
    }

    private fun setupNavigation() {
        setSupportActionBar(binding.mainToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        navController = Navigation.findNavController(this, R.id.main_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            @Suppress("DEPRECATION")
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                binding.drawerLayout.setScrimColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.transparent
                    )
                )
                binding.content.translationX = drawerView.width * slideOffset
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }


    private fun eventAddBackStackFragment() {
        supportFragmentManager.addOnBackStackChangedListener {
            KeyboardUtils.hideKeyboard(this)
            if (supportFragmentManager.backStackEntryCount > 0) {
                img_toolbar_open_drawer.visibility = View.VISIBLE
//                imgHam.setOnClickListener { drawer.openDrawer(GravityCompat.START) }

                // show back button
//                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                // Animation back button
//                ObjectAnimator.ofFloat(
//                    actionBarDrawerToggle.getDrawerArrowDrawable(),
//                    "progress",
//                    1
//                ).start()
//                actionBarDrawerToggle.syncState()
//
//                toolbar.setNavigationOnClickListener({ v ->
//                    if (isClickedQuick) return@toolbar.setNavigationOnClickListener
//                    onBackPressed()
//                })
//
                changeBottomColor()

            } else {
//                //show hamburger
//                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                img_toolbar_open_drawer.visibility = View.GONE
                // Animation hamburger button
//                ObjectAnimator.ofFloat(
//                    actionBarDrawerToggle.getDrawerArrowDrawable(),
//                    "progress",
//                    0
//                ).start()
//                actionBarDrawerToggle.syncState()
//
//                toolbar.setNavigationOnClickListener({ v -> drawer.openDrawer(GravityCompat.START) })
            }
        }
    }

//    fun addFragmentLessonDetail(id: String, @SLIDE type: Int) {
//        getTransaction(type)
//            .addToBackStack(LessonDetailFragment.TAG)
//            .replace(
//                R.id.frame_content, LessonDetailFragment.newInstance(id),
//                LessonDetailFragment.TAG
//            )
//            .commitAllowingStateLoss()
//    }

    private fun changeBottomColor() {
        img_main_home.setBackgroundResource(R.drawable.home)
        tv_main_home.setTextColor(ContextCompat.getColor(this, R.color.black))

        img_main_lesson_creation.setBackgroundResource(R.drawable.lesson_creation)
        tv_main_lesson_creation.setTextColor(ContextCompat.getColor(this, R.color.black))

        img_main_my_schedule.setBackgroundResource(R.drawable.my_schedule)
        tv_main_my_schedule.setTextColor(ContextCompat.getColor(this, R.color.black))

        img_main_reservation_pay.setBackgroundResource(R.drawable.reservation_pay)
        tv_main_reservation_pay.setTextColor(ContextCompat.getColor(this, R.color.black))

        img_main_search.setBackgroundResource(R.drawable.search)
        tv_main_search.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    private fun changeBottomColor(pos: Int) {
        changeBottomColor()
        when (pos) {
            1 -> {
                img_main_home.setBackgroundResource(R.drawable.home_cover)
                tv_main_home.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
            2 -> {
                img_main_lesson_creation.setBackgroundResource(R.drawable.lesson_creation_cover)
                tv_main_lesson_creation.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )
            }
            3 -> {
                img_main_my_schedule.setBackgroundResource(R.drawable.my_schedule_cover)
                tv_main_my_schedule.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
            4 -> {
                img_main_reservation_pay.setBackgroundResource(R.drawable.reservation_pay_cover)
                tv_main_reservation_pay.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )
            }
            5 -> {
                img_main_search.setBackgroundResource(R.drawable.search_cover)
                tv_main_search.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
        }
    }
}
