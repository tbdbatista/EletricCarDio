package tbdbatista.eletriccardio.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tbdbatista.eletriccardio.R
import tbdbatista.eletriccardio.presentation.data.CarroFactory
import tbdbatista.eletriccardio.presentation.ui.adapters.CarroAdapter

class CarroFragment : Fragment() {

    lateinit var goToCalcularBotao: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.carro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        setupLista()
    }

    fun setupViews(view: View) {
        goToCalcularBotao = view.findViewById(R.id.button_goto_calcular)
        listaCarros = view.findViewById(R.id.rv_lista_carro)
    }
    fun setupListeners() {
        goToCalcularBotao.setOnClickListener {
            startActivity(Intent(context, CalculaAutonomiaActivity::class.java))
        }
    }

    fun setupLista() {
        val adapter = CarroAdapter(CarroFactory.list)
        listaCarros.layoutManager = LinearLayoutManager(context)
        listaCarros.adapter = adapter
    }
}