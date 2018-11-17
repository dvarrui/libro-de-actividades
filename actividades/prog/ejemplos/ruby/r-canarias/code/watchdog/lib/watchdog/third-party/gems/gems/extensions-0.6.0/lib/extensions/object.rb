#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# == extensions/object.rb
#
# Adds methods to the builtin Object class.
#

require 'extensions/_base'


#
# Object#singleton_class
#
ExtensionsProject.implement(Object, :singleton_class) do
  class Object
    #
    # Returns the singleton class associated with this object.  How useful this
    # is I don't know, but it's an idiom that has appeared on ruby-talk several
    # times.
    #
    def singleton_class
      class << self
        self
      end
    end
  end
end


#
# * Object.in?
# This has special treatment: it's included here and in enumerable.rb, so we don't
# want a warning if it's already defined.
#
unless Object.method_defined?(:in?)
  ExtensionsProject.implement(Object, :in?) do
    class Object
      #
      # Test this object for inclusion in a given collection.
      #
      #   45.in? (1...100)             => true
      #
      # This method is contained in <tt>object.rb</tt> and
      # <tt>enumerable.rb</tt>, because it logically belongs in both.
      #
      def in?(enumerable)
        enumerable.include?(self)
      end
    end
  end
end


#
# * Object.not_nil?
#
ExtensionsProject.implement(Object, :not_nil?) do
  class Object
    #
    # The opposite of <tt>#nil?</tt>.
    #
    #   "hello".not_nil?      # -> true
    #   nil.not_nil?          # -> false 
    #
    def not_nil?
      not self.nil?
    end
  end
end


#
# * Object.non_nil?
#
ExtensionsProject.implement(Object, :non_nil?) do
  class Object
    #
    # The opposite of <tt>#nil?</tt>.
    #
    #   "hello".non_nil?      # -> true
    #   nil.non_nil?          # -> false 
    #
    def non_nil?
      not self.nil?
    end
  end
end


#
# Object#pp_s
#
ExtensionsProject.implement(Object, :pp_s) do
  require 'pp'
  require 'stringio'
  class Object
    #
    # Returns a pretty-printed string of the object.  Requires libraries +pp+ and
    # +stringio+ from the Ruby standard library.
    #
    # The following code pretty-prints an object (much like +p+ plain-prints an
    # object):
    #
    #   pp object
    #
    # The following code captures the pretty-printing in +str+ instead of
    # sending it to +STDOUT+.
    #
    #   str = object.pp_s 
    #
    def pp_s
      pps = StringIO.new
      PP.pp(self, pps)
      pps.string
    end
  end
end

#
# Object#pp_s
#
ExtensionsProject.implement(Object, :define_method) do
  class Object
    #
    # Defines a singleton method on the object.  For example, the following are
    # equivalent (assume <tt>o = Object.new</tt>):
    #
    #   def o.add(x, y)
    #     x + y
    #   end
    #
    #   o.define_method(:add) do |x, y|
    #     x + y
    #   end
    #
    # The difference is that with <tt>define_method</tt>, you can use variables
    # local to the _current_ scope.
    #
    #   x = 5 
    #   o.define_method(:add_x) do |n|
    #     x + n
    #   end
    #   o.add_x(11)          # -> 16
    #
    # You can't define such a method as <tt>add_x</tt> above with <tt>def
    # o.add_x; x + n; end</tt>, as +def+ introduces a new scope.
    #
    # There are three ways to provide the body of the method: with a block (as
    # in both examples above), or with a +Proc+ or +Method+ object.  See the
    # built-in method <tt>Module#define_method</tt> for details.
    #
    # (This method is exactly equivalent to calling <tt>Module#define_method</tt>
    # in the scope of the singleton class of the object.) 
    #
    def define_method(*args, &block)
      singleton_class = class << self; self; end
      singleton_class.module_eval do
        define_method(*args, &block)
      end
    end
  end
end

