package com.example.phohouseexpress.app_ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phohouseexpress.R
import com.bumptech.glide.Glide
import com.example.phohouseexpress.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hiển thị ảnh GIF bằng Glide
        Glide.with(this)
            .asGif()
            .load(R.raw.illustration_home)
            .into(binding.imgIllustration)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_login)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_register)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
