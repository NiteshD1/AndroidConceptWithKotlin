package com.android.concept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.FragmentOnAttachListener
import androidx.fragment.app.FragmentTransaction
import com.android.concept.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener,SyncDataWithActivity {

    private lateinit var binding: ActivityMainBinding
    var fragmentManager = supportFragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        fragmentManager.addFragmentOnAttachListener(
            FragmentOnAttachListener{fragmentManager, fragment ->

                if(fragment == fragmentManager.findFragmentByTag("home")){
                    println("Home fragment is Attached")
                }
            }
        )
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
//        binding.buttonHome.setOnClickListener(this)
//        binding.buttonFirst.setOnClickListener(this)
//        binding.buttonSecond.setOnClickListener(this)

    }

    private fun addFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()

        if(fragment is HomeFragment){
            fragmentTransaction.add(R.id.frameLayout,fragment,"home")
            fragmentTransaction.addToBackStack("back_stack")
            fragment.initializeInterfaceObject(this)
        }else{
            fragmentTransaction.add(R.id.frameLayout,fragment)
            fragmentTransaction.addToBackStack("back_stack")
        }

        fragmentTransaction.commit()
        println("Activity : addFragment Demo Fragment")

    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        println("Activity : replace Fragment")

    }

    private fun removeFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
        println("Activity : replace Fragment")

    }

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
        super.onBackPressed()
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
}