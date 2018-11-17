#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'optparse'
require 's4t-utils'
include S4tUtils

module UserChoices # :nodoc

  # Treat the command line (including the arguments) as a source
  # of choices.
  class CommandLineChoices < ExternallyFilledHash
    
    def self.fill(&block)    # :nodoc:
      instance = new
      instance.for_these_options(&block)
      instance
    end

    # The _usage_lines_ will be used to produce the output from
    # --help (or on error). The __block__ is given the CommandLineChoices
    # object to initialize.
    def self.usage(*usage_lines, &block) 
      fill do | command_line |
        command_line.help_banner(*usage_lines)
        block.call(command_line)
      end
        
    end

    def source     # :nodoc: 
      "the command line"
    end

    def for_these_options    # :nodoc: 
      @parser = OptionParser.new
      @arglist_handler = lambda { | args |
        user_claims(args.length == 0) do
          "No arguments are allowed."
        end
      }
      yield(self)
      parse
    end

    def parse    # :nodoc: 
      begin
        remainder = @parser.parse(ARGV)
        @arglist_handler.call(remainder)
      rescue SystemExit
        raise
      rescue Exception => ex
        $stderr.puts "Error in the command line: " + ex.message
        help
      end
    end

    def help    # :nodoc: 
      $stderr.puts @parser
      exit
    end

    def help_banner(banner, *more)    # :nodoc: 
      @parser.banner = banner
      more.each do | line |
        @parser.separator(line)
      end
      @parser.separator ''
      @parser.separator 'Options:'

      @parser.on_tail("-?", "-h", "--help", "Show this message.") do
        help
      end
    end


    # What we can parse out of the command line

    # Describes how a particular _choice_ is represented on the
    # command line. The _args_ are passed to OptionParser. Each arg
    # will either describe one variant of option (such as <tt>"-s"</tt>
    # or <tt>"--show VALUE"</tt>) or is a line of help text about
    # the option (multiple lines are allowed).
    #
    # If the option takes an array of values, separate the values by commas:
    # --files a,b,c
    # There's currently no way to escape a comma and no cleverness about
    # quotes. 
    def uses_option(choice, *args)
      external_name = extract_external_name(args)
      @parser.on(*args) do | value |
        record_name_key_and_value(external_name, choice, value)
      end
    end

    # A switch is an option that doesn't take a value. A switch
    # described as <tt>"--switch"</tt> has these effects:
    # * If it is not given, the _choice_ is the default value
    #   or is not present in the hash that holds all the choices.
    # * If it is given as <tt>--switch</tt>, the _choice_ has the
    #   value <tt>"true"</tt>. (If the _choice_ was described in
    #   ChoicesBuilder#add_choice as having a <tt>:type => :boolean</tt>,
    #   that value is converted from a string to +true+.)
    # * If it is given as <tt>--no-switch</tt>, the _choice_ has the
    #   value <tt>"false"</tt>.
    def uses_switch(choice, *args)
      external_name = extract_external_name(args)
      args = change_name_to_switch(external_name, args)
      @parser.on(*args) do | value |
        record_name_key_and_value(external_name, choice, value.to_s)
      end
    end


    # Bundle up all non-option and non-switch arguments into an
    # array of strings indexed by _choice_. _arglist_arity_ describes
    # how many arguments there can be. It may be a range or an exact count. 
    def uses_arglist(choice, arglist_arity = (0..100000000))
      @arglist_handler = lambda { | arglist |
        self.class.claim_arglist_arity_OK(arglist.length, arglist_arity)
        self[choice] = arglist
      }
    end

    # By default, the single argument required argument is turned into
    # a string indexed by _choice_. If the _arglist_arity_ is
    # <tt>(0..1)</tt>, the argument is optional. If not given, its
    # _choice_ will have no value in the hash.
    #
    # An _arglist_arity_ other than <tt>1</tt> or <tt>(0..1)</tt> is meaningless. 
    #
    # See uses_optional_arg for a more convenient way to describe an
    # optional argument.
    def uses_arg(choice, arglist_arity = 1)
      # if they call arg with an arity other than 1 or 0..1, they
      # deserve whatever (undefined thing) they get.
      @arglist_handler = lambda { | arglist |
        self.class.claim_arglist_arity_OK(arglist.length, arglist_arity)
        self[choice] = arglist[0] if arglist.length == 1
      }
    end

    # If a single argument is present, it (as a string) is the value of
    # _choice_. If no argument is present, _choice_ has no value.
    # Any other case is an error. 
    def uses_optional_arg(choice)
      uses_arg(choice, 0..1)
    end

    private

    def self.claim_arglist_arity_OK(length, arglist_arity)
      user_claims(arglist_arity === length) {
        plural = length==1 ? '' : 's'
        case arglist_arity
        when Integer
          expected = arglist_arity.to_s
        when Range
          expected = arglist_arity.in_words
        end
        "#{length} argument#{plural} given, #{expected} expected."
      }
    end

    def extract_external_name(option_descriptions)
      option_descriptions.each do | desc |
        break $1 if /^--([\w-]+)/ =~ desc
      end
    end

    def change_name_to_switch(name, option_descriptions)
      option_descriptions.collect do | desc |
        /^--/ =~ desc ? "--[no-]#{name}" : desc
      end
    end        

    def record_external_name(choice, external_name)
      super(choice, "--" + external_name)
    end
  end


  # Process command-line choices according to POSIX rules. Consider
  #
  # ruby copy.rb file1 --odd-file-name
  #
  # Ordinarily, that's permuted so that --odd-file-name is expected to
  # be an option or switch, not an argument. One way to make
  # CommandLineChoices parsing treat it as an argument is to use a -- to
  # signal the end of option parsing:
  #
  # ruby copy.rb -- file1 --odd-file-name
  #
  # Another is to rely on the user to set environment variable
  # POSIXLY_CORRECT.
  #
  # Since both of those require the user to do something, they're error-prone. 
  #
  # Another way is to use this class, which obeys POSIX-standard rules. Under
  # those rules, the first word on the command line that does not begin with
  # a dash marks the end of all options. In that case, the first command line
  # above would parse into two arguments and no options.
  class PosixCommandLineChoices < CommandLineChoices
    def parse
      begin
        already_set = ENV.include?('POSIXLY_CORRECT')
        ENV['POSIXLY_CORRECT'] = 'true' unless already_set
        super
      ensure
        ENV.delete('POSIXLY_CORRECT') unless already_set
      end
    end
  end
end



