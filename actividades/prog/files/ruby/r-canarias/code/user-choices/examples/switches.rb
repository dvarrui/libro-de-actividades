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

class SwitchExample < Command

  def add_sources(builder)
    builder.add_source(CommandLineChoices, :usage,
                       "Usage ruby #{$0} [options] args...",
                       "There may be 2-4 arguments.")
    
  end

  # Switches are slightly different than options. (The difference is
  # in how they're invoked, as either --switch or --no-switch.) Almost
  # certainly, you want the switch to be of type :boolean and have a
  # default.  Note that the default - like all
  # pre-type-conversion-values - should be a string.
  def add_choices(builder)
    builder.add_choice(:switch,
                       :default => 'false',
                       :type => :boolean) { | command_line |
      command_line.uses_switch("--switch", "-s")
    }

    # You specify a range of allowable arguments with Ruby Ranges.
    builder.add_choice(:args) { | command_line |
      command_line.uses_arglist(2..4)
    }
  end

  def execute
    pp @user_choices
  end
end


if $0 == __FILE__
  S4tUtils.with_pleasant_exceptions do
    SwitchExample.new.execute
  end
end
