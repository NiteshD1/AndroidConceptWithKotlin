package com.android.concept

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.concept.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

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

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.buttonSetting.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.action_global_settingFragment)
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("Fragment : onActivityCreated")

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