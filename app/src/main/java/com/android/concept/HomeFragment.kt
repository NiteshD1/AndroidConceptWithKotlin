package com.android.concept

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {

    private lateinit var syncDataWithActivity: SyncDataWithActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("Fragment : onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Fragment : onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println("Fragment : onCreateView")

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        var button : Button = view.findViewById(R.id.button)

        button.setOnClickListener(View.OnClickListener {
            syncDataWithActivity.demoFunction()
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("Fragment : onActivityCreated")

    }

    fun initializeInterfaceObject(syncDataWithActivity: SyncDataWithActivity){
        this.syncDataWithActivity = syncDataWithActivity
    }

    private fun addFragment(fragment: Fragment) {

        var fragmentManager = childFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        println("Activity : addFragment First Fragment")

    }

    override fun onStart() {
        super.onStart()
        println("Fragment : onStart")

    }

    override fun onResume() {
        super.onResume()
        println("Fragment : onResume")

    }

    override fun onPause() {
        super.onPause()
        println("Fragment : onPause")

    }

    override fun onStop() {
        super.onStop()
        println("Fragment : onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("Fragment : onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("Fragment : onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        println("Fragment : onDetach")

    }

}