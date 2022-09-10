package src

const val BASE_URL = "https://www.google.com/maps/"

data class Location(val x: Double, val y: Double)

class Deriver {
  private val browser: Browser
  private var location: Location
  private val isStuck: Boolean
    get() = browser.location?.let { it.x == location.x && it.y == location.y } ?: true

  constructor(x: Double, y: Double) {
    location = Location(x, y)
    browser = Browser(getUrl(location))
  }

  fun walk(): Unit {
    browser.forward()
    getUnstuck()
    browser.location?.let { location = it }
  }

  fun getUnstuck(): Unit {
    val turn = if (Math.random() > 0.5) browser::left else browser::right

    while (isStuck) {
      browser.forward()
      turn()
      browser.forward()
    }
  }

  fun finish(): Unit = browser.finish()

  private fun getUrl(loc: Location): String {
    val x = loc.x
    val y = loc.y
    return BASE_URL + "@$x,$y,15z"
  }
}
