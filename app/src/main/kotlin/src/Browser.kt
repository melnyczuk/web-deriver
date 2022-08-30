package src

import java.awt.Robot
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriver.Window
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

const val CANVAS_ID = "scene"
const val PEGMAN_ID = "q2sIQ"
const val REJECT_COOKIES_XPATH = "//button[normalize-space()='Reject all']"
const val LEFT_ARROW = "//*[@id='compass']/div/button[1]"
const val CENTER_ARROW = "//*[@id='compass']/div/button[2]"
const val RIGHT_ARROW = "//*[@id='compass']/div/button[3]"

enum class Directions {
  LEFT,
  RIGHT,
}

enum class Movements {
  FORWARD,
  BACKWARD,
}

class Browser {
  private val driver: WebDriver = ChromeDriver()
  private val window: Window = driver.manage().window()
  private val actions: Actions = Actions(driver)
  private val robot: Robot = Robot()

  val location: Location?
    get() =
        """@\d+.\d+,-?\d+.\d+,""".toRegex().find(driver.currentUrl)?.let {
          val coords = it.value.split(",")
          return Location(coords[0].split("@")[1].toDouble(), coords[1].toDouble())
        }

  constructor(url: String) {
    Thread.sleep(2000)
    window.fullscreen()
    Thread.sleep(2000)
    driver.navigate().to(url)
    Thread.sleep(5000)
    driver.findElement(By.xpath(REJECT_COOKIES_XPATH)).click()
    Thread.sleep(5000)
    driver.findElement(By.id(PEGMAN_ID)).click()
    driver.findElement(By.id(CANVAS_ID)).click()
    Thread.sleep(3000)
  }

  fun clickWindowCenter(): Unit = actions.click(driver.findElement(By.id(CANVAS_ID))).perform()
  fun forward(): Unit = move(Movements.FORWARD)
  fun backward(): Unit = move(Movements.BACKWARD)
  fun left(): Unit = turn(Directions.LEFT)
  fun right(): Unit = turn(Directions.RIGHT)
  fun finish(): Unit = driver.close().let { driver.quit() }

  private fun move(movement: Movements): Unit =
      when (movement) {
            Movements.FORWARD -> actions.sendKeys(Keys.UP)
            Movements.BACKWARD -> actions.sendKeys(Keys.DOWN)
          }
          .perform()
          .let { Thread.sleep(1000) }

  private fun turn(direction: Directions): Unit =
      actions
          .click(
              when (direction) {
                Directions.LEFT -> driver.findElement(By.xpath(LEFT_ARROW))
                Directions.RIGHT -> driver.findElement(By.xpath(RIGHT_ARROW))
              }
          )
          .perform()
          .let { Thread.sleep(1000) }
}
