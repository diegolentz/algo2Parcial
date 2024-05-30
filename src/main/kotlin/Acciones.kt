interface Acciones {
    fun ejecutar()

    fun actualizar(
        programa: Programa,
        tituloActualizado: String,
        conductoresActualizados: MutableList<Conductor>,
        presupuestoActualizado: Double,
        duracionActualizada: Double
    ) {
        programa.titulo = tituloActualizado
        programa.conductores = conductoresActualizados
        programa.presupuestoBase = presupuestoActualizado
        programa.duracion = duracionActualizada
    }
}

class partirPrograma(val programaOriginal: Programa) : Acciones {
    val programaClonado = programaOriginal.clone() as Programa

    val conductoresPrimeraParte = programaOriginal.conductores.subList(0, programaOriginal.conductores.size / 2).toMutableList()
    val conductoresSegundaParte = programaOriginal.conductores.subList(programaOriginal.conductores.size / 2, programaOriginal.conductores.size).toMutableList()

    val presupuesto = programaOriginal.presupuestoBase / 2
    val duracion: Double = programaOriginal.duracion / 2

    val primerTitulo = programaOriginal.titulo.split(" ")[0]
    var primerTituloArmado = "$primerTitulo en el aire!"

    val segundoTitulo = if (programaOriginal.titulo.contains(" ")) {
        programaOriginal.titulo.split(" ")[1]
    } else {
        "programa sin nombre"
    }

    override fun ejecutar() {
        actualizar(programaOriginal, primerTituloArmado, conductoresPrimeraParte, presupuesto, duracion)
        actualizar(programaClonado, segundoTitulo, conductoresSegundaParte, presupuesto, duracion)
    }
}

class desaparecerPrograma(val programaReemplazar: Programa) : Acciones {
    override fun ejecutar() {
        actualizar(programaReemplazar, "los simpsons", mutableListOf(), 100.00, programaReemplazar.duracion)
    }
}

class fusionarPrograma(val programa: Programa, val programacion: MutableList<Programa>) : Acciones {
    val titulo: MutableList<String> = mutableListOf("Impacto total", "Un buen día")
    val primerPrograma = programacion.indexOf(programa)
    var siguientePrograma = if (primerPrograma == programacion.size - 1) {
        programacion.first()
    } else {
        programacion[primerPrograma + 1]
    }
    val conductores = mutableListOf<Conductor>().apply {
        add(programa.conductores.first())
        add(siguientePrograma.conductores.first())
    }
    val presupuesto = if (programa.presupuestoBase < siguientePrograma.presupuestoBase) {
        programa.presupuestoBase
    } else {
        siguientePrograma.presupuestoBase
    }
    val sponsor = programa.sponsors
    val setDeSponsors = programa.sponsors.union(siguientePrograma.sponsors)
    val sponsorRandom = setDeSponsors.random()
    val duracion = programa.duracion + siguientePrograma.duracion
    val dia = programa.dia
    val tituloNuevo = titulo.random()
    val programaFusionado = Programa()

    override fun ejecutar() {
        actualizar(programaFusionado, tituloNuevo, conductores, presupuesto, duracion)
    }
}

class moverDiaPrograma(val programa: Programa) : Acciones {
    override fun ejecutar() {
        programa.apply { dia = Dia.MARTES }
    }
}
