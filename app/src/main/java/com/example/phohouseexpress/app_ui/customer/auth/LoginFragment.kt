package com.example.phohouseexpress.app_ui.customer.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phohouseexpress.databinding.FragmentLoginBinding
import com.example.phohouseexpress.utils.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.phohouseexpress.utils.ApiService


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val username = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Gọi API login
            val call = RetrofitClient.instance.create(ApiService::class.java)
                .login(username, password)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(com.example.phohouseexpress.R.id.action_login_to_home)
                    } else {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

//        binding.btnCreateAccount.setOnClickListener {
//            findNavController().navigate(com.example.phohouseexpress.R.id.action_login_to_register)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
