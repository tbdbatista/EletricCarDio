package tbdbatista.eletriccardio.presentation.data

import tbdbatista.eletriccardio.presentation.domain.Carro

object CarroFactory {

    val list = listOf(
        Carro(
            id = 1,
            preco = "R$ 300.000,00",
            bateria = "300 kWh",
            potencia = "200cv",
            recarga = "30 min",
            urlPhoto = "www.google.com.br"
        ),
        Carro(
            id = 2,
            preco = "R$ 200.000,00",
            bateria = "200 kWh",
            potencia = "150cv",
            recarga = "40 min",
            urlPhoto = "www.google.com.br"
        )
    )
}