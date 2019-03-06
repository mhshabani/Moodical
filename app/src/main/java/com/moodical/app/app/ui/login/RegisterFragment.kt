package com.moodical.app.app.ui.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.moodical.app.R
import com.moodical.app.app.Injector
import com.moodical.app.app.ui.home.HomeBaseFragment

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayNameEditText = view.findViewById<EditText>(R.id.display_name)
        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val emailEditText = view.findViewById<EditText>(R.id.email)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.confirm_password)
        view.findViewById<View>(R.id.register).setOnClickListener {

            var error = false
            if (displayNameEditText.text.isEmpty()) {
                displayNameEditText.setTextColor(Color.RED)
                error = true
            }
            if (usernameEditText.text.isEmpty()) {
                usernameEditText.setTextColor(Color.RED)
                error = true
            }
            if (emailEditText.text.isEmpty()) {
                emailEditText.setTextColor(Color.RED)
                error = true
            }
            if (passwordEditText.text.isEmpty()) {
                passwordEditText.setTextColor(Color.RED)
                error = true
            }
            if (confirmPasswordEditText.text.isEmpty()) {
                confirmPasswordEditText.setTextColor(Color.RED)
                error = true
            }
            if (confirmPasswordEditText.text.isEmpty()) {
                passwordEditText.setTextColor(Color.RED)
                confirmPasswordEditText.setTextColor(Color.RED)
                error = true
            }

            if (error) {
                return@setOnClickListener
            }

            val userRepository = Injector.provideUserRepository(view.context)
            userRepository.register(
                displayNameEditText.text.toString(),
                usernameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                ::onRegisterResult
            )


        }
    }


    private fun onRegisterResult(result: String) {
        activity?.runOnUiThread {
            if (result.isEmpty()) {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, HomeBaseFragment())
                    ?.commit()
            } else {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
