require 'selenium-webdriver'
class Browser 
  :driver
  CANVAS_CLASS = 'widget-scene-canvas'
  PEGMAN_CLASS = 'widget-expand-button-pegman-icon'
  LEFT_ARROW = "compass-counterclockwise-arrow"
  RIGHT_ARROW = "compass-clockwise-arrow" 

  def initialize() @driver = Selenium::WebDriver.for :chrome end

  def drag_pegman() (!pegman || !canvas) ? fin : drag_pegman_to_canvas end
  def go_to(url) @driver.get url end
  def maximize() window.maximize end
  def fin() close; quit end
  def click() action.move_to(canvas).click.perform end
  def send_key(key) action.send_keys(key).perform end
  
  private def choose() rand(2) == 1 end
  private def arrow() choose ? LEFT_ARROW : RIGHT_ARROW end
  private def close() @driver.close end
  private def quit() @driver.quit end
  private def action() @driver.action end
  private def drag_pegman_to_canvas() action.drag_and_drop(pegman, canvas).perform end
  private def vertical_offset() window_height.round * 0.05 end
  private def window_height() window.size.height end
  private def window() @driver.manage.window end
  private def canvas() element CANVAS_CLASS end
  private def pegman() element PEGMAN_CLASS end
  private def element(cls) @driver.find_element(class: cls) end
end