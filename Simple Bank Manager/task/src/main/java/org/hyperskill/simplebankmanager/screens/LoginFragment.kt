package org.hyperskill.simplebankmanager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.R
import org.hyperskill.simplebankmanager.databinding.FragmentLoginBinding


private const val duration = Toast.LENGTH_SHORT
private const val textSuccess = "logged in"
private const val textNotSuccess = "invalid credentials"

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        val intent = (view.context as AppCompatActivity).intent
        val userName = intent.extras?.getString("username") ?: "Lara"
        val userPassword = intent.extras?.getString("password") ?: "1234"

        val userBalance = intent.extras?.getDouble("balance") ?: 100.0

        binding.loginButton.setOnClickListener {
            val inputName = binding.loginUsername.text.toString()
            val inputPassword = binding.loginPassword.text.toString()
            if (userName == inputName && userPassword == inputPassword) {
                bundle.putString("USER_NAME", inputName)
                intent.putExtra("BALANCE", userBalance)
//                bundle.putDouble("BALANCE", userBalance)
                Toast.makeText(context, textSuccess, duration).show()
                findNavController().navigate(R.id.userMenuFragment, bundle)
            } else Toast.makeText(context, textNotSuccess, duration).show()
        }

    }

}