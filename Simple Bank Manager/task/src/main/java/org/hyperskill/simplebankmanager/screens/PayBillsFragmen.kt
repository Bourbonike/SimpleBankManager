package org.hyperskill.simplebankmanager.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.hyperskill.simplebankmanager.databinding.FragmentPayBillsBinding
import org.hyperskill.simplebankmanager.defaultMaps.defaultBillInfoMap

class PayBillsFragmen : Fragment() {
    private lateinit var binding: FragmentPayBillsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPayBillsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val balance = arguments?.getDouble("BALANCE")
        var momentBalance = balance ?: 0.0


        val intent = (view.context as AppCompatActivity).intent
        val billInfoMap =
            intent.extras?.getSerializable("billInfo") as? Map<String, Triple<String, String, Double>>
                ?: defaultBillInfoMap


        binding.payBillsShowBillInfoButton.setOnClickListener {
            val editText = binding.payBillsCodeInputEditText.text
            val operationCost = billInfoMap["$editText"]?.third ?: 0.0
            if (billInfoMap.contains(editText.toString())) {

                AlertDialog.Builder(requireContext())
                    .setTitle("Bill info")
                    .setMessage(
                        "Name: ${billInfoMap["$editText"]?.first}\n" +
                                "BillCode: ${billInfoMap["$editText"]?.second}\n" +
                                "Amount: $${
                                    String.format("%.2f", billInfoMap["$editText"]?.third)
                                }"
                    )
                    .setPositiveButton("Confirm") { _, _ ->
                        if (momentBalance >= operationCost) {
                            Toast.makeText(
                                requireContext(),
                                "Payment for bill ${billInfoMap["$editText"]?.first}, was successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            momentBalance -= operationCost
                            intent.putExtra("BALANCE", momentBalance)
                        } else {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Error")
                                .setMessage("Not enough funds")
                                .setPositiveButton(android.R.string.ok) { _, _ ->
                                    Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                .show()
                        }
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                    }
                    .show()


            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Wrong code")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }

        }
    }


}