#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'user-choices/choices'
require 'user-choices/command-line-choices'
require 'user-choices/command'
require 'user-choices/builder'
require 'user-choices/version'

=begin rdoc

UserChoices provides a unified interface to more than one source of
user choices: the command line, environment variables, configuration
files, and the choice to use program defaults. A typical usage defines allowable choices
within the framework of a Command object:

    class Example < Command
    
      # The sources are the various places in which the user can
      # describe her choices to the program. 
    
      def add_sources(builder)
        builder.add_source(...)
        ...
      end
    
      # Each individual choice is named with a symbol that is common
      # to all sources.
      def add_choices(builder)
        builder.add_choice(:choice, ...) { | command_line | ... }
      end

      # Immediately after recording the choices, the program can
      # add new (derived ones) or do any other once-per-program
      # initialization.
      def postprocess_user_choices
        ... @user_choices ...
      end
    
      # Perform the command.
      def execute
        ...
      end
    end
    
    ...
    CommandLineExample.new.execute
    ...

= Describing sources

Sources are described by ChoicesBuilder#add_source.

EnvironmentChoices describes the use of environment variables as sources. The following says that all environment variables beginning with "amazon_" are choices about this program.

    builder.add_source(EnvironmentChoices, :with_prefix, "amazon_")

XmlConfigFileChoices points to a configuration file with choices.

    builder.add_source(XmlConfigFileChoices, :from_file, "ms-config.xml")

CommandLineChoices uses the command line options and
arguments as a source of choices. The following gives the usage line
for the script:

    builder.add_source(CommandLineChoices, :usage,
                       "Usage ruby #{$0} [options] names...")
    
= Describing choices

The end result of the process is a hash mapping choices to chosen
values. Choices are named by symbols. They are described by
ChoicesBuilder#add_choice. Here are simple examples that
don't involve the command line.

The first just names a choice.

    builder.add_choice(:ordinary_choice)

The second gives a default value:

    builder.add_choice(:ordinary_choice,
                       :default => "eaargh")

The second gives a default value and a type. The type is used to check the value and, if appropriate, to convert the value away from a string. Note that the default is always a string regardless of the type.

    builder.add_choice(:on,
                       :default => "false",
                       :type => :boolean)

= Command line options

ChoicesBuilder#add_choice passes a
CommandLineChoices object to a block. That can be used to
describe the command line. The syntax is the same as OptionParser.

In the following, <tt>ordinary_choice</tt> can be specified with either the <tt>-o</tt> or <tt>--ordinary-choice</tt> options. The strings also appear in help messages (automatically produced from <tt>ruby script --help</tt>).

    builder.add_choice(:ordinary_choice,
                       :default => 'default') { | command_line |
      command_line.uses_option("-o", "--ordinary-choice CHOICE",
                               "CHOICE can be any string.")
    }

The command line's argument list (everything that's not an option) can
be bundled up into another choice. Here, the arguments become an array
named by <tt>:names</tt>:

    builder.add_choice(:names) { | command_line |
      command_line.uses_arglist
    }

= Using choices

Most often, choices are used within the context of a Command object. They are stored in a hash named by instance variable <tt>@user_choices</tt> (or accessor +user_choices+). 

  class AffinityTripCommand < Command
    ...
    def execute
      starting_url = @strategy.url_for(self.user_choices[:isbn])
      take_trip(starting_url, self.user_choices[:trip_steps])
    end

You can construct the hash directly with ChoicesBuilder#build. That's
not needed or used when using the Command object.

=end

module UserChoices
end

