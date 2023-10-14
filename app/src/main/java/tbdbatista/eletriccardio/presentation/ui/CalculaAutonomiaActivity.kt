package tbdbatista.eletriccardio.presentation.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tbdbatista.eletriccardio.R

class CalculaAutonomiaActivity : AppCompatActivity() {

    var kmPercorridoValue: Float = 0.0f
    var custoKwhValue: Float = 0.0f

    lateinit var kmPercorridoEditText: EditText
    lateinit var custoKwhEditText: EditText
    lateinit var resultado: TextView
    lateinit var calcularBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupViews()
        setupListeners()
        setupCachedResult()
    }

    fun setupViews() {
        kmPercorridoEditText = findViewById<EditText>(R.id.et_km_percorrido)
        custoKwhEditText = findViewById<EditText>(R.id.et_preco_kwh)
        resultado = findViewById(R.id.tv_resultado)
        calcularBotao = findViewById(R.id.button_calcular)
    }

    private fun setupCachedResult() {
        val valorCalculado = getSharedPref()
        resultado.text = valorCalculado.toString()
    }

    fun setupListeners() {
        calcularBotao.setOnClickListener {
            if(kmPercorridoEditText.text.isNotEmpty() && custoKwhEditText.text.isNotEmpty()) {
                kmPercorridoValue = kmPercorridoEditText.text.toString().toFloat()
                custoKwhValue = custoKwhEditText.text.toString().toFloat()
                calculate(kmPercorridoValue, custoKwhValue)
            } else {
                calcularBotao.text = "Digite dois valores válidos para o cálculo"
            }
        }
    }

    fun calculate(a: Float, b: Float) {
        val preco = custoKwhEditText.text.toString().toFloat()
        val km = kmPercorridoEditText.text.toString().toFloat()
        val result = preco / km

        resultado.text = result.toString()
        saveSharedPref(result)
    }

    fun saveSharedPref(resultado: Float) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), resultado)
            apply()
        }
    }

    fun getSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}