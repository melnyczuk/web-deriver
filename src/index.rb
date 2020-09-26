require_relative 'src/deriver'
  guy_debord = Deriver.new *ARGV[0..1]
begin
  guy_debord.start.run.fin
rescue Exception => e
  puts e
  exit 0
end
