package tbdbatista.eletriccardio.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tbdbatista.eletriccardio.R
import tbdbatista.eletriccardio.presentation.domain.Carro

class CarroAdapter(private val carros: List<Carro>) : RecyclerView.Adapter<CarroAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView

        init {
            itemView.apply {
                preco = findViewById(R.id.tv_preco_valor)
                bateria = findViewById(R.id.tv_bateria_valor)
                potencia = findViewById(R.id.tv_potencia_valor)
                recarga = findViewById(R.id.tv_recarga_valor)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return carros.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.bateria.text = carros[position].bateria
        holder.potencia.text = carros[position].potencia
        holder.recarga.text = carros[position].recarga
    }
}

