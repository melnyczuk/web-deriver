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
    @browser.maximize
    @browser.go_to url
    sleep 5
    @browser.drag_pegman
    sleep 3
    @browser.click
    sleep 3
    self
  end

  def run 
    while true 
      @browser.send_key :up
      sleep 1
      @browser.send_key left_or_right
      sleep 1
    end
    self 
  end

  def fin() @browser.fin end

  private def url() BASE_URL + "@%{x},%{y},15z" % @location end
  private def choose() rand(2) == 1 end
  private def left_or_right() choose ? :right : :left end
end
