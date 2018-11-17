#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module UserChoices

  # A template class. Subclasses describe all the choices a user may
  # make to affect the execution of the command, and the sources for
  # those choices (such as the command line).
  class Command

    attr_reader :user_choices

    def initialize
      builder = ChoicesBuilder.new
      add_sources(builder)
      add_choices(builder)
      @user_choices = builder.build
      postprocess_user_choices
    end

    # Add sources such as EnvironmentChoices, XmlConfigFileChoices,
    # and CommandLineChoices to the ChoicesBuilder.
    #
    # Must be defined in a subclass.
    def add_sources(builder); subclass_responsibility; end
    
    # Add choices to the ChoicesBuilder. A choice is a symbol that ends up in the
    # @user_choices hash. It may have a :default value and a :type, as well
    # as an OptionParser-style description of command-line arguments.
    #
    # Must be defined in a subclass.
    def add_choices(builder); subclass_responsibility; end

    # After choices from all sources are collected into @user_choices, this
    # method is called to do any postprocessing. Does nothing unless
    # overridden. 
    def postprocess_user_choices
    end

    # Execute the command, using @user_choices for parameterization. 
    #
    # Must be defined in a subclass.
    def execute; subclass_responsibility; end
  end
end
