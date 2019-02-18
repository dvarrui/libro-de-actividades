#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 's4t-affinity-trip'

### These tests require a network.
class AffinityTripSlowTests < Test::Unit::TestCase
  include AffinityTrip
  include S4tUtils::TestUtil

  def test_assert_isbn_title_translation_works
    title = `ruby #{script('isbn-to-title.rb')} 0743292332`
    assert_equal('Cell: A Novel', title.strip)
  end

  def test_affinity_list_fetching_works
    # Because affinity lists change, we can only test
    # approximate correctness.
    list = `ruby #{script('isbn-to-affinity-list.rb')} 0974514055`
    assert_match(/Ruby/, list)
  end

  def test_affinity_list_trip_works
    list = `ruby #{script('affinity-trip.rb')}`.split($/)
    assert_equal(10, list.length)
    assert_match(/^Programming Ruby.*Second Edition$/, list[0])
  end

  def test_affinity_list_trip_can_produce_csv
    list = `ruby #{script('affinity-trip.rb')} --csv 0974514055`.split($/)
    assert_equal(10, list.length)
    assert_match(/^"Programming Ruby.*Second Edition.*","Dave Thomas,/, list[0])
  end
end

