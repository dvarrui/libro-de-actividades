#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/class.rb
#
# Adds methods to the builtin Class class. 
#

require "extensions/_base"

ExtensionsProject.implement(Class, :autoinit) do
  class Class
    #
    # A shorthand for the common chore of assigning initialize's parameters to
    # instance variables.  For example:
    #  
    #   class Circle
    #
    #     attr_reader :radius, :location, :area
    #
    #     autoinit(:radius, :location) do
    #       @area = Math::PI * @radius ** 2
    #     end
    #
    #   end
    #
    # A TypeError is raised unless all the arguments to +autoinit+ are strings
    # or symbols.
    #
    #--
    # Taken from ruby-talk:11668, by Avi Bryant.
    def autoinit(*args, &block) # :yield:
      unless args.all? { |a| Symbol === a or String === a }
        raise TypeError, "All arguments must be symbols or strings"
      end
      block = proc {} if block.nil?
      define_method(:__init_proc) { block }
      params = args.join(", ")
      vars = args.map { |a| "@#{a}" }.join(", ")

      code = %{
          def initialize(#{params})
            #{vars} = #{params}
            instance_eval(&__init_proc)
          end
        }
      class_eval code
    end
  end
end

