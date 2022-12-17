package com.android.concept


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import androidx.fragment.app.FragmentOnAttachListener
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.utils.Utils
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,SyncDataWithActivity {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    var fragmentManager = supportFragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"


        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener(this)
        fragmentManager.addFragmentOnAttachListener(
            FragmentOnAttachListener{fragmentManager, fragment ->

                if(fragment == fragmentManager.findFragmentByTag("home")){
                    println("Home fragment is Attached")
                }
            }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }else when(item.itemId){
            R.id.item_home -> {
                Utils.showToast("Home Clicked")
                return true
            }
            R.id.item_setting -> {
                Utils.showToast("Setting Clicked")
                return true
            }
            R.id.item_logout -> {
                Utils.showToast("Logout Clicked")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun setupButtonListeners() {
//        binding.buttonHome.setOnClickListener(this)
//        binding.buttonFirst.setOnClickListener(this)
//        binding.buttonSecond.setOnClickListener(this)

    }

//    private fun addFragment(fragment: Fragment) {
//        fragmentTransaction = fragmentManager.beginTransaction()
//
//        if(fragment is HomeFragment){
//            fragmentTransaction.add(R.id.frameLayout,fragment,"home")
//            fragmentTransaction.addToBackStack("back_stack")
//            fragment.initializeInterfaceObject(this)
//        }else{
//            fragmentTransaction.add(R.id.frameLayout,fragment)
//            fragmentTransaction.addToBackStack("back_stack")
//        }
//
//        fragmentTransaction.commit()
//        println("Activity : addFragment Demo Fragment")
//
//    }
//
//    private fun replaceFragment(fragment: Fragment) {
//        fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout,fragment)
//        fragmentTransaction.commit()
//        println("Activity : replace Fragment")
//
//    }
//
//    private fun removeFragment(fragment: Fragment) {
//        fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.remove(fragment)
//        fragmentTransaction.commit()
//        println("Activity : replace Fragment")
//
//    }

    override fun onStart() {
        super.onStart()
        println("Activity : onStart")

    }

    override fun onResume() {
        super.onResume()
        println("Activity : onResume")

    }

    override fun onPause() {
        super.onPause()
        println("Activity : onPause")

    }

    override fun onStop() {
        super.onStop()
        println("Activity : onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("Activity : onDestroy")

    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

        //fragmentManager.popBackStack("back_stack", POP_BACK_STACK_INCLUSIVE)
    }

    override fun onClick(v: View?) {
//        if (v != null) {
//            when(v.id){
//                R.id.button_home -> addFragment(HomeFragment())
//                R.id.button_first -> addFragment(FirstFragment())
//                R.id.button_second -> addFragment(SecondFragment())
//            }
//        }
    }

    override fun demoFunction() {
        println("Activity : Demo Function Called")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_home ->{
                Utils.showToast("Home Clicked")
                Navigation.findNavController(this,R.id.fragmentContainerView)
                    .navigate(R.id.action_global_homeFragment)
            }
            R.id.item_setting ->{
                Utils.showToast("Setting Clicked")
                Navigation.findNavController(this,R.id.fragmentContainerView)
                    .navigate(R.id.action_global_settingFragment)
            }
            R.id.item_logout ->{
                Utils.showToast("Logout Clicked")
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}