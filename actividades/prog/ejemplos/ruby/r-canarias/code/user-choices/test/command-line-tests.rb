#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
load "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
require 'builder'
require 'user-choices'
include S4tUtils

              ### Handling of options with arguments ###

class OPTIONS_CommandLineTests < Test::Unit::TestCase
  include UserChoices

  def test_options_can_be_given_in_the_command_line
    with_command_args('--given-option value') {
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:given_option, "--given-option VALUE")
      end

      assert_true(hash.has_key?(:given_option))
      assert_equal("value", hash[:given_option])

      assert_false(hash.has_key?(:unspecified_option))
      assert_equal(nil, hash[:unspecified_option])
    }
  end

  def test_the_specification_can_describe_options_that_are_not_given
    # They're really /optional/.
    with_command_args('') { 
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:unused_option, "--unused-option VALUE")
      end
      assert_false(hash.has_key?(:unused_option))
      assert_equal(nil, hash[:unused_option])
    }
  end

  def test_options_can_have_one_letter_abbreviations
    with_command_args('-s s-value --option=option-value') {
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:option, "-o", "--option=VALUE")
        c.uses_option(:something, "-s", "--something=VALUE")
      end
      assert_equal("s-value", hash[:something])
      assert_equal("option-value", hash[:option])
    }
  end



  def test_command_line_list_of_possible_values_checking
    with_command_args("-n true") do
      assert_raises_with_matching_message(StandardError,
           %r{Error in the command line: 'true' is not a valid value for '--north-west'}) {
        choices = CommandLineChoices.fill do | c |
          c.uses_option(:north_west, "-n", "--north-west=VALUE")
        end
        choices.check_values(:north_west => ['low', 'high'])
      }
    end
  end

  def test_command_line_integer_value_checking
    with_command_args("--day-count-max=2d3") do
      assert_raises_with_matching_message(StandardError,
            /Error in the command line: '--day-count-max' requires an integer value, and '2d3' doesn't look like one/) {
        choices = CommandLineChoices.fill do | c |
          c.uses_option(:day_count_max, "--day-count-max=VALUE")
        end
        choices.check_values(:day_count_max => :integer)
      }
    end
  end


  def test_integer_value_updating
    with_command_args("--day-count-max 23") do
      choices = CommandLineChoices.fill do | c |
        c.uses_option(:day_count_max, "--day-count-max=VALUE")
      end
      choices.update_values(:day_count_max => :integer)
      assert_equal(23, choices[:day_count_max])
    end
  end

  def test_array_value_updating_with_proper_multivalue_declaration
    with_command_args("--hosts localhost,foo.com") do
      choices = CommandLineChoices.fill do | c |
        c.uses_option(:hosts, "--hosts HOST,HOST")
      end
      choices.update_values(:hosts => [:string])
      assert_equal(['localhost', 'foo.com'],
                   choices[:hosts])
    end
  end

  def test_array_value_updating_without_proper_multivalue_declaration
    with_command_args("--hosts localhost,foo.com") do
      choices = CommandLineChoices.fill do | c |
        c.uses_option(:hosts, "--hosts HOSTS...")
      end
      choices.update_values(:hosts => [:string])
      assert_equal(['localhost', 'foo.com'],
                   choices[:hosts])
    end
  end

end



                       ### Boolean switches ###

# Note that switches are string-valued for consistency with
# other sources (like environment variables).
class SWITCHES_CommandLineTest < Test::Unit::TestCase
  include UserChoices

  def test_boolean_switches_are_accepted
    with_command_args("--c") do
      choices = CommandLineChoices.fill do | c |
        c.uses_switch(:csv, "-c", "--csv")
      end
      assert_equal("true", choices[:csv])
    end
  end

  def test_unmentioned_switches_have_no_value
    with_command_args("") do
      choices = CommandLineChoices.fill do | c |
        c.uses_switch(:csv, "-c", "--csv")
      end
      assert_false(choices.has_key?(:csv))
    end
  end

  def test_switches_can_be_explicitly_false
    with_command_args("--no-csv") do
      choices = CommandLineChoices.fill do | c |
        c.uses_switch(:csv, "-c", "--csv")
      end
      assert_equal("false", choices[:csv])
    end
  end
end


                        ### Argument Lists ###

# Arguments lists are treated as another option.
class ARGLISTS_CommandLineTest < Test::Unit::TestCase
  include UserChoices

  def test_arglist_after_options_can_turned_into_an_option
    with_command_args("--unused unused arg1 arg2") {
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:unused, "--unused VALUE")  # just for grins
        c.uses_arglist(:args)
      end
      assert_true(hash.has_key?(:args))
      assert_equal(["arg1", "arg2"], hash[:args])
    }
  end

  def test_arglist_can_describe_allowable_number_of_arguments
    with_command_args("--unused unused arg1 arg2") {
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:unused, "--unused VALUE")  # just for grins
        c.uses_arglist(:args, 2)
      end
      assert_true(hash.has_key?(:args))
      assert_equal(["arg1", "arg2"], hash[:args])
    }
  end

  def test_error_if_exact_arglist_number_is_wrong
    with_command_args("arg1 arg2") {
      output = capturing_stderr do
        assert_wants_to_exit do
          hash = CommandLineChoices.fill do | c |
            c.uses_arglist(:args, 3)
          end
        end
      end

      assert_match(/Error in the command line:.*2 arguments given, 3 expected./, output)
    }
  end

  def test_arglist_arity_can_be_a_range
    with_command_args("arg1 arg2") {
      hash = CommandLineChoices.fill do | c |
        c.uses_arglist(:args, 1..3)
      end
      assert_true(hash.has_key?(:args))
      assert_equal(["arg1", "arg2"], hash[:args])
    }
  end


  def test_error_if_arglist_does_not_match_range
    with_command_args("arg1 arg2") {
      output = capturing_stderr do
        assert_wants_to_exit do
          hash = CommandLineChoices.fill do | c |
            c.uses_arglist(:args, 3..6)
          end
        end
      end

      assert_match(/Error in the command line:.*2 arguments given, 3 to 6 expected./, output)
    }
  end
end

                    ### Option-Handling Style ###

class OPTION_STYLE_CommandLineTest < Test::Unit::TestCase
  include UserChoices

  Arglist_def = proc { | c | 
    c.uses_switch(:switch, "--switch")
    c.uses_arglist(:args)
  }

  def test_default_style_is_permutation
    with_command_args('3 --switch 5') {
      choices = CommandLineChoices.fill(&Arglist_def)
      assert_equal('true', choices[:switch])
      assert_equal(['3', '5'], choices[:args])
    }
  end

  def test_subclass_allows_all_options_before_arguments
    with_command_args('3 --switch 5') { 
      choices = PosixCommandLineChoices.fill(&Arglist_def)
      assert_equal(nil, choices[:switch])
      assert_equal(['3', '--switch', '5'], choices[:args])
    }
  end

  def test_choosing_posix_parsing_does_not_override_environment_variable
    with_environment_vars('POSIXLY_CORRECT' => 'hello') do
      with_command_args('3 --switch 5') { 
        choices = PosixCommandLineChoices.fill(&Arglist_def)
        assert_equal('hello', ENV['POSIXLY_CORRECT'])
      }
    end
  end
    
end

                        ### Error Handling ###

# Additional commandline-specific error checking.
class ERROR_CommandLineTest < Test::Unit::TestCase
  include UserChoices

  def test_invalid_option_produces_error_message_and_exit
    with_command_args('--doofus 3') {
      output = capturing_stderr do
        assert_wants_to_exit do
          choices = CommandLineChoices.fill do | c |
            c.uses_option(:doofus, "--option VALUE")
          end
        end
      end

      assert_match(/invalid option.*doofus/, output)
    }
  end

  def test_error_is_identified_as_coming_from_the_command_line
    with_command_args('--doofus') {
      output = capturing_stderr do
        assert_wants_to_exit do
          choices = CommandLineChoices.fill do | c |
            c.uses_option(:doofus, "--doofus VALUE")
          end
        end
      end
      
      assert_match(/Error in the command line:.*missing argument.*doofus/, output)
    }
  end

  def test_errors_cause_usage_style_output
    with_command_args('wanted --wanted') {
      output = capturing_stderr do
        assert_wants_to_exit do
          choices = CommandLineChoices.fill { | c |
            default_isbn = "343"
            c.help_banner("Usage: ruby prog [options] [isbn]",
                          "This and further strings are optional.")
            c.uses_option(:option, "-o", "--option=VALUE",
                          "Message about option",
                          "More about option")
          }
        end
      end

      lines = output.split($/)
      # puts output
      assert_match(/Error in the command line: /, lines[0])
      assert_equal("Usage: ruby prog [options] [isbn]", lines[1])
      assert_match(/This and further/, lines[2])
      assert_match(/\s*/, lines[3])
      assert_match(/Options:/, lines[4])
      assert_match(/-o.*--option=VALUE.*Message about option/, lines[5])
      assert_match(/More about option/, lines[6])
      assert_match(/--help.*Show this message/, lines.last)
    }
  end
end

# Here are a variety of ways of writing the error messages

class ERROR_FORMATTING_CommandLineTest < Test::Unit::TestCase
  include UserChoices

  def test_range_violation_descriptions
    # Good about plurals.
    assert_raises_with_matching_message(StandardError,
            /2 arguments given, 3 expected/) {
      CommandLineChoices.claim_arglist_arity_OK(2, 3)
    }

    assert_raises_with_matching_message(StandardError,
            /1 argument given, 3 expected/) {
      CommandLineChoices.claim_arglist_arity_OK(1, 3)
    }

    assert_raises_with_matching_message(StandardError,
            /0 arguments given, 1 expected/) {
      CommandLineChoices.claim_arglist_arity_OK(0, 1)
    }

    # Handle both types of ranges.
    assert_raises_with_matching_message(StandardError,
            /2 arguments given, 3 to 5 expected/) {
      CommandLineChoices.claim_arglist_arity_OK(2, 3..5)
    }
    assert_raises_with_matching_message(StandardError,
            /1 argument given, 3 to 5 expected/) {
      CommandLineChoices.claim_arglist_arity_OK(1, 3...6)
    }
  end


  def test_a_singleton_arg_will_not_be_in_a_list
    with_command_args("arg-only") {
      hash = CommandLineChoices.fill do | c |
        c.uses_option(:unused, "--unused VALUE")  # just for grins
        c.uses_arg(:arg)
      end
      assert_true(hash.has_key?(:arg))
      assert_equal("arg-only", hash[:arg])
    }
  end

  def test_singleton_args_generate_errors
    with_command_args("") {
      output = capturing_stderr do
        assert_wants_to_exit do
          hash = CommandLineChoices.fill do | c |
            c.uses_arg(:arg)
          end
        end
      end

      assert_match(/0 arguments given, 1 expected./, output)
    }
  end

  def test_singleton_arguments_can_take_ranges_to_make_them_optional
    with_command_args("") {
      hash = CommandLineChoices.fill do | c |
        c.uses_arg(:arg, 0..1)
      end
      assert_false(hash.has_key?(:arg))
      assert_equal(nil, hash[:arg])
    }
  end

  def test_that_optional_singleton_arguments_still_precludes_two
    with_command_args("one two") {
      output = capturing_stderr do
        assert_wants_to_exit do
          hash = CommandLineChoices.fill do | c |
            c.uses_arg(:arg, 0..1)
          end
        end
      end

      assert_match(/2 arguments given, 0 to 1 expected/, output)
    }
  end

  def test_shorthand_for_optional_arg_omits_explicit_range
    with_command_args("") {
      hash = CommandLineChoices.fill do | c |
        c.uses_optional_arg(:arg)
      end
      assert_false(hash.has_key?(:arg))
      assert_equal(nil, hash[:arg])
    }
  end

end
