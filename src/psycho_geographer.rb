require 'faraday'
class PsychoGeographer
  BASE_URL = 'https://google.com'
  PATH = '/google/places/fetchPlaces'

  def locate(x, y)
    route = '/locate'
    query = '?lat=%{x}&lng=%{y}' % { :x => x, :y => y }
    puts Faraday.get(BASE_URL + route + query).body
  end
end
