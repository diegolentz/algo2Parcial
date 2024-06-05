class Persona {
    val perfilRegalo = mutableListOf<IPerfiles>()
    val regalos = mutableListOf<Regalo>()

    fun regaloAdecuado(regalo: Regalo) = perfilRegalo.any{it.esAdecuado(regalo)}
    fun recibirRegalo(regalo: Regalo) = regalos.add(regalo)
    fun modificaPerfil() {
        perfilRegalo.clear()
        perfilRegalo.add(Interesadas(5000.0))
    }

}


interface IPerfiles {
    fun esAdecuado(regalo: Regalo) : Boolean

}

class Conformista : IPerfiles {
    override fun esAdecuado(regalo: Regalo): Boolean = true
}

class Exigentes : IPerfiles {
    override fun esAdecuado(regalo: Regalo): Boolean = regalo.esValioso()
}

class Interesadas(var monto : Double) : IPerfiles {
    override fun esAdecuado(regalo: Regalo): Boolean = regalo.supera(monto)
}

class Marqueras(var marca : String) : IPerfiles {
    override fun esAdecuado(regalo: Regalo): Boolean = regalo.marca(marca)
}

class Combinetas(val preferencias : List<IPerfiles>) : IPerfiles {
    override fun esAdecuado(regalo: Regalo): Boolean = preferencias.any { it.esAdecuado(regalo) }
}
