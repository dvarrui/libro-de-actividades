#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
### The following adjusts the load path so that the correct version of
### a self-contained package is found, no matter where the script is
### run from. 
require 'pathname'
$:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
require 'user-choices/third-party/s4t-utils/load-path-auto-adjuster'


require 'pp'
require 'user-choices'
include UserChoices

class ArgNotingCommand < Command

  def add_sources(builder)
    builder.add_source(CommandLineChoices, :usage,
                       "Usage ruby #{$0} [options] [name]")
  end

  def add_choices(builder)
    # This example shows how you can specify a default value for an
    # option.
    builder.add_choice(:choice,
                       :default => 'default') { | command_line |
      command_line.uses_option("-c", "--choice CHOICE",
                               "CHOICE can be any string.")
    }

    # uses_optional_arg allows either zero or one arguments. If an
    # argument is given, it is directly the value of user_choices[key]
    # (rather than an array).
    builder.add_choice(:name) { | command_line |
      command_line.uses_optional_arg
    }
  end

  # Perform the command.
  def execute
    pp @user_choices
  end
end


if $0 == __FILE__
  S4tUtils.with_pleasant_exceptions do
    ArgNotingCommand.new.execute
  end
end
