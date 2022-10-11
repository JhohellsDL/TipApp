package com.example.tipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun calcularPropina() {
        val stringInTextField = binding.costoDeServicio.text.toString()
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
}