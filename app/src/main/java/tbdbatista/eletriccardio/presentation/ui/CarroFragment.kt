package tbdbatista.eletriccardio.presentation.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import tbdbatista.eletriccardio.R
import tbdbatista.eletriccardio.presentation.domain.Carro
import tbdbatista.eletriccardio.presentation.ui.adapters.CarroAdapter
import java.net.HttpURLConnection
import java.net.URL

class CarroFragment : Fragment() {

    lateinit var goToCalcularBotao: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var progressBar: ProgressBar
    val carrosArray = ArrayList<Carro>()

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
        callService()
    }

    fun setupViews(view: View) {
        goToCalcularBotao = view.findViewById(R.id.button_goto_calcular)
        listaCarros = view.findViewById(R.id.rv_lista_carro)
        progressBar = view.findViewById(R.id.pb_loader)
    }
    fun setupListeners() {
        goToCalcularBotao.setOnClickListener {
            startActivity(Intent(context, CalculaAutonomiaActivity::class.java))
        }
    }

    fun setupLista() {
        val adapter = CarroAdapter(carrosArray)
        listaCarros.layoutManager = LinearLayoutManager(context)
        listaCarros.adapter = adapter
    }

    // Funções assíncronas
    inner class  MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", " onPreExecute")
        }
        override fun doInBackground(vararg params: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {

                var url = URL(params[0])
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty("Accept", "application/json")

                if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {

                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                } else {
                    Log.e("ServiceError", "Serviço indisponível no momento")
                }
            } catch (e: java.lang.Exception) {
                Log.e("StreamError", e.toString())
                Log.e("StreamError", "Stream error on doInBackground in CarroFragment.kt")
            } finally {
                urlConnection?.disconnect()
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("Preco ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("Preco ->", bateria)

                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("Potencia ->", potencia)

                    val recarga = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("Recarga ->", recarga)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )
                    carrosArray.add(model)
                }
                progressBar.isVisible = false
                listaCarros.isVisible = true
//                noInternetImage.isVisible = false
//                noInternetText.isVisible = false
                setupLista()
            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }

    }

    fun callService() {
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        listaCarros.isVisible = false
        progressBar.isVisible = true
        MyTask().execute(urlBase)
    }
}

