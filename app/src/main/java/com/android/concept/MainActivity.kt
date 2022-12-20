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
    var fragmentManager = supportFragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        setupBottomSheetModal()
        setupBottomSheetPersistent()
    }

    private fun setupBottomSheetPersistent() {

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bootomSheetPersistent.bottomSheet)
        binding.buttonBottomSheetPersistent.setOnClickListener(View.OnClickListener {
            if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }else{
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        })

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> Utils.showToast("Collapsed")
                    BottomSheetBehavior.STATE_EXPANDED -> Utils.showToast("Expanded")
                    BottomSheetBehavior.STATE_DRAGGING -> Utils.showToast("Dragging")
                    else -> Utils.showToast("None")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })

    }

    private fun setupBottomSheetModal() {
        binding.buttonBottomSheetModal.setOnClickListener(View.OnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet_modal,null)

            val button : Button = view.findViewById(R.id.buttonDismiss)

            button.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setCancelable(false)

            bottomSheetDialog.setContentView(view)

            bottomSheetDialog.show()

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