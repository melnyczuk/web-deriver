# frozen_string_literal: true

require 'selenium-webdriver'

module WebDeriver
  class Error < StandardError; end

  # Handles all interactions with the browser through selenium-webdriver
  class Browser
    CANVAS_CLASS = 'widget-scene-canvas'
    PEGMAN_CLASS = 'widget-expand-button-pegman-icon'
    LEFT_ARROW = 'compass-counterclockwise-arrow'
    RIGHT_ARROW = 'compass-clockwise-arrow'
    I_AGREE = '//*[contains(text(), "My Button")]'

    def initialize
      @driver = Selenium::WebDriver.for :chrome
      @action = @driver.action
      @window = @driver.manage.window
    end

    def agree
      @action.find_element_by_xpath I_AGREE
      sleep 2
      self
    end

    def drag_pegman
      !pegman || !canvas ? finish : drag_pegman_to_canvas
      sleep 3
      self
    end

    def go_to(url)
      @driver.get url
      sleep 5
      self
    end

    def maximize
      @window.maximize
      self
    end

    attr_reader :finish

    def click
      @action
        .move_to(canvas)
        .click
        .perform
      sleep 3
      self
    end

    def send_key(key)
      @action.send_keys(key).perform
      self
    end

    private

    def left
      @action.find_element(LEFT_ARROW)
    end

    def right
      @action.find_element(RIGHT_ARROW)
    end

    def drag_pegman_to_canvas
      @action.drag_and_drop(pegman, canvas).perform
    end

    def vertical_offset
      @window.size.height.round * 0.05
    end

    def canvas
      @action.find_element(CANVAS_CLASS)
    end

    def pegman
      @action.find_element(PEGMAN_CLASS)
    end
  end
end
