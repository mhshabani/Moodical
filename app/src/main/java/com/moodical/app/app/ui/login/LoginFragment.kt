package com.moodical.app.app.ui.login

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

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {

            if (usernameEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            ) {

                val userRepository = Injector.provideUserRepository(view.context)
                userRepository.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    ::onLoginResult
                )
            }
        }
        view.findViewById<View>(R.id.go_to_register).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, RegisterFragment())
                ?.commit()
        }
    }

    private fun onLoginResult(result: String) {
        println(result)
        println(activity)
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
