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



class Examples < Test::Unit::TestCase
  def evalue(command)
    result = `#{command}`
    eval(result)
  end

  RUBY = "ruby #{PACKAGE_ROOT}/examples/"

  require "#{PACKAGE_ROOT}/examples/command-line"
  require "#{PACKAGE_ROOT}/examples/types"
  require "#{PACKAGE_ROOT}/examples/switches"
  require "#{PACKAGE_ROOT}/examples/two-args"
  require "#{PACKAGE_ROOT}/examples/multiple-sources"


  def test_succeeding_examples
    val = evalue("#{RUBY}command-line.rb --choice cho sophie paul dawn me")
    assert_equal({:names => ["sophie", "paul", "dawn", "me"],
                   :choice=>"cho"},
                 val)

    val = evalue("#{RUBY}command-line.rb -c choice")
    assert_equal({:names => [], :choice => "choice"}, val)

    val = evalue("#{RUBY}command-line.rb -cchoice")
    assert_equal({:names => [], :choice => "choice"}, val)

    val = evalue("#{RUBY}command-line.rb --choi choice")
    assert_equal({:names => [], :choice => "choice"}, val)

    val = evalue("#{RUBY}command-line.rb --choi choice -- -name1- -name2-")
    assert_equal({:names => ['-name1-', '-name2-'], :choice => 'choice'}, val)


    val = evalue("#{RUBY}default-values.rb --choice specific")
    assert_equal({:choice => 'specific'}, val)

    val = evalue("#{RUBY}default-values.rb")
    assert_equal({:choice => 'default'}, val)

    val = evalue("#{RUBY}default-values.rb only-arg")
    assert_equal({:choice => 'default', :name => 'only-arg'}, val)


    val = evalue("#{RUBY}types.rb --must-be-integer 3 argument")
    assert_equal({:arg => 'argument', :must_be_integer => 3}, val)


    val = evalue("#{RUBY}switches.rb 1 2")
    assert_equal({:switch=> false, :args => ['1', '2']}, val)

    val = evalue("#{RUBY}switches.rb --switch 1 2")
    assert_equal({:switch=> true, :args => ['1', '2']}, val)

    val = evalue("#{RUBY}switches.rb -s 2 1 ")
    assert_equal({:switch=> true, :args => ['2', '1']}, val)

    val = evalue("#{RUBY}switches.rb --no-switch 1 2")
    assert_equal({:switch=> false, :args => ['1', '2']}, val)

    val = evalue("#{RUBY}switches.rb 1  2  3 4")
    assert_equal({:switch=> false, :args => ['1', '2', '3', '4']}, val)


    val = evalue("#{RUBY}two-args.rb 1 2 ")
    assert_equal({:args => ['1', '2']}, val)


    val = evalue("#{RUBY}postprocess.rb 1 2")
    assert_equal({:infile => '1', :outfile => '2', :args => ['1', '2']},
                 val)
  end

  def test_multiple_sources
    xml = "<config><ordinary_choice>greetings</ordinary_choice></config>"

    with_local_config_file("ms-config.xml", xml) {
      val = evalue("#{RUBY}multiple-sources.rb")
      assert_equal({:names => [], :ordinary_choice => 'greetings'}, val)
    }

    with_local_config_file("ms-config.xml", xml) {
      with_environment_vars("ms_ordinary_choice" => 'hi') { 
        val = evalue("#{RUBY}multiple-sources.rb ")
        assert_equal({:names => [], :ordinary_choice => 'hi'}, val)
      }
    }
    

    with_local_config_file("ms-config.xml", xml) {
      with_environment_vars("ms_ordinary_choice" => 'hi') { 
        val = evalue("#{RUBY}multiple-sources.rb --ordinary-choice hello")
        assert_equal({:names => [], :ordinary_choice => 'hello'}, val)
      }
    }

  end


  def error(klass, args)
    capturing_stderr {
      with_pleasant_exceptions {
        with_command_args(args) {
          klass.new.execute
        }
      }
    }
  end

  def test_error_checking
    assert_match(/missing argument: --choice/,
                 error(CommandLineExample,  "--choice"))


    assert_match(/invalid option: --other/,
                 error(CommandLineExample,  "--other 3 -- choice"))

    
    assert_match(/'not-a' is not a valid value for '--a-or-b'/,
                 error(TypesExample,  "--a-or-b not-a  argument"))

    
    assert_match(/'--must-be-integer' requires an integer value/,
                 error(TypesExample,  "--must-be-integer 1d argument"))


    assert_match(/0 arguments given, 1 expected/, 
                 error(TypesExample,  ""))

    assert_match(/2 arguments given, 1 expected/, 
                 error(TypesExample,  "argument extra"))

    assert_match(/1 argument given, 2 to 4 expected/, 
                 error(SwitchExample,  "1"))

    assert_match(/5 arguments given, 2 to 4 expected/, 
                 error(SwitchExample,  "1 2 3 4 5"))

    assert_match(/1 argument given, 2 expected/, 
                 error(TwoArgExample,  "1"))

    assert_match(/3 arguments given, 2 expected/, 
                 error(TwoArgExample,  "1 2 3"))

  end

  def test_bad_xml
    xml = "<config><names"
    with_local_config_file("ms-config.xml", xml) {
      assert_match(/Badly formatted configuration file/,
                     error(MultipleSourcesExample,  "1 2")  )
    }
  end


  def test_help
    result = error(CommandLineExample, "--help")
    assert_match(/Usage.*Options:.*--choice.*CHOICE can be.*Show this/m,
                 result)

    result = error(SwitchExample, "--help")
    assert_match(/Usage.*Options:.*--\[no-\]switch.*Show this message/m,
                 result)
  end

end

