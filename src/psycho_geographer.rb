require 'faraday'
class PsychoGeographer
  BASE_URL = 'https://google.com'
  PATH = '/locate?loc=%{x},%{y}'

  def initialize 
    @x = 2
  end

  def locate(x, y)
    puts Faraday.get(BASE_URL + PATH % { :x => x, :y => y } ).body
  end
end
