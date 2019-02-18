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

class TypesExample < Command

  def add_sources(builder)
    builder.add_source(CommandLineChoices, :usage,
                       "Usage ruby #{$0} [options] arg")
  end

  def add_choices(builder)
    # This is how you restrict an option argument to one of a list of
    # strings.
    builder.add_choice(:a_or_b,
                       :type => ['a', 'b']) { | command_line |
      command_line.uses_option("--a-or-b CHOICE",
                               "CHOICE is either 'a' or 'b'")
    }

    # This is how you insist that an option argument be an integer
    # (in string form). If correctly formatted, the string is turned
    # into an integer. Note that the default value is a string, not
    # an integer. 
    builder.add_choice(:must_be_integer,
                       :default => '0',
                       :type => :integer) { | command_line |
      command_line.uses_option("--must-be-integer INT")
    }


    builder.add_choice(:option_list, :type => [:string]) { | command_line |
      command_line.uses_option("--option-list OPT,OPT...",
                               "Comma-separated list of options.")
    }
                       

    builder.add_choice(:arg) { | command_line |
      command_line.uses_arg
    }
  end

  def execute
    pp @user_choices
  end
end


if $0 == __FILE__
  S4tUtils.with_pleasant_exceptions do
    TypesExample.new.execute
  end
end
