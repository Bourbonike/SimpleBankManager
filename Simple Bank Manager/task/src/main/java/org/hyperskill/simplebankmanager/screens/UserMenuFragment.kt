package org.hyperskill.simplebankmanager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.R
import org.hyperskill.simplebankmanager.databinding.FragmentUserMenuBinding

class UserMenuFragment : Fragment() {
    private lateinit var binding: FragmentUserMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = (view.context as AppCompatActivity).intent
        val bundle = Bundle()
        val userName = arguments?.getString("USER_NAME")
        binding.userMenuWelcomeTextView.text =
            resources.getString(R.string.welcome_username, userName)

//        val balance = arguments?.getDouble("BALANCE", 100.0)
        val balance = intent.extras?.getDouble("BALANCE")



        binding.userMenuViewBalanceButton.setOnClickListener {
            bundle.putDouble("BALANCE", balance ?: 100.0)
            findNavController().navigate(R.id.viewBalanceFragment, bundle)
        }

        binding.userMenuTransferFundsButton.setOnClickListener {
            bundle.putDouble("BALANCE", balance ?: 100.0)
            findNavController().navigate(R.id.transferFundsFragment, bundle)
        }
        binding.userMenuExchangeCalculatorButton.setOnClickListener {
            findNavController().navigate(R.id.calculateExchangeFragment)
        }
        binding.userMenuPayBillsButton.setOnClickListener {
            bundle.putDouble("BALANCE", balance ?: 100.0)
            findNavController().navigate(R.id.payBillsFragment, bundle)
        }

    }

}