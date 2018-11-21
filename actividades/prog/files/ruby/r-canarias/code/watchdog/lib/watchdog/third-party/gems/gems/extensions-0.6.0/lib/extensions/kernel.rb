#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/module.rb
#
# Adds methods to the builtin Kernel module. 
#

require "extensions/_base"

ExtensionsProject.implement(Kernel, :require_relative) do
  module Kernel
      #
      # <tt>require_relative</tt> complements the builtin method <tt>require</tt> by allowing
      # you to load a file that is <em>relative to the file containing the require_relative
      # statement</em>.
      #
      # When you use <tt>require</tt> to load a file, you are usually accessing functionality
      # that has been properly installed, and made accessible, in your system.  <tt>require</tt>
      # does not offer a good solution for loading files within the project's code.  This may
      # be useful during a development phase, for accessing test data, or even for accessing
      # files that are "locked" away inside a project, not intended for outside use.
      #
      # For example, if you have unit test classes in the "test" directory, and data for them
      # under the test "test/data" directory, then you might use a line like this in a test
      # case:
      #
      #   require_relative "data/customer_data_1"
      #
      # Since neither "test" nor "test/data" are likely to be in Ruby's library path (and for
      # good reason), a normal <tt>require</tt> won't find them.  <tt>require_relative</tt> is
      # a good solution for this particular problem.
      #
      # You may include or omit the extension (<tt>.rb</tt> or <tt>.so</tt>) of the file you
      # are loading.
      #
      # _path_ must respond to <tt>to_str</tt>.
      #
    def require_relative(path)
      require File.join(File.dirname(caller[0]), path.to_str)
    end
  end
end
