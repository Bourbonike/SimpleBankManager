package org.hyperskill.simplebankmanager.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.R
import org.hyperskill.simplebankmanager.databinding.FragmentTransferFundsBinding

private const val duration = Toast.LENGTH_SHORT
private const val textNotSuccess = "Not enough funds to transfer"

class TransferFundsFragment : Fragment() {
    private lateinit var binding: FragmentTransferFundsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransferFundsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = (view.context as AppCompatActivity).intent

        binding.transferFundsButton.setOnClickListener {
            checkAndCalculateState(intent)
        }

    }

    private fun checkAndCalculateState(intent: Intent) {
        val bundle = Bundle()

        val balance = arguments?.getDouble("BALANCE")

        val inputAccountNumber = binding.transferFundsAccountEditText.text.toString()
        val inputAmount = binding.transferFundsAmountEditText.text.toString()
        var trueAccount = false
        var trueAmount = false


        if (inputAccountNumber.isNotEmpty()) {
            if (
                inputAccountNumber[0].toString() == "s" || inputAccountNumber[0].toString() == "c"
                && inputAccountNumber[1].toString() == "a"
                && inputAccountNumber.substring(2).count() == 4
                && inputAccountNumber.substring(2).toIntOrNull() != null

            ) {
                trueAccount = true
            } else binding.transferFundsAccountEditText.error = "Invalid account number"
        } else binding.transferFundsAccountEditText.error = "Invalid account number"
        if (inputAmount.isNotEmpty() && inputAmount.toDouble() > 0) {
            trueAmount = true
        } else binding.transferFundsAmountEditText.error = "Invalid amount"

        if (trueAccount && trueAmount) {
            if (inputAmount.toDouble() > balance!!) {
                Toast.makeText(
                    context,
                    "$textNotSuccess $${String.format("%.2f", inputAmount.toDouble())}",
                    duration
                ).show()
            }
            if (inputAmount.toDouble() <= balance) {
                Toast.makeText(
                    context,
                    "Transferred $${
                        String.format(
                            "%.2f",
                            inputAmount.toDouble()
                        )
                    } to account $inputAccountNumber",
                    duration
                ).show()
                intent.putExtra("BALANCE", balance - inputAmount.toDouble())
//                bundle.putDouble("BALANCE", balance - inputAmount.toDouble())
                findNavController().navigate(R.id.userMenuFragment, bundle)
            }
        }
    }
}

