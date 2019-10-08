package webderiver;

import java.lang.Math;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;

public class Deriver {
  private WebDriver driver;
  private Actions action;
  PsychoGeographer pg;

  public Deriver() {
    driver = new ChromeDriver();
    action = new Actions(driver);
    pg = new PsychoGeographer();
  }

  private void goToSpot(String x, String y) {
    String url = String.format("https://www.google.com/maps/@%s,%s,16z", x, y);
    driver.get(url);
    Utils.sleeper(5000);
  }

  private void maximize() {
    // driver.manage().window().maximize();
    Utils.sleeper(1000);
    pg.setWidth(driver.manage().window().getSize().width);
  }

  private void dragPegmanToCanvas() {
    WebElement canvas = driver.findElement(By.className("widget-scene-canvas"));
    WebElement pegman = driver.findElement(By.className("widget-expand-button-pegman-icon"));

    if (pegman == null || canvas == null) {
      quit();
    }

    action.dragAndDrop(pegman, canvas).perform();
    Utils.sleeper(5000);
    return;
  }

  private void doBigTurn(boolean turn) {
    if (turn == true) {
      String direction = Math.rint(Math.random()) == 0 ? "compass-clockwise-arrow" : "compass-counterclockwise-arrow";
      WebElement arrow = driver.findElement(By.className(direction));

      action.click(arrow).perform();
      
      Utils.sleeper(1000);

      return;
    }
  }

  private void progress(int horizontalOffset) {
    WebElement canvas = driver.findElement(By.className("widget-scene-canvas"));
    int height = driver.manage().window().getSize().height;
    int verticalOffset = Math.toIntExact(Math.round(height * 0.05));

    action
      .moveToElement(canvas)
      .moveByOffset(horizontalOffset, verticalOffset)
      .click()
      .perform();

    Utils.sleeper(1000);

    return;
  }

  public void init(String x, String y) {
    goToSpot(x, y);
    maximize();
    dragPegmanToCanvas();
    pg.setLocation(Utils.parseXY(x, y));
    doBigTurn(true);
    return;
  }

  public void quit() {
    driver.quit();
    System.exit(0);
  }

  public void walk() {
    Double[] coords = Utils.parseCoords(driver.getCurrentUrl());
    progress(pg.lilTurn(coords));
    pg.setLocation(coords);
    return;
  }
}