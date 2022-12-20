package com.android.concept


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"


        setupCustomDialogFragment()
    }

    private fun setupCustomDialogFragment() {
        binding.buttonEnrollNow.setOnClickListener(View.OnClickListener {

            CustomDialog.newInstance(getString(R.string.dialog_heading),getString(R.string.dialog_description))
                .show(supportFragmentManager,"CustomDialogFragment")

        })
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

    }



}