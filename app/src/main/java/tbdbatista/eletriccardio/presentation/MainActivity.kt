package tbdbatista.eletriccardio.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import tbdbatista.eletriccardio.R

class MainActivity : AppCompatActivity() {
    var kmPercorridoValue: Float = 0.0f
    var custoKwhValue: Float = 0.0f

    lateinit var kmPercorridoEditText: EditText
    lateinit var custoKwhEditText: EditText
    lateinit var calcularBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
    }

    fun setupViews() {
        kmPercorridoEditText = findViewById<EditText>(R.id.et_km_percorrido)
        custoKwhEditText = findViewById<EditText>(R.id.et_preco_kwh)
        calcularBotao = findViewById(R.id.button_calcular)
    }

    fun setupListeners() {

        calcularBotao.setOnClickListener {
            kmPercorridoValue = kmPercorridoEditText.text.toString().toFloat()
            custoKwhValue = custoKwhEditText.text.toString().toFloat()
            calculate()
        }
    }

    fun calculate() {
        Log.i("TAG", kmPercorridoValue.toString())
        Log.i("TAG", custoKwhValue.toString())
    }
}