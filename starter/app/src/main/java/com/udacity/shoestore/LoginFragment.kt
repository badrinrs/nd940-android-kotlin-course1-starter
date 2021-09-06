package com.udacity.shoestore

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        binding.loginButton.setOnClickListener {
            if(binding.emailEditText.text.toString().isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                AlertDialog.Builder(activity)
                    .setTitle("Login Error!")
                    .setMessage("Email or Password Missing")
                    .setIcon(android.R.drawable.stat_notify_error)
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }.create().show()
            }
        }
        binding.registerButton.setOnClickListener {
            if(binding.emailEditText.text.toString().isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                AlertDialog.Builder(activity)
                    .setTitle("Registration Error!")
                    .setMessage("Email or Password Missing")
                    .setIcon(android.R.drawable.stat_notify_error)
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }.create().show()
            }
        }
        return binding.root
    }
}