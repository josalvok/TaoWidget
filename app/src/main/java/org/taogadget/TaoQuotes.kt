package org.taogadget

data class Quote(val text: String, val chapter: Int)

/**
 * Paráfrasis breves y originales en español, inspiradas en el texto de Laozi
 * (dominio público). Si quieres usar una traducción concreta al español,
 * comprueba su licencia: la mayoría de traducciones modernas tienen copyright.
 */
object TaoQuotes {
    val all = listOf(
        Quote("El Tao que puede nombrarse no es el Tao eterno.", 1),
        Quote("Lo más elevado es como el agua: beneficia a todo sin competir con nada.", 8),
        Quote("Llenar la copa hasta el borde es lo mismo que derramarla. Detenerse a tiempo es sabiduría.", 9),
        Quote("Treinta radios convergen en un eje; es el vacío central lo que hace útil la rueda.", 11),
        Quote("Se moldea la arcilla para hacer una vasija, pero es su vacío lo que la hace servir.", 11),
        Quote("Alcanza el vacío más profundo, guarda la quietud: todo regresa a su raíz.", 16),
        Quote("El mejor gobernante es aquel de quien apenas se sabe que existe.", 17),
        Quote("Cede y permanecerás entero; dóblate y te mantendrás recto.", 22),
        Quote("Quien se pone de puntillas no se sostiene; quien da zancadas no llega lejos.", 24),
        Quote("Conocer a los demás es inteligencia; conocerse a uno mismo es verdadera lucidez.", 33),
        Quote("Quien vence a otros tiene fuerza; quien se vence a sí mismo es verdaderamente poderoso.", 33),
        Quote("El Tao no hace nada, y sin embargo nada queda por hacer.", 37),
        Quote("Quien sabe que tiene suficiente es rico.", 44),
        Quote("La mayor habilidad parece torpeza; la mayor elocuencia, balbuceo.", 45),
        Quote("Buscar el saber es sumar cada día; buscar el Tao es restar cada día.", 48),
        Quote("Quien sabe no habla; quien habla no sabe.", 56),
        Quote("Un árbol que apenas abarcas con los brazos nació de un diminuto brote.", 64),
        Quote("Un viaje de mil leguas comienza bajo tus pies.", 64),
        Quote("Lo rígido y fuerte es discípulo de la muerte; lo blando y flexible, de la vida.", 76),
        Quote("Nada hay más blando que el agua, y nada la supera al desgastar lo duro.", 78),
        Quote("Tengo tres tesoros: la compasión, la sencillez y la humildad.", 67),
        Quote("El sabio no acumula; cuanto más da a los demás, más tiene.", 81),
    )
}
