#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/io'
require 'tempfile'

class TC_IO < Test::Unit::TestCase

  def setup
    tmp_dir = ENV["TMP"] || ENV["TEMP"] || "/tmp"
    raise "Can't find TMP directory" unless File.directory?(tmp_dir)
    @path = File.join(tmp_dir, "ruby_io_test")
  end

  # Test IO.write
  def test_io_write
    data_in = "Test data\n"
    nbytes = File.write(@path, data_in)
    data_out = File.read(@path)          # This is standard class method.
    assert_equal(data_in, data_out)
    assert_equal(data_out.size, nbytes)
  end

  # Test IO.writelines
  def test_io_writelines
    data_in = %w[one two three four five]
    File.writelines(@path, data_in)
    data_out = File.readlines(@path)     # This is standard class method.
    assert_equal(data_in, data_out.map { |l| l.chomp })
  end

end

