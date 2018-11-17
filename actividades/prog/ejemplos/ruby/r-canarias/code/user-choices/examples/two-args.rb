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

class TwoArgExample < Command

  def add_sources(builder)
    builder.add_source(CommandLineChoices, :usage,
                       "Usage ruby #{$0} [options] infile outfile")
    
  end

  def add_choices(builder)
    # You can specify an exact number of arguments, rather than a range.
    builder.add_choice(:args) { | command_line |
      command_line.uses_arglist(2)
    }
  end

  def execute
    pp @user_choices
  end
end


if $0 == __FILE__
  S4tUtils.with_pleasant_exceptions do
    TwoArgExample.new.execute
  end
end
