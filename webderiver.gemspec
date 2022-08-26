# frozen_string_literal: true

require_relative 'lib/webderiver'

Gem::Specification.new do |spec|
  spec.name          = 'webderiver'
  spec.version       = WebDeriver::VERSION
  spec.authors       = ['how melnyczuk']
  spec.email         = ['how@melnycz.uk']

  spec.summary       = 'A gem to drift around Google Street view'
  spec.description   = 'A technological exploration of Situationist philosophy and practice
                        that explores the layers of metadata that overlay the physical world'
  spec.homepage      = 'https://github.com/melnyczuk/web-deriver'
  spec.license       = 'MIT'
  spec.required_ruby_version = Gem::Requirement.new('>= 2.3.0')

  spec.metadata['homepage_uri'] = spec.homepage
  spec.metadata['source_code_uri'] = spec.homepage

  # Specify which files should be added to the gem when it is released.
  # The `git ls-files -z` loads the files in the RubyGem that have been added into git.
  spec.files = Dir.chdir(File.expand_path(__dir__)) do
    `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  end
  spec.bindir        = 'exe'
  spec.executables   = spec.files.grep(%r{^exe/}) { |f| File.basename(f) }
  spec.require_paths = ['lib']
end
