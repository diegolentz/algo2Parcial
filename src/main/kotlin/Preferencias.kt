interface IPreferencias{
    fun loPrefiere(regalo: Regalo ): Boolean
}

class Conformistas : IPreferencias {
    override fun loPrefiere(regalo: Regalo) = true
}
class Interesadas(var monto : Double) : IPreferencias {
    override fun loPrefiere(regalo: Regalo) = regalo.superaMonto(monto)
}
class Exigentes : IPreferencias {
    override fun loPrefiere(regalo: Regalo) = regalo.seraValioso()
}
class Marqueras(val marcasPreferida : List<String>) : IPreferencias {

    override fun loPrefiere(regalo: Regalo) = marcasPreferida.contains(regalo.marca)
}
class Combinetas(val preferencias: List<IPreferencias>) : IPreferencias {
    override fun loPrefiere(regalo: Regalo) = preferencias.any { it.loPrefiere(regalo) }
}
