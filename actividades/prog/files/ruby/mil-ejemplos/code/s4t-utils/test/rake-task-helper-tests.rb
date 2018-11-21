#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'
require 's4t-utils'


class RakeHelperTests < Test::Unit::TestCase
  include S4tUtils

  def test_generated_taskname
    assert_equal('__generated__foo', generated_taskname('foo'))
    assert_equal('__generated__foo', generated_taskname(:foo))
    assert_equal('__generated__foo_barness',
                 generated_taskname("! foo_bar-ness"))
    assert_equal('prefix_orp', generated_taskname('orp', 'prefix_'))
  end

  def test_update_peer_subtask_name
    assert_equal('__update_peers_s4tutils',
                 update_peer_subtask_name('s4t-utils'))
  end

  def test_singular_test_targets_allow_plurals
    assert_equal("{dbtest,dbtests}", broaden_test_name('dbtest'))
  end

  def test_plural_test_targets_allow_singular
    assert_equal("{dbtest,dbtests}", broaden_test_name('dbtests'))
  end

  def test_desired_subset_can_include_pending_tests
    local_pending_test = "pending.test.rb"
    local_finished_test = "finished.test.html"
    remote_pending_test = "dir/pending.x.rb"
    remote_finished_test = "dir/fini.rb"

    assert_true(pending?(local_pending_test))
    assert_true(pending?(remote_pending_test))
    assert_false(pending?(local_finished_test))
    assert_false(pending?(remote_finished_test))

    tests = [
      local_pending_test, local_finished_test,
      remote_pending_test, remote_finished_test
    ]
    assert_equal([local_finished_test, remote_finished_test],
                 desired_subset(tests, false))
    assert_equal(tests,
                 desired_subset(tests, true))
  end

  def test_pending_has_an_exact_boundary
    assert_false(pending?('pendingx.rb'))
    assert_false(pending?('xpending.rb'))
  end

  def test_pending_can_use_dashes_or_dots
    assert_true(pending?('pending-test.rb'))
    assert_true(pending?('pending.test.rb'))
  end


end
