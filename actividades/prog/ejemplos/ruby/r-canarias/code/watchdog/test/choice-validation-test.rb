#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog/barkers.rb'

# Note: testing for reasonable values is done in site-default test.

class ChoiceValidationTests < Test::Unit::TestCase
  include Watchdog

  def assert_error_matching(regexp)
    assert_equal(1, @errors.grep(regexp).length, @errors.inspect)
  end

  def test_all_jabber_barker_errors
    @errors = JabberBarker.new({}).errors
    assert_error_matching(/no Jabber account/)
    assert_error_matching(/no Jabber password/)
    assert_error_matching(/no destination/)
    assert_equal(3, @errors.length)
  end

  def test_all_jabber_barker_hates_empty_to_list
    @errors = JabberBarker.new({:jabber_to => []}).validate
    assert_error_matching(/no destination/)
  end




  def test_all_email_barker_errors

    @errors = SmtpBarker.new({}).errors

    assert_error_matching(/no recipients/)
    assert_error_matching(/From line/)
    assert_error_matching(/no server/)
    assert_error_matching(/no.*account.*mail_account/)
    assert_error_matching(/no password/)
    assert_error_matching(/no port/)
    assert_error_matching(/no domain/)
    assert_error_matching(/No authentication/)

    assert_equal(8, @errors.length)
  end

  def test_all_email_barker_hates_empty_to_list
    @errors = SmtpBarker.new({:email_to => []}).errors
    assert_error_matching(/no recipients/)
  end


end
