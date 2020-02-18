require 'faraday'
class PsychoGeographer
  BASE_URL = 'https://google.com'
  PATH = '/locate?loc=%{x},%{y}'

  def locate(x, y)
    route = '/locate'
    query = '?loc=%{x},%{y}' % { :x => x, :y => y }
    puts Faraday.get(BASE_URL + route + query).body
  end
end
