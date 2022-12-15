package com.android.concept

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.android.concept.databinding.FragmentFirstBinding
import com.android.concept.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var syncDataWithActivity: SyncDataWithActivity
    private lateinit var binding: FragmentHomeBinding

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

        binding = FragmentHomeBinding.inflate(inflater, container, false)


        setupClickListners()
        return binding.root
    }

    private fun setupClickListners() {
        binding.buttonFirst.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_firstFragment)
        })
        binding.buttonSecond.setOnClickListener(View.OnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSecondFragment("Naman")
            it.findNavController().navigate(action)
        })
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