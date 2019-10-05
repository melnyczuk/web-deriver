package webderiver;

import java.util.Arrays;
import java.lang.Math;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
  private WebDriver driver;
  private Actions action;
  private Double[] location;

  public App() {
    driver = new ChromeDriver();
    action = new Actions(driver);
    location = new Double[2];
  }

  private static void sleeper(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
    return;
  }

  private static Double[] getCoords(String url) {
    String[] coordinates = url.split("@")[1].split(",");
    return new Double[] { Double.parseDouble(coordinates[0]),  Double.parseDouble(coordinates[1]) };
  }

  private static Boolean compareCoords(Double[] a, Double[] b) {
    Boolean[] comparisons = new Boolean[2];
    
    for (int i = 0; i < 2; i++) {
      comparisons[i] = Math.rint(a[i] * 1000) == Math.rint(b[i] * 1000);
    }

    return (comparisons[0] == true || comparisons[1] == true);
  }

  private void quit() {
    driver.quit();
    System.exit(0);
  }

  private WebElement find(String classname) {
    return driver.findElement(By.className(classname));
  }

  private void dragPegmanToCanvas() {
    WebElement canvas = find("widget-scene-canvas");
    WebElement pegman = find("widget-expand-button-pegman-icon");

    if (pegman == null || canvas == null) {
      quit();
    }

    action.dragAndDrop(pegman, canvas).build().perform();

    sleeper(5000);
    return;
  }

  private void setLocation(Double[] coords) {
    location = coords;
    return;
  }

  private void centerMouseToCanvas() {
    WebElement canvas = find("widget-scene-canvas");
    int height = (int) ((double) driver.manage().window().getSize().height * 0.1);
    action.moveToElement(canvas).moveByOffset(0, height).build().perform();
    return;
  }

  private void lilTurn(Boolean direction) {
    int width = (int) ((double) driver.manage().window().getSize().width * 0.2);
    int offset = direction ? -width : width; 
    action.moveByOffset(offset, 0).perform();
    sleeper(500);
    walk();
    sleeper(1000);
    centerMouseToCanvas();
    return;
  }

  private void bigTurn(Boolean direction) {
    WebElement arrow = find(direction ? "compass-clockwise-arrow" : "compass-counterclockwise-arrow");
    action.click(arrow).perform();
    sleeper(1000);
    centerMouseToCanvas();
    sleeper(1000);
    return;
  }

  private void walk() {
    action.doubleClick().perform();
    sleeper(1000);
    return;
  }

  public static void main(String args[]) {
    App app = new App();

    String url = String.format("https://www.google.com/maps/@%s,%s,16z", args[0], args[1]);

    app.driver.get(url);
    sleeper(5000);
    app.driver.manage().window().maximize();
    sleeper(1000);

    app.dragPegmanToCanvas();
    app.setLocation(getCoords(app.driver.getCurrentUrl()));

    while (true) {
      Double[] currentCoords = getCoords(app.driver.getCurrentUrl());

      if (compareCoords(currentCoords, app.location)) {
        app.setLocation(currentCoords);
        app.lilTurn(Math.rint(Math.random()) == 0);
      }

      app.walk();
    }
  }
}
