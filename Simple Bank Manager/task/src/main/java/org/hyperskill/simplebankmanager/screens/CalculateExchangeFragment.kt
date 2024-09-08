package org.hyperskill.simplebankmanager.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.simplebankmanager.defaultMaps.defaultMap
import org.hyperskill.simplebankmanager.R
import org.hyperskill.simplebankmanager.databinding.FragmentCalculateExchangeBinding


private const val duration = Toast.LENGTH_SHORT
private const val textSameCurrency = "Cannot convert to same currency"
private const val textAmountIsEmpty = "Enter amount"


class CalculateExchangeFragment : Fragment() {
    private lateinit var binding: FragmentCalculateExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculateExchangeBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerFrom = binding.calculateExchangeFromSpinner
        val spinnerTo = binding.calculateExchangeToSpinner

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFrom.adapter = adapter
            spinnerTo.adapter = adapter
        }

        val intent = (view.context as AppCompatActivity).intent
        val exchangeMap =
            intent.extras?.getSerializable("exchangeMap") as? Map<String, Map<String, Double>>
                ?: defaultMap


        val listener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                val spinnerItemFromPosition = binding.calculateExchangeFromSpinner.selectedItem
                val spinnerItemToPosition = binding.calculateExchangeToSpinner.selectedItem
                if (spinnerItemFromPosition == spinnerItemToPosition) {
                    Toast.makeText(context, textSameCurrency, duration).show()

                    val position = binding.calculateExchangeToSpinner.selectedItemPosition
                    binding.calculateExchangeToSpinner.setSelection(
                        if (position <= 1) {
                            position + 1
                        } else 0
                    )


                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        binding.calculateExchangeToSpinner.onItemSelectedListener = listener
        binding.calculateExchangeFromSpinner.onItemSelectedListener = listener

        binding.calculateExchangeButton.setOnClickListener {
            val spinnerItemFrom = binding.calculateExchangeFromSpinner.selectedItem
            val spinnerItemTo = binding.calculateExchangeToSpinner.selectedItem
            val inputAmount = binding.calculateExchangeAmountEditText.text.toString()


            val firstIcon = when (spinnerItemFrom.toString()) {
                "EUR" -> "€"
                "GBP" -> "£"
                "USD" -> "$"
                else -> "?"
            }
            val secondIcon = when (spinnerItemTo.toString()) {
                "EUR" -> "€"
                "GBP" -> "£"
                "USD" -> "$"
                else -> "?"
            }
            if (inputAmount.isNotEmpty()) {
                val amountOfMoney = exchangeMap[spinnerItemFrom]?.get(spinnerItemTo).toString()
                    .toDouble() * inputAmount.toDouble()
                binding.calculateExchangeDisplayTextView.text = "$firstIcon${
                    String.format(
                        "%.2f",
                        inputAmount.toDouble()
                    )
                } = $secondIcon${String.format("%.2f", amountOfMoney)}"
            } else Toast.makeText(context, textAmountIsEmpty, duration).show()

        }
    }


}

