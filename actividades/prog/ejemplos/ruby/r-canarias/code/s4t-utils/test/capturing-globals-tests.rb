#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'
require 's4t-utils'


class CapturingGlobalsTests < Test::Unit::TestCase
  include S4tUtils

  def test_capturing_stderr
    result = capturing_stderr do 
      $stderr.puts "hello, world"
    end

    assert_equal("hello, world\n", result)
  end

  def test_with_environment_vars
    ENV["test_test"] = "original"
    result = with_environment_vars({ "test_test" => "replacement" }) {
      ENV["test_test"]
    }
    assert_equal("replacement", result)
    assert_equal("original", ENV["test_test"])
  end

  def test_with_local_config_file
    assert_false(File.exist?("./config"))
    with_local_config_file("config", "contents\nfoo\n") {
      assert_equal(".", ENV['HOME'])
      assert_equal("contents\nfoo\n", IO.read("config"))
    }
    assert_false(File.exist?("./config"))
  end
                 
  def test_with_command_args
    with_command_args("sizzle steak") {
      assert_equal('sizzle', ARGV[0])
      assert_equal('steak', ARGV[1])
    }
  end

end
