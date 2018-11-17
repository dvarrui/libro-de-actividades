#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# This file is 'required' by all files that implement standard class
# extensions as part of the "Ruby/Extensions" project.
#
# The "Extensions" project requires 1.8.0 or greater to run, as it is too
# much hassle at the moment to consider supporting older versions.  That may
# one day be implemented if demand is there.  One option would be to require
# "shim", so that we can assume all 1.8 library methods are implemented.
#
# This file is only of interest to developers of the package, so no detailed
# documentation is included here.  However, by way of introduction, this is what
# it's all about.  Each method that is implemented as part of this package is
# done so through a framework implemented in this file.  Take the following
# simple example:
#
#   ExtensionsProject.implement(Integer, :even?, :instance) do
#     class Integer
#       #
#       # RDoc comments.
#       # 
#       def even?
#         self % 2 == 0
#       end
#     end
#   end
#
# This purposes of this are as follows:
# - if the intended method (in this case IO.write) is already defined,
#   we don't want to overwrite it (we issue a warning and move on)
# - if the intended method is _not_ implemented as a result of the block,
#   we have not done as we said, and an error is raised
# - the ExtensionsProject class gathers information on which methods have
#   been implemented, making for a very handy command-line reference (+rbxtm+)
#
# The <tt>ExtensionsProject.implement</tt> method is responsible for ensuring
# these are so.  It gives us documentation, and some assurance that the
# extensions are doing what we say they are doing.
#

# :enddoc:

#
# For what reason does Ruby define Module#methods, Module#instance_methods,
# and Module#method_defined?, but not Module#instance_method_defined? ?
#
# No matter, extending standard classes is the name of the game here.
#
class Module
  if Module.method_defined?(:instance_method_defined?)
    STDERR.puts "Warning: Module#instance_method_defined? already defined; not overwriting"
  else
    def instance_method_defined?(_method)
      instance_methods(true).find { |m| m == _method.to_s }
    end
  end

  if Module.method_defined?(:module_method_defined?)
    STDERR.puts "Warning: Module#module_method_defined? already defined; not overwriting"
  else
    def module_method_defined?(_method)
      singleton_methods(true).find { |m| m == _method.to_s }
    end
  end
end


class ExtensionsProject

  class << ExtensionsProject
    @@extension_methods = []

    #
    # The list of methods implemented in this project.
    #
    def extension_methods
      @@extension_methods
    end

    #
    # Return the name of the project.  To be used in error messages, etc., for
    # consistency.
    #
    def project_name
      "Ruby/Extensions"
    end

    #
    # Wraps around the implementation of a method, emitting a warning if the
    # method is already defined.  Returns true to indicate - false to indicate
    # failure (i.e. method is already defined).  Raises an error if the
    # specified method is not actually implemented by the block.
    #
    def implement(_module, _method, _type=:instance)
      raise "Internal error: #{__FILE__}:#{__LINE__}" unless
        _module.is_a? Module and
        _method.is_a? Symbol and
        _type == :instance or _type == :class or _type == :module

      fullname = _module.to_s + string_rep(_type) + _method.to_s

      if _defined?(_module, _method, _type)
        STDERR.puts "#{project_name}: #{fullname} is already defined; not overwriting"
        return false
      else
        yield # Perform the block; presumably a method implementation.
        if _method == :initialize and _type == :instance
          # Special case; we can't verify this.
          @@extension_methods<< "#{_module}::new"
        else
          unless _defined?(_module, _method, _type)
            raise "#{project_name}: internal error: was supposed to implement " +
              "#{fullname}, but it didn't!"
          end
          @@extension_methods << fullname
        end
        return true
      end
    end


    # See whether the given module implements the given method, taking account
    # of the type (class/instance) required.
    def _defined?(_module, _method, _type)
      case _type
      when :instance
        _module.instance_method_defined?(_method) # See definition above.
      when :class, :module
        _module.module_method_defined?(_method)   # See definition above.
      end
    end
    private :_defined?


    # Return the string representation of the given method type.
    def string_rep(method_type)
      case method_type
      when :instance then "#"
      when :class    then "."
      when :module   then "."
      else
        nil
      end
    end
    private :string_rep
  end
end    # class ExtensionsProject


if VERSION < "1.8.0"
  raise "#{ExtensionsProject.project_name} requires Ruby 1.8.0 at least (for now)"
end

