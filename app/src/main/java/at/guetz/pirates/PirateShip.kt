package at.guetz.pirates

data class PirateShip(val id: Int, val title: String = "", val description: String, val price: Int,
                      val image: String, val greeting: String)