#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/module.rb
#
# Adds methods to the builtin Module class. 
#

require "extensions/_base"

ExtensionsProject.implement(Module, :deep_const_get) do
  class Module
    #
    # Recursively dereferences constants separated by "<tt>::</tt>".
    #
    # _const_ is a Symbol or responds to #to_str, for compatibility with the builtin method
    # <tt>Module#const_get</tt>.
    #
    #   Object.const_get("String")                    # -> String
    #   Object.const_get(:String)                     # -> String
    #
    #   Object.deep_const_get("String")               # -> String
    #   Object.deep_const_get(:String)                # -> String
    #
    #   Object.deep_const_get("Process::Sys")         # -> Process::Sys
    #   Object.deep_const_get("Regexp::IGNORECASE")   # -> 1
    #   Object.deep_const_get("Regexp::MULTILINE")    # -> 4
    #
    #   require 'test/unit'
    #   Test.deep_const_get("Unit::Assertions")       # -> Test::Unit::Assertions
    #   Test.deep_const_get("::Test::Unit")           # -> Test::Unit
    #
    # For resolving classes or modules based on their name, see Module.by_name, which uses this
    # method.
    #
    def deep_const_get(const)
      if Symbol === const
        const = const.to_s
      else
        const = const.to_str.dup
      end
      if const.sub!(/^::/, '')
        base = Object
      else
        base = self
      end
      const.split(/::/).inject(base) { |mod, name| mod.const_get(name) }
    end
  end
end


ExtensionsProject.implement(Module, :by_name, :class) do
  class Module
    #
    # <em>Note: the following documentation uses "class" because it's more common, but it
    # applies to modules as well.</em>
    #
    # Given the _name_ of a class, returns the class itself (i.e. instance of Class).  The
    # dereferencing starts at Object.  That is,
    #
    #   Class.by_name("String")
    #
    # is equivalent to
    #
    #   Object.get_const("String")
    #
    # The parameter _name_ is expected to be a Symbol or String, or at least to respond to
    # <tt>to_str</tt>.
    #
    # An ArgumentError is raised if _name_ does not correspond to an existing class.  If _name_
    # is not even a valid class name, the error you'll get is not defined.
    #
    # Examples:
    #
    #   Class.by_name("String")             # -> String
    #   Class.by_name("::String")           # -> String
    #   Class.by_name("Process::Sys")       # -> Process::Sys
    #   Class.by_name("GorillaZ")           # -> (ArgumentError)
    #
    #   Class.by_name("Enumerable")         # -> Enumerable
    #   Module.by_name("Enumerable")        # -> Enumerable
    #
    def Module.by_name(name)
      if Symbol === name
        name = name.to_s
      else
        name = name.to_str
      end
      result = Object.deep_const_get(name)
      if result.is_a? Module
        return result
      else
        raise ArgumentError, "#{name} is not a class or module"
      end
    end
  end
end


ExtensionsProject.implement(Module, :basename) do
  class Module
    #
    # Returns the immediate name of the class/module, stripped of any containing classes or
    # modules.  Compare Ruby's builtin methods <tt>Module#name</tt> and <tt>File.basename</tt>.
    #
    #   Process::Sys.name           # -> "Process::Sys"
    #   Process::Sys.basename       # -> "Sys"
    #   String.basename             # -> "String"
    #
    def basename
      self.name.sub(/^.*::/, '')
    end
  end
end
