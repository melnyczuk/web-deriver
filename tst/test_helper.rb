# spec/spec_helper.rb
RSpec.configure do |config|
  config.before(:each) do
    stub_request(:any, /api.github.com/).to_rack(FakeGitHub)
  end
end
