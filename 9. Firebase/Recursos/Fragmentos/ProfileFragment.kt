package com.example.icesiapp231.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.icesiapp231.databinding.FragmentProfileBinding

class ProfileFragment:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentProfileBinding.inflate(inflater, container, false)
        return view.root
    }

    companion object{
        fun newInstance() = ProfileFragment()
    }

}