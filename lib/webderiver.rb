# frozen_string_literal: true

module WebDeriver
  VERSION = '0.1.0'
  def WebDeriver.run(lat, lng)
    d = WebDeriver::Deriver.new(lat, lng)
    d.walk
  end
end


