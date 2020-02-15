require 'selenium-webdriver'
class Browser 
  :driver
  CANVAS_CLASS = 'widget-scene-canvas'
  PEGMAN_CLASS = 'widget-expand-button-pegman-icon'

  def initialize() @driver = Selenium::WebDriver.for :chrome end
    
  def move(dist) action.move_to(canvas, vertical_offset, dist).click.perform end
  def drag_pegman() (!pegman || !canvas) ? quit : drag_pegman_to_canvas end
  def go_to(url) @driver.get url end
  def quit() @driver.quit end
  
  private def action() @driver.action end
  private def drag_pegman_to_canvas() action.drag_and_drop(pegman, canvas).perform end
  private def vertical_offset() window_height.round * 0.05 end
  private def window_height() @driver.manage.window.size.height end
  private def canvas() element CANVAS_CLASS end
  private def pegman() element PEGMAN_CLASS end
  private def element(cls) @driver.find_element(class: cls) end
end