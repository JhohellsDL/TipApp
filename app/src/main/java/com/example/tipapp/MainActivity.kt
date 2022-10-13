package com.example.tipapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tipapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener { calcularPropina() }

        binding.costoDeServicioEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view,keyCode) }
    }

    private fun calcularPropina() {
        val stringInTextField = binding.costoDeServicioEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null){
            binding.montoPropina.text = ""
            return
        }

        val porcentajePropinas = when (binding.opcionesPropina.checkedRadioButtonId) {
            R.id.opcion_veinte_porciento -> 0.20
            R.id.opcion_diesiocho_porciento -> 0.18
            else -> 0.15
        }

        var propina = cost * porcentajePropinas
        if(binding.rendondeoSwitch.isChecked){
            propina = ceil(propina)
        }

        val propinaFormateada = NumberFormat.getCurrencyInstance().format(propina)
        binding.montoPropina.text = getString(R.string.monto_de_propina, propinaFormateada)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}