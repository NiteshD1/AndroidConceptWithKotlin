package com.android.concept

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.android.concept.databinding.LayoutCustomDialogBinding
import com.android.concept.utils.Utils

class CustomDialog : DialogFragment() {

    private lateinit var binding : LayoutCustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{

        const val TITLE_KEY = "title_key"
        const val DESCRIPTION_KEY = "description_key"

        fun newInstance(title:String,description:String) : CustomDialog{
            val fragment = CustomDialog()

            val args = Bundle()
            args.putString(TITLE_KEY,title)
            args.putString(DESCRIPTION_KEY,description)

            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutCustomDialogBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        binding.textViewTitle.text = arguments?.getString(TITLE_KEY) ?: "Title"
        binding.textViewDescription.text = arguments?.getString(DESCRIPTION_KEY) ?: "Description..."

        setClickListners()
    }

    private fun setClickListners() {

        binding.buttonProceed.setOnClickListener(View.OnClickListener {
            Utils.showToast("Starting Your Payment Transaction...")
            dismiss()
        })

        binding.buttonCancel.setOnClickListener(View.OnClickListener {
            Utils.showToast("Your Payment Transaction Failed...")
            dismiss()
        })
    }
}