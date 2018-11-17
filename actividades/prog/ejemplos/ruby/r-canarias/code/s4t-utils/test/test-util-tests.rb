#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# This file should be copied into a test ending in 'tests.rb' so that
# the Rakefile knows it's a test.

require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils


class TestUtilTestsNoInclude < Test::Unit::TestCase

  def test_test_method_finds_test_directory
    assert_equal(["test found me\n"],
                 IO.readlines(TestUtil.test("test-location-file")))
  end

  def test_test_data_method_finds_test_data_directory
    assert_equal(["test_data found me\n"],
                 IO.readlines(TestUtil.test_data("test-data-location-file")))
  end
                 
  def test_script_method_finds_test_data_directory
    assert_equal("script found me\n",
                 `ruby #{TestUtil.script("script-location-file")}`)
  end
end

class TestUtilTestsWithInclude < Test::Unit::TestCase
  include S4tUtils::TestUtil

  def test_test_method_finds_test_directory
    assert_equal(["test found me\n"],
                 IO.readlines(test("test-location-file")))
  end

  def test_test_data_method_finds_test_data_directory
    assert_equal(["test_data found me\n"],
                 IO.readlines(test_data("test-data-location-file")))
  end
                 
  def test_script_method_finds_test_data_directory
    assert_equal("script found me\n",
                 `ruby #{script("script-location-file")}`)
  end
end
