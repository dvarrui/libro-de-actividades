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


class TestExternallyFilledHash < Test::Unit::TestCase
  class SubHash < UserChoices::ExternallyFilledHash
    
    def self.fill(names_to_values); new(names_to_values); end
    
    def source; "the test hash"; end
    
    def initialize(names_to_values)
      super()
      names_to_values.each do | name, value |
        key = name.to_sym
        record_name_key_and_value(name, key, value)
      end
    end
  end

  # Checking
  
  def test_checking_by_list
    SubHash.fill('a' => 'one').check_values(:a => ['one', 'two'])

    assert_raises_with_matching_message(StandardError,
            /Error in the test hash: 'one' is not a valid value for 'a'/) {
      SubHash.fill('a' => 'one').check_values(:a => ['two', 'three'])
    }
  end

  def test_checking_as_integer
    SubHash.fill('a' => '1').check_values(:a => :integer)

    assert_raises_with_matching_message(StandardError,
            /Error in the test hash: 'a' requires an integer value, and '1d' doesn't look like one/) {
      SubHash.fill('a' => '1d').check_values(:a => :integer)
    }
  end

  def test_checking_as_boolean
    SubHash.fill('a' => 'true').check_values(:a => :boolean)
    SubHash.fill('a' => 'false').check_values(:a => :boolean)

    # Case insensitive
    SubHash.fill('a' => 'False').check_values(:a => :boolean)
    SubHash.fill('a' => 'TRUE').check_values(:a => :boolean)
    
    assert_raises_with_matching_message(StandardError,
            /Error in the test hash: 'a' requires a boolean value, and 'tru' doesn't look like one/) {
      SubHash.fill('a' => 'tru').check_values(:a => :boolean)
    }
  end

  def test_it_is_ok_for_key_not_to_appear
    SubHash.fill('b' => 'foo').check_values(:a => :integer)
  end

  # Changing values

  def test_updating_into_integer
    h = SubHash.fill('a' => '1')
    h.update_values(:a => :integer)
    assert_equal(1, h[:a])
  end

  def test_updating_into_boolean
    h = SubHash.fill('a' => 'true')
    h.update_values(:a => :boolean)
    assert_equal(true, h[:a])

    h = SubHash.fill('a' => 'FALSE')
    h.update_values(:a => :boolean)
    assert_equal(false, h[:a])
  end

  def test_updating_into_array
    h = SubHash.fill('names' => 'brian,dawn,paul,sophie')
    h.update_values(:names => [:string])
    assert_equal(%w{brian dawn paul sophie}, h[:names])
  end


  def test_it_is_ok_for_key_not_to_appear
    h = SubHash.fill('b' => 'foo')
    h.update_values(:a => :integer)
    assert_equal('foo', h[:b])
    assert_false(h.respond_to?(:a))
  end

end

class DefaultChoicesTest < Test::Unit::TestCase
  include UserChoices

  def test_default_values_are_created_with_key_not_string
    # It just seemed for the default values to be created with a
    # string transformed into a hash -- too different from creating
    # a default hash.
    choices = DefaultChoices.fill(:a => 'a')
    assert_equal(1, choices.size)
    assert_equal('a', choices[:a])
  end

  def test_value_checking_is_set_up_properly
    assert_raises_with_matching_message(StandardError,
            /Error in the default values: ':a' requires an integer value, and 'five' doesn't look like one/) {
      DefaultChoices.fill(:a => 'five').check_values(:a => :integer)
    }
  end

  def test_value_updating_is_set_up_properly
    # Even though this means that the value 1 in the default is 
    # specified as "1". Too awkward?
    choices = DefaultChoices.fill(:a => '5')
    choices.update_values(:a => :integer)
    assert_equal(5, choices[:a])

    choices = DefaultChoices.fill(:a => '5,6')
    choices.update_values(:a => [:string])
    assert_equal(['5', '6'], choices[:a])
  end

  def test_nil_is_default
    choices = DefaultChoices.fill(:a => '5')
    assert_nil(choices[:foo])
  end
end

class EnvironmentChoicesTest < Test::Unit::TestCase
  include UserChoices

  def test_the_environment_args_of_interest_can_be_listed_explicitly
    with_environment_vars('amazon_option' => "1",
                          'root' => 'ok',
                          '~' => 'ok, too') do
      choices = EnvironmentChoices.fill(:option => 'amazon_option',
                                        :root => 'root',
                                        :home => '~')

      assert_equal(3, choices.size)
      assert_equal('1', choices[:option])
      assert_equal('ok', choices[:root])
      assert_equal('ok, too', choices[:home])
    end
  end

  def test_unmentioned_environment_vars_are_ignored
    with_environment_vars('unfound' => "1") do
      choices = EnvironmentChoices.fill(:option => 'amazon_option')

      assert_true(choices.empty?)
    end
  end

  def test_nil_is_default
    with_environment_vars('found' => "1") do
      choices = EnvironmentChoices.fill(:option => 'f')
      assert_nil(choices[:foo])
      assert_nil(choices[:option]) # for fun
    end
  end

  def test_value_checking_is_set_up_properly
    with_environment_vars('amazon_option' => "1") do
      assert_raises_with_matching_message(StandardError,
            /Error in the environment: '1' is not a valid value for 'amazon_option'/) {
        choices = EnvironmentChoices.fill(:a => 'amazon_option')
        choices.check_values(:a => ['one', 'dva'])
      }
    end

    with_environment_vars('amazon_option' => "1ed") do
      assert_raises_with_matching_message(StandardError,
            /Error in the environment: 'amazon_option' requires an integer value, and '1ed' doesn't look like one/) {
        choices = EnvironmentChoices.fill(:a => 'amazon_option')
        choices.check_values(:a => :integer)
      }
    end
  end

  def test_value_updating_is_set_up_properly
    with_environment_vars('a' => "1", 'names' => 'foo,bar') do
      choices = EnvironmentChoices.fill(:a => 'a', :names => 'names')
      choices.update_values(:a => :integer,
                            :names => [:string])
      assert_equal(1, choices[:a])
      assert_equal(['foo', 'bar'], choices[:names])
    end
  end

  def test_the_environment_args_of_interest_can_be_described_by_prefix
    with_environment_vars('amazon_option' => "1") do
      choices = EnvironmentChoices.with_prefix('amazon_')

      assert_true(choices.has_key?(:option))
      assert_equal('1', choices[:option])
    end
  end

  def test_can_also_give_explicit_args_of_interest
    with_environment_vars('amazon_o' => "1",
                          'other_option' => 'still found') do
      choices = EnvironmentChoices.with_prefix('amazon_',
                                               :other => 'other_option')

      assert_equal(2, choices.size)
      assert_equal('1', choices[:o])
      assert_equal('still found', choices[:other])
    end
  end

end

class XmlConfigFileChoicesTestCase < Test::Unit::TestCase
  include UserChoices

  def setup
    builder = Builder::XmlMarkup.new(:indent => 2)
    @some_xml = builder.config {
      builder.reverse("true")
      builder.maximum("53")
      builder.host('a.com')
      builder.host('b.com')
    }
  end
    
  def test_config_file_normal_use
    with_local_config_file('.amazonrc', @some_xml) {
      choices = XmlConfigFileChoices.fill(".amazonrc")

      assert_equal(3, choices.size)
      assert_equal("true", choices[:reverse])
      assert_equal("53", choices[:maximum])
      assert_equal(['a.com', 'b.com'], choices[:host])
    }
  end

  def test_config_file_need_not_exist
    assert_false(File.exist?(".amazonrc"))
    choices = XmlConfigFileChoices.fill(".amazonrc")

    assert_true(choices.empty?)
  end

  def test_config_file_with_bad_xml
    with_local_config_file('.amazonrc',"<malformed></xml>") {
      assert_raise_with_matching_message(REXML::ParseException,
          %r{Badly formatted configuration file ./.amazonrc: .*Missing end tag}) do
              XmlConfigFileChoices.fill(".amazonrc")
      end
    }
  end


  def test_config_file_value_checking_is_set_up_properly
    with_local_config_file(".amazonrc", @some_xml) do
      assert_raises_with_matching_message(StandardError,
           %r{Error in configuration file ./.amazonrc: '53' is not a valid value for 'maximum'}) {
        choices = XmlConfigFileChoices.fill(".amazonrc")
        choices.check_values(:maximum => ['low', 'high'])
      }
    end

    with_local_config_file(".amazonrc", @some_xml) do
      assert_raises_with_matching_message(StandardError,
            /Error in configuration file.* 'reverse' requires an integer value, and 'true' doesn't look like one/) {
        choices = XmlConfigFileChoices.fill('.amazonrc')
        choices.check_values(:reverse => :integer)
      }
    end
  end

  def test_value_updating_is_set_up_properly
    with_local_config_file('.amazonrc', @some_xml) do
      choices = XmlConfigFileChoices.fill('.amazonrc')
      choices.update_values(:maximum => :integer)
      assert_equal(53, choices[:maximum])
    end
  end

  def test_unmentioned_values_are_nil
    with_local_config_file('.amazonrc', @some_xml) do
      choices = XmlConfigFileChoices.fill('.amazonrc')
      assert_nil(choices[:unmentioned])
    end
  end


end


class CompositionChoicesTests < Test::Unit::TestCase
  include UserChoices

  def test_earlier_has_priority
    with_command_args("--option perfectly-fine --only-cmd=oc") {
      choices = ComposedChoices.fill(
                   CommandLineChoices.fill { | c | 
                      c.uses_option(:option, '--option VALUE')
                      c.uses_option(:only_cmd, '--only-cmd VALUE')
                   },
                   DefaultChoices.fill(:option => '0.3', 
                                       :only_default => 'od'))
      assert_equal("perfectly-fine", choices[:option])
      assert_equal("oc", choices[:only_cmd])
      assert_equal("od", choices[:only_default])
    }
  end
  
  def test_checking_is_forwarded
    with_command_args("--command-option perfectly-fine") {
      assert_raises_with_matching_message(StandardError, 
                                          /Error in the default values/) {
      ComposedChoices.fill(
                           CommandLineChoices.fill { | c | 
                             c.uses_option(:command_option,
                                           '--command-option VALUE')
                           },
                           DefaultChoices.fill(:broken => '0.3')) { | c |
          c.check_values(:broken => :integer)
        }
      }
    }
  end
  
  def test_updates_are_forwarded
    with_environment_vars("amazon_rc" => "1") {
      choices = ComposedChoices.fill(
                   EnvironmentChoices.with_prefix('amazon'),
                   DefaultChoices.fill(:_rc => '3')) { | c |
        c.check_values(:_rc => :integer)
        c.update_values(:_rc => :integer)
      }
      assert_equal(1, choices[:_rc])
    }
  end

  def test_unmentioned_choices_are_nil
    choices = ComposedChoices.fill(
                      DefaultChoices.fill(:rc => '5'),
                      DefaultChoices.fill(:rc => '3')) { | c |
      c.check_values(:rc => :integer)
    }
    assert_equal(nil, choices[:unmentioned])
    assert_equal('5', choices[:rc])   # for fun
  end
    

  # There's an unfortunate side effect of the way command-line arglists
  # are handled. Should empty arglists override later choices objects? 
  # Probably. But right now that only happens for optional arguments.
  # An empty command list overrides, though, turning into the empty list.
  # Hard to fix. Fix if anyone ever complains.

  def test_missing_optional_args_do_not_override_lower_precedence_sources
    with_command_args("") {
      choices = ComposedChoices.fill(
                    CommandLineChoices.fill { | c | 
                      c.uses_optional_arg(:name)
                    },
                                     DefaultChoices.fill(:name => 'default'))
      assert_equal('default', choices[:name])
    }
  end

  def test_however_given_optional_args_do
    with_command_args("override") {
      choices = ComposedChoices.fill(
                    CommandLineChoices.fill { | c | 
                      c.uses_optional_arg(:name)
                    },
                    DefaultChoices.fill(:name => 'default'))
      assert_equal('override', choices[:name])
    }
  end

  def test_empty_arglists_DO__surprisingly__override_lower_precedence_sources
    xml = "<config><name>1</name><name>2</name></config>"
    with_local_config_file("test-config", xml) {
      with_command_args("") {
        choices = ComposedChoices.fill(
                    CommandLineChoices.fill { | c | 
                      c.uses_arglist(:name)
                    },
                    XmlConfigFileChoices.fill("test-config"))
        # assert_equal(['1', '2'], choices[:name])
        assert_equal([], choices[:name])
      }
    }
  end

  def test_however_non_empty_arglists_do # name suggests what should be expected of the previous test.
    with_command_args("1") {
      choices = ComposedChoices.fill(
                    CommandLineChoices.fill { | c | 
                      c.uses_arglist(:name)
                    },
                    DefaultChoices.fill(:name => ['default', '1']))
      assert_equal(['1'], choices[:name])
    }
  end


  
end
