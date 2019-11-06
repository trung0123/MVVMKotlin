package com.example.askbekotlin.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.askbekotlin.R
import com.example.askbekotlin.databinding.ActivityMainBinding
import com.example.askbekotlin.ui.base.BaseVMActivity
import com.example.askbekotlin.utils.KeyboardUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseVMActivity<MainViewModel>() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java

    private lateinit var imgHam: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()

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
                imgHam.visibility = View.VISIBLE
//                imgHam.setOnClickListener { drawer.openDrawer(GravityCompat.START) }

                // show back button
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
//                changeBottomColor()

            } else {
                //show hamburger
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                imgHam.visibility = View.GONE
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
}
