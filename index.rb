require_relative 'src/deriver'
Deriver.new(*ARGV[0..1]).start.run.quit
