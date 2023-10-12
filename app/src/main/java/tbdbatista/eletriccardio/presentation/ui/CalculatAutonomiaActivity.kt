package tbdbatista.eletriccardio.presentation.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import tbdbatista.eletriccardio.R

class CalculatAutonomiaActivity : AppCompatActivity() {

    var kmPercorridoValue: Float = 0.0f
    var custoKwhValue: Float = 0.0f

    lateinit var kmPercorridoEditText: EditText
    lateinit var custoKwhEditText: EditText
    lateinit var calcularBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        supportActionBar?.title = "Eletric Car Calculator App"
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
        calcularBotao.text = "Resultado: " + (a/b).toString()
    }
}