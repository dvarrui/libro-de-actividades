#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# = extensions/test/TEST.rb
#
# Runs all the unit tests by default, or only those listed on the command line.
#
# This file manages Ruby's load path so that the correct library files are loaded for the
# tests.  None of the individual test files do that, so it's most convenient to simply let this
# file do all the testing.
#
# It should not matter from which working directory you run this.  But it does assume it's
# located in the 'test' directory.  It won't run otherwise.
#
# Test files must be named <tt>tc_WHATEVER.rb</tt>, and can be in any directory under
# <tt>test</tt>.
#
# Usage:
#   ruby test/TEST.rb 
#   ruby test/TEST.rb str io
#       # this will run tc_string.rb and tc_io.rb
#

test_dir = File.dirname(__FILE__)
Dir.chdir(test_dir) do
  puts "Current directory: #{Dir.pwd}"

    # 1. Handle the load path.
  lib_dir = "../lib"
  unless File.directory?(lib_dir)
    raise "Can't find 'lib' directory.  Is #$0 in the 'test' directory?"
  end
  $:.unshift(lib_dir)

    # 2. Decide which test files to include.  If there are arguments, then any test
    #    file that wants to be included had better have one of the arguments as a
    #    substring.
  test_files = Dir["tc_*.rb"]
  unless ARGV.empty?
    test_files = test_files.select { |fn| ARGV.any? { |str| fn.index(str) } }
  end

    # 3. Load the test files.  It's sufficient to _require_ them, and let Test::Unit
    #    run them on exit.
  test_files.each do |test_file|
    puts "Loading test file: #{test_file}"
    require test_file
  end
end

