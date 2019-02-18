#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/hash.rb
#
# Adds methods to the builtin Hash class. 
#

require "extensions/_base"

#
# * Hash#select!
#
ExtensionsProject.implement(Hash, :select!) do
  class Hash
    #
    # In-place version of Hash#select.  (Counterpart to, and opposite of, the
    # built-in #reject!)
    #
    def select!
      reject! { |k,v| not yield(k,v) }
    end
  end
end
