#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# A template for new files in the project; of no interest to end users.  An
# error will be raised if you +require+ it.
#--
# :enddoc:
#
# == extensions/XXX.rb
#
# Adds methods to the builtin XXX class. 
#

raise "Do not load this file!"

require "extensions/_base"

#
# * Enumerable#build_hash
#
ExtensionsProject.implement(Enumerable, :build_hash) do
  module Enumerable
    #
    # Like #map/#collect, but it generates a Hash.
    #
    #   [1,5,11].build_hash { |x| [x, x**2] }
    #      => { 1 => 2, 5 => 25, 11 => 121 }
    #
    def build_hash
      result = {}
      self.each do |elt|
        key, value = yield elt
        result[key] = value
      end
      result
    end
  end
end
