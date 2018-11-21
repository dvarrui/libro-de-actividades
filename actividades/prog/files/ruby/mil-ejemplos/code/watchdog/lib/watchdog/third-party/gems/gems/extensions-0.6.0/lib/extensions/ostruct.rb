#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/ostruct.rb
#
# Adds methods to the standard library's OpenStruct class. 
#

require "extensions/_base"
require 'ostruct'

#
# * OpenStruct#initialize
#
ExtensionsProject.implement(OpenStruct, :initialize) do
  class OpenStruct
    alias old_initialize initialize
    private :old_initialize

    #
    # Allows the initialization of an OpenStruct with a block:
    #
    #   person = OpenStruct.new do |p|
    #     p.name    = 'John Smith'
    #     p.gender  = :M
    #     p.age     = 71
    #   end 
    #
    # You can still provide a hash for initialization purposes, and even combine
    # the two approaches if you wish.
    #
    #   person = OpenStruct.new(:name => 'John Smith', :age => 31) do |p|
    #     p.gender = :M 
    #   end
    #
    def initialize(*args) # :yield: self
      old_initialize(*args)
      yield self if block_given?
    end
  end
end

