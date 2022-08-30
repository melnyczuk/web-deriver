package src

const val BASE_URL = "https://www.google.com/maps/"

data class Location(val x: Double, val y: Double)

class Deriver {
  private val browser: Browser
  private var location: Location
  private val isStuck: Boolean
    get() = updateLocation()

  constructor(x: Double, y: Double) {
    location = Location(x, y)
    browser = Browser(getUrl(location))
  }

  fun walk(): Unit {
    if (updateLocation()) {
      getUnstuck()
    }

    browser.forward()
  }

  fun getUnstuck(): Unit {
    val turn = if (Math.random() > 0.5) browser::left else browser::right

    while (isStuck) {
      turn()
      browser.clickWindowCenter()
      browser.forward()
    }
  }

  fun finish(): Unit = browser.finish()

  fun updateLocation(): Boolean {
    val oldLocation = location.copy()
    browser.location?.let { location = it }
    return oldLocation.x == location.x && oldLocation.y == location.y
  }

  private fun getUrl(loc: Location): String {
    val x = loc.x
    val y = loc.y
    return BASE_URL + "@$x,$y,15z"
  }
}
