#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
load "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
require 'user-choices'
include S4tUtils


class TestDefaultsAndTypes < Test::Unit::TestCase
  include UserChoices

  def test_builder_can_add_defaults
    b = ChoicesBuilder.new
    b.add_choice(:trip_steps, :default => '5')
    choices = b.build
    assert_equal('5', choices[:trip_steps])
  end

  def test_builder_can_declare_types_and_do_error_checking
    b = ChoicesBuilder.new
    b.add_choice(:trip_steps, :default => 'a', :type => :integer)
    assert_raises_with_matching_message(StandardError,
                                        /':trip_steps' requires an integer value, and 'a'/) {
      b.build
    }
  end

  def test_builder_can_declare_types_and_do_conversions
    b = ChoicesBuilder.new
    b.add_choice(:csv, :default => 'true', :type => :boolean)
    choices = b.build
    assert_equal(true, choices[:csv])
  end

  def test_some_types_cause_no_conversion
    # Checking is done
    b = ChoicesBuilder.new
    b.add_choice(:trip_steps, :default => 'a', :type => ['b', 'c'])
    assert_raises_with_matching_message(StandardError,
                                        /'a' is not a valid value/) {
      b.build
    }

    # ... but, if checking passes, no changes are made
    b = ChoicesBuilder.new
    b.add_choice(:trip_steps, :default => 'b', :type => ['b', 'c'])
    assert_equal('b', b.build[:trip_steps])
  end

  def test_arrays_can_be_built_from_comma_separated_list
    b = ChoicesBuilder.new
    b.add_choice(:targets, :default => 'a,b,cd',
                 :type => [:string])
    assert_equal(['a', 'b', 'cd'],
                 b.build[:targets])
  end

  def test_arrays_can_be_accepted_as_is
    b = ChoicesBuilder.new
    b.add_choice(:targets, :default => ['a', 'b', 'c'],
                 :type => [:string])
    assert_equal(['a', 'b', 'c'], b.build[:targets])
  end

  def arrays_are_constructed_from_single_elements
    b = ChoicesBuilder.new
    b.add_choice(:targets, :default => 'a',
                 :type => [:string])
    assert_equal(['a'], b.build[:targets])
  end

end

class TestChainingOfSources < Test::Unit::TestCase
  include UserChoices

  def test_sources_are_chained_correctly
    with_environment_vars("prefix_in_ecd" =>  "e") {
      with_local_config_file(".builder_rc",
                             "<config>
                                <in_ecd>c</in_ecd>
                                <in_cd>c</in_cd>
                              </config>") {
        b = ChoicesBuilder.new
        b.add_source(EnvironmentChoices, :with_prefix, "prefix_")
        b.add_source(XmlConfigFileChoices, :from_file, ".builder_rc")

        b.add_choice(:in_ecd, :default => "d")
        b.add_choice(:in_cd, :default => "d")
        b.add_choice(:in_d, :default => "d")

        choices = b.build

        assert_equal('e', choices[:in_ecd])
        assert_equal('c', choices[:in_cd])
        assert_equal('d', choices[:in_d])
      }
    }
  end
end
        
class TestCommandLineConstruction < Test::Unit::TestCase
  include UserChoices

  def test_command_line_choices_requires_blocks_for_initialization
    with_command_args("--switch -c 5 arg") {
      b = ChoicesBuilder.new
      b.add_source(CommandLineChoices, :fill)

      b.add_choice(:unused) { | command_line |
        command_line.uses_switch("-u", "--unused")
      }

      b.add_choice(:switch, :type=>:boolean) { | command_line |
        command_line.uses_switch("--switch")
      }

      b.add_choice(:clear, :type => :integer) { | command_line |
        command_line.uses_option("-c", "--clear N",
                            "Clear the frobbistat N times.")
      }

      b.add_choice(:args) { | command_line |
        command_line.uses_arglist
      }
        
      choices = b.build

      assert_equal(true, choices[:switch])
      assert_false(choices.has_key?(:unused))
      assert_equal(5, choices[:clear])
      assert_equal(['arg'], choices[:args])
    }
  end

  def test_command_line_source_initializes_help_text
    with_command_args('--help') {
      output = capturing_stderr do
        assert_wants_to_exit do
          b = ChoicesBuilder.new
          b.add_source(CommandLineChoices, :usage,
                       "Usage: prog [options]",
                       "This is supplemental.")

          b.add_choice(:test) { | command_line |
            command_line.uses_switch("--test",
                                     "Here's text for a switch")
            command_line.uses_option("-r", "--renew VALUE",
                                     "Here's text for an option")
          }
          b.build
        end
      end
      assert(l1 = output.index("Usage: prog [options]"))
      assert(l2 = output.index("This is supplemental"))
      assert(l3 = output.index(/--\[no-\]test.*Here's text for a switch/))
      assert(l4 = output.index(/-r.*--renew.*VALUE.*Here's text for an option/))
      assert(l5 = output.index("--help"))

      assert(l1 < l2)
      assert(l2 < l3)
      assert(l3 < l4)
      assert(l4 < l5)
    }
  end
end

