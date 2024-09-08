package org.hyperskill.simplebankmanager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.hyperskill.simplebankmanager.R
import org.hyperskill.simplebankmanager.databinding.FragmentViewBalanceBinding

class ViewBalanceFragment : Fragment() {
    private lateinit var binding: FragmentViewBalanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBalanceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balance = arguments?.getDouble("BALANCE")
        binding.viewBalanceAmountTextView.text =
            resources.getString(R.string.user_balance, String.format("%.2f", balance))
    }
}