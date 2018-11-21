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
                       "Usage ruby #{$0} [options] infile outfile")
    
  end

  def add_choices(builder)
    builder.add_choice(:args) { | command_line |
      command_line.uses_arglist(2)
    }
  end

  # postprocess_user_choices gives the program the opportunity to
  # do something about choices immediately after they are made. This method
  # runs only once per invocation; whereas the execute() method can 
  # execute several times. This method will often set instance variables. 
  def postprocess_user_choices
    @user_choices[:infile] = @user_choices[:args][0]
    @user_choices[:outfile] = @user_choices[:args][1]
  end

  def execute
    pp @user_choices
  end
end


if $0 == __FILE__
  S4tUtils.with_pleasant_exceptions do
    ArgNotingCommand.new.execute
  end
end
