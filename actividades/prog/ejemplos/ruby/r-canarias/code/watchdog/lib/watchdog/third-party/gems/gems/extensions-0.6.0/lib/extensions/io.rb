#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# == extensions/io.rb
#
# Adds methods to the builtin IO class.
#

require "extensions/_base"

# This is Ruby's built-in IO class.
class IO
end

#
# * IO.write
#
ExtensionsProject.implement(IO, :write, :class) do
  class << IO
    #
    # Writes the given data to the given path and closes the file.  This is
    # done in binary mode, complementing <tt>IO.read</tt> in standard Ruby.
    #
    # Returns the number of bytes written.
    #
    def write(path, data)
      File.open(path, "wb") do |file|
        return file.write(data)
      end
    end
  end
end

#
# * IO.writelines
#
ExtensionsProject.implement(IO, :writelines, :class) do
  class << IO
    #
    # Writes the given array of data to the given path and closes the file.
    # This is done in binary mode, complementing <tt>IO.readlines</tt> in
    # standard Ruby.
    #
    # Note that +readlines+ (the standard Ruby method) returns an array of lines
    # <em>with newlines intact</em>, whereas +writelines+ uses +puts+, and so
    # appends newlines if necessary.  In this small way, +readlines+ and
    # +writelines+ are not exact opposites. 
    #
    # Returns +nil+. 
    #
    def writelines(path, data)
      File.open(path, "wb") do |file|
        file.puts(data)
      end
    end
  end
end

