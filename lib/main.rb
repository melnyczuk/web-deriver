# frozen_string_literal: true
require_relative "webderiver"

WebDeriver.new.Deriver.new.run(*ARGV[0..1])
