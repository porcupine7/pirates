package at.guetz.pirates

data class PirateShip(val id: Int, val title: String = "", val description: String, val price: Int,
                      val image: String, val greeting_type: String) {

    fun getFormattedGreeting(): String {
        if (greeting_type == null) return "Ahoi!"
        return when (greeting_type) {
            "ah" -> "Ahoi!"
            "ar" -> "Arrr!"
            "ay" -> "Aye Aye!"
            "yo" -> "Yo ho hooo!"
            else -> "Ahoi!"
        }
    }
}

