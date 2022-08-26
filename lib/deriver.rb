# frozen_string_literal: true

require 'webderiver'

module WebDeriver
  class Error < StandardError; end

  # Handles all logic around making the automaton move
  class Deriver
    BASE_URL = 'https://www.google.com/maps/'

    def initialize(lat, lng)
      @browser = Browser.new
      @location = { :lat => lat, :lng => lng }
    end

    def walk
      start

      loop do
        forward
        left_or_right
      end

      @browser.finish
    end

    private

    def start
      url = BASE_URL + format('@%<lat>s,%<lng>s,15z', @location)

      @browser
        .maximize
        .go_to(url)
        .agree
        .drag_pegman
        .click

      self
    end

    def left_or_right
      key = rand(2) == 1 ? :right : :left
      @browser.send_key(key)
      sleep 1
    end

    def forward
      @browser.send_key(:up)
      sleep 1
    end
  end
end
