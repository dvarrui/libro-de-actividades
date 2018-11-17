#!/usr/bin/env ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'fileutils'
require 'rubygems'
require 'test/unit/testsuite'
require 'test/unit/ui/reporter/reporter'
require 'test/unit/ui/console/testrunner'

fail "Missing Test Results Directory" if ARGV.empty?
html_dir = ARGV.shift

FileUtils.rm_r html_dir rescue nil
FileUtils.mkdir_p html_dir

Dir['test/test*.rb'].each do |fn|
  load fn
end

suite = Test::Unit::TestSuite.new
ObjectSpace.each_object(Class) do |cls|
  next if cls == Test::Unit::TestCase
  suite << cls.suite if cls.respond_to?(:suite)
end

Test::Unit::UI::Reporter::Reporter.run(suite, html_dir)
