#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'net/http'
require 'cgi'
include Net    # You don't need to understand this to understand the test.
               # See the chapter on modules if you want to.


class HttpExample < Test::Unit::TestCase

  def test_echoing
    HTTP.start('www.testing.com') do |server|   # (1)
      name = CGI.escape("Brian Marick")     # (2)
      count = CGI.escape("3")
      params = "name=#{name}&count=#{count}"     # (3)
      response = server.post('/cgi-bin/post-example', params)      # (4)
      assert_match(/Brian Marick/, response.body)      # (5)
      assert_match(/count is 3/, response.body)
    end 
  end 

end
