#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 's4t-utils'
include S4tUtils


module UserChoices

  # This class accepts a series of source and choice descriptions
  # and then builds a hash-like object that describes all the choices
  # a user has made before (or while) invoking a script.
  class ChoicesBuilder

    #--
    # DANGER WILL ROBINSON!
    #
    # There's a kludge here. Alone among the kinds of Choices, 
    # the CommandLineChoices takes a block to initialize itself.
    # Each choice produces a piece of that block. They're all
    # executed when build() is called. The blocks are passed to
    # each Choice in the ComposedChoices, relying on the fact
    # that only the CommandLineChoices will pick it up. 
    #++

    def initialize
      @defaults = {}
      @checks = {}
      @sources = []
      @command_line_blocks = []
    end
    
    # Add the choice named _choice_, a symbol. _args_ is a keyword
    # argument: 
    # * <tt>:default</tt> takes a *string* value that is the default value of the _choice_. 
    # * <tt>:type</tt> can be given an array of valid string values. These are
    #   checked.
    # * <tt>:type</tt> can also be given <tt>:integer</tt>. The value is cast into
    #   an integer. If that's impossible, an exception is raised. 
    # * <tt>:type</tt> can also be given <tt>:boolean</tt>. The value is converted into
    #   +true+ or +false+ (or an exception is raised).
    # * <tt>:type</tt> can also be given <tt>[:string]</tt>. The value
    #   will be an array of strings. For example, "--value a,b,c" will
    #   produce ['a', 'b', 'c'].
    # 
    # The _block_ is passed a CommandLineChoices object. It's expected
    # to describe the command line.
    def add_choice(choice, args={}, &block)
      update_hash_when_explicit_value(@defaults, choice, args[:default])
      update_hash_when_explicit_value(@checks, choice, args[:type])
      if block
        @command_line_blocks << lambda { | source |
          block.call(ArgForwarder.new(source, choice))
        }
      end
    end

    # This adds a source of choices. The _source_ is a class like
    # CommandLineChoices. The _factory_method_ is a class method that's
    # called to create an instance of the class. The _args_ are passed
    # to the _factory_method_. 
    def add_source(source, factory_method, *args)
      @sources << {:source => source, :factory_method => factory_method, :args => args}
    end

    # Once sources and choices have been described, this builds and
    # returns a hash-like object indexed by the choices.
    def build
      built_sources = @sources.collect do | source |
        source[:source].send(source[:factory_method], *source[:args]) do | source | 
          @command_line_blocks.each { | block | block.call(source) }
        end
      end
      built_sources << DefaultChoices.fill(@defaults)
      ComposedChoices.fill(*built_sources) { | c | 
        c.check_values(@checks)
        c.update_values(@checks)
      }
    end
    
    private
    
    def update_hash_when_explicit_value(hash, key, value)
      hash[key] = value if value
    end
  

  end

end
