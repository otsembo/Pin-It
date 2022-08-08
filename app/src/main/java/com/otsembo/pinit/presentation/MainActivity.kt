package com.otsembo.pinit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.otsembo.pinit.R
import com.otsembo.pinit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomNav()
    }

    private fun initBottomNav() {
        val hostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val controller = hostFragment.navController
        binding.bottomNavNotes.setupWithNavController(controller)
    }
}
