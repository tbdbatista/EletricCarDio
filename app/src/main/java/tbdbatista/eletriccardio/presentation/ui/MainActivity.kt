package tbdbatista.eletriccardio.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tbdbatista.eletriccardio.R
import tbdbatista.eletriccardio.presentation.ui.adapters.CarroAdapter
import tbdbatista.eletriccardio.presentation.data.CarroFactory

class MainActivity : AppCompatActivity() {

    lateinit var goToCalcularBotao: Button
    lateinit var listaCarros: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Eletric Car Calculator App"
        setupViews()
        setupListeners()
        setupLista()
    }

    fun setupListeners() {
        goToCalcularBotao.setOnClickListener {
            startActivity(Intent(this, CalculatAutonomiaActivity::class.java))
        }
    }

    fun setupViews() {
        goToCalcularBotao = findViewById(R.id.button_goto_calcular)
        listaCarros = findViewById(R.id.rv_lista_carro)
    }

    fun setupLista() {
        val adapter = CarroAdapter(CarroFactory.list)
        listaCarros.layoutManager = LinearLayoutManager(this)
        listaCarros.adapter = adapter
    }
}