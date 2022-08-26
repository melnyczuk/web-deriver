# frozen_string_literal: true

require 'json'
require 'faraday'

module WebDeriver
  class Error < StandardError; end

  # Handles complex logic about which direction the automaton should move in
  class PsychoGeographer
    BASE_URL = 'http://127.0.0.1:7777'

    def get_orientation(lat, lng)
      path = '/ratings'
      body = { lat: lat, lng: lng }
      url = BASE_URL + path
      resp = Faraday.post(url, body.to_json, 'Content-Type' => 'application/json')
      resp.body
    end
  end
end
