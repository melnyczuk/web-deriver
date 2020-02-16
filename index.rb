require_relative 'src/deriver'
  guy_debord = Deriver.new *ARGV[0..1]
begin
  guy_debord.start.run.fin
rescue
  exit 0
end