#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/enumerable.rb
#
# Adds methods to the builtin Enumerable module. 
#

require "extensions/_base"

#
# * Enumerable#build_hash
#
ExtensionsProject.implement(Enumerable, :build_hash) do
  module Enumerable
    #
    # Like <tt>#map</tt>/<tt>#collect</tt>, but it generates a Hash.  The block
    # is expected to return two values: the key and the value for the new hash.
    #   numbers  = (1..3)
    #   squares  = numbers.build_hash { |n| [n, n*n] }   # 1=>1, 2=>4, 3=>9
    #   sq_roots = numbers.build_hash { |n| [n*n, n] }   # 1=>1, 4=>2, 9=>3
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

  # There was a bug in Hash which causes the above code to issue a warning when
  # used with a Hash.  That was fixed on 2003-10-24.
  if RUBY_RELEASE_DATE < "2003-10-25"
    class Hash #:nodoc:
      def build_hash
        result = {}
        self.each_pair do |k, v|
          key, value = yield(k, v)
          result[key] = value
        end
        result
      end
    end
  end
end


#
# Enumerable#mapf
#
ExtensionsProject.implement(Enumerable, :mapf) do
  module Enumerable
    #
    # "map function"
    #   enum.mapf(:x)
    # is short for
    #   enum.map { |elt| elt.x }
    #
    def mapf(message)
      self.map { |elt| elt.send(message) }
    end
  end
end


#
# Enumerable#collectf
#
ExtensionsProject.implement(Enumerable, :collectf) do
  module Enumerable
    alias collectf mapf
  end
end


#
# * Enumerable#includes?
#
ExtensionsProject.implement(Enumerable, :includes?) do
  module Enumerable
    alias includes? include?
  end
end


#
# * Enumerable#contains?
#
ExtensionsProject.implement(Enumerable, :contains?) do
  module Enumerable
    alias contains? include?
  end
end


#
# * Enumerable#has?
#
ExtensionsProject.implement(Enumerable, :has?) do
  module Enumerable
    alias has? include?
  end
end


#
# * Enumerable#map_with_index
#
ExtensionsProject.implement(Enumerable, :map_with_index) do
  module Enumerable
    #
    # Same as Enumerable#map, but the index is yielded as well.  See
    # Enumerable#each_with_index.
    #   puts files.map_with_index { |fn, idx| "#{idx}. #{fn}" }
    #   print "Please select a file (0-#{files.size}): "
    #
    def map_with_index
      result = []
      self.each_with_index do |elt, idx|
        result << yield(elt, idx)
      end
      result
    end
  end
end


#
# * Enumerable#collect_with_index
#
ExtensionsProject.implement(Enumerable, :collect_with_index) do
  module Enumerable
    alias  collect_with_index  map_with_index
  end
end


#
# * Enumerable#partition_by
#
ExtensionsProject.implement(Enumerable, :partition_by) do
  module Enumerable
    #
    # See Enumerable#partition for the background.  #partition_by is best
    # explained by example.
    #
    #   (1..5).partition_by { |n| n % 3 } 
    #        # -> { 0 => [3], 1 => [1, 4], 2 => [2,5] } 
    #
    #   ["I had", 1, "dollar and", 50, "cents"].partition_by { |e| e.class }
    #        # -> { String => ["I had","dollar and","cents"], Fixnum => [1,50] } 
    #
    # #partition_by is used to group items in a collection by something they
    # have in common.  The common factor is the key in the resulting hash, the
    # array of like elements is the value.
    #
    def partition_by
      result = {}
      self.each do |e|
        value = yield e
        (result[value] ||= []) << e
      end
      result
    end
  end
end


#
# * Enumerable#none?
#
ExtensionsProject.implement(Enumerable, :none?) do
  module Enumerable
    #
    # Enumerable#none? is the logical opposite of the builtin method Enumerable#any?.  It
    # returns +true+ if and only if _none_ of the elements in the collection satisfy the
    # predicate.
    #
    # If no predicate is provided, Enumerable#none? returns +true+ if and only if _none_ of the
    # elements have a true value (i.e. not +nil+ or +false+).
    #
    #   [].none?                      # true
    #   [nil].none?                   # true
    #   [5,8,9].none?                 # false
    #   (1...10).none? { |n| n < 0 }  # true
    #   (1...10).none? { |n| n > 0 }  # false
    #
    def none?  # :yield: e
      if block_given?
        not self.any? { |e| yield e }
      else
        not self.any?
      end
    end
  end
end


#
# * Enumerable#one?
#
ExtensionsProject.implement(Enumerable, :one?) do
  module Enumerable
    #
    # Enumerable#one? returns +true+ if and only if <em>exactly one</em> element in the
    # collection satisfies the given predicate.
    #
    # If no predicate is provided, Enumerable#one? returns +true+ if and only if <em>exactly
    # one</em> element has a true value (i.e. not +nil+ or +false+).
    #
    #   [].one?                      # false
    #   [nil].one?                   # false
    #   [5].one?                     # true
    #   [5,8,9].one?                 # false
    #   (1...10).one? { |n| n == 5 } # true
    #   (1...10).one? { |n| n < 5 }  # false
    #
    def one?  # :yield: e
      matches = 0
      if block_given?
        self.each do |e|
          if yield(e)
            matches += 1
            return false if matches > 1
          end
        end
        return (matches == 1)
      else
        one? { |e| e }
      end
    end
  end
end


#
# * Object.in?
# This has special treatment: it's included here and in object.rb, so we don't
# want a warning if it's alredy defined.
#
unless Object.method_defined?(:in?)
  ExtensionsProject.implement(Object, :in?) do
    class Object
      def in?(enumerable) # :nodoc: It's documented in object.rb.
        enumerable.include?(self)
      end
    end
  end
end

