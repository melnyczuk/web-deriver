require_relative 'browser'

class Deriver
  :browser
  :location
  BASE_URL = 'https://www.google.com/maps/'

  def initialize(x, y) 
    @browser = Browser.new 
    @location = { :x => x, :y => y }
  end
  
  def start
    url = BASE_URL + "@%{x},%{y},15z" % @location
    @browser.go_to(url)
    sleep 3
    @browser.drag_pegman
    sleep 3
    self
  end

  def run 
    while true 
      forward 10 
    end 
  end
    
  def forward(dist) 
    @browser.move(dist)
    sleep 3
    self
  end

  def turn(ang) end

  def quit() @browser.quit end
end
