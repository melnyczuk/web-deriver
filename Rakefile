# frozen_string_literal: true

require 'bundler/gem_tasks'
require 'rspec/core/rake_task'

RSpec::Core::RakeTask.new(:spec)

task :default do
  bundler exec ruby 'lib/main.rb'
end

task :lint do
  sh 'rubocop -A'
end
