#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/enumerable'
require 'set'

class TC_Enumerable < Test::Unit::TestCase
  def setup
  end

  def teardown
  end

  def test_build_hash
    input = [1, 2, 3]
    expected_output = { 1 => 1, 4 => 8, 9 => 27 }
    output = input.build_hash { |n| [n**2, n**3] }
    assert_equal(expected_output, output)

    input = { "x" => 1, "y" => 2 }
    expected_output = { :x => 1, :y => 2 }
    output = input.build_hash { |a,b| [a.intern, b] }
    assert_equal(expected_output, output)

    input = [[1,1], [2,2]]
    expected_output = { 1 => 1, 2 => 2 }
    output = input.build_hash { |a,b| [a, b] }
    assert_equal(expected_output, output)
  end

  def test_mapf
    assert_equal([1,2,3], %w[1 2 3].mapf(:to_i))
    assert_equal(%w[John Terry Fiona], %w[john terry fiona].mapf(:capitalize))
  end

  def test_collectf
    assert_equal([1,2,3], %w[1 2 3].collectf(:to_i))
    assert_equal(%w[John Terry Fiona], %w[john terry fiona].collectf(:capitalize))
  end

  def test_includes
    assert([1, 2, 3].includes?(2))
    assert("one\ntwo\nthree\n".includes?(5) == false)
    assert({:t => 5, :u => 10, :e => 51}.includes?([:u, 10]))
  end

  def test_contains
    assert([1, 2, 3].contains?(2))
    assert("one\ntwo\nthree\n".contains?(5) == false)
    assert({:t => 5, :u => 10, :e => 51}.contains?([:u, 10]))
  end

  def test_has
    assert([1, 2, 3].has?(2))
    assert("one\ntwo\nthree\n".has?(5) == false)
    assert("one\ntwo\nthree\n".has?("two\n"))
    assert({:t => 5, :u => 10, :e => 51}.has?([:u, 10]))
  end

  def test_map_with_index
    columns = [ ["EMP_ID","INTEGER"], ["DIVISION", "CHAR(10)"] ]
    data = ["11", "Retail"]
    string_data = columns.map_with_index { |c, i|
      col, type = c
      datum = data[i]
      datum = "'#{datum}'" if type =~ /char/i
      datum
    }
    expected_output = ["11", "'Retail'"]
    assert_equal(expected_output, string_data)
  end

  def test_partition_by
    assert_equal({}, [].partition_by { |x| x })
    
    result = (1..5).partition_by { |n| n % 3 } 
    expected_result = { 0 => [3], 1 => [1, 4], 2 => [2,5] } 
    assert_equal(expected_result, result)
  
    result = ["I had", 1, "dollar and", 50, "cents"].partition_by { |e| e.class }
    expected_result = { String => ["I had","dollar and","cents"], Fixnum => [1,50] } 
    assert_equal(expected_result, result)
  end

  # Object#in? is tested in tc_object.rb.

  def test_none?
    enum = (5..15)
    assert_equal(true,  [].none?)
    assert_equal(true,  [nil, nil, nil].none? )
    assert_equal(false, enum.none?)
    assert_equal(false, enum.none? { |n| n < 10 } )
    assert_equal(true,  enum.none? { |n| n < 0 } )

      # Taken from RDoc documentation.
    assert_equal(true, [].none?)
    assert_equal(true, [nil].none?)
    assert_equal(false, [5,8,9].none?)
    assert_equal(true, (1...10).none? { |n| n < 0 })
    assert_equal(false, (1...10).none? { |n| n > 0 })
  end

  def test_one?
    enum = (5..15)
    assert_equal(true,  [nil, 5, nil].one?)
    assert_equal(false, enum.one?)
    assert_equal(false, enum.one? { |n| n < 10 } )
    assert_equal(true,  enum.one? { |n| n == 9 } )

      # Taken from RDoc documentation.
    assert_equal(false, [].one?)
    assert_equal(false, [nil].one?)
    assert_equal(true,  [5].one?)
    assert_equal(false, [5,8,9].one?)
    assert_equal(true,  (1...10).one? { |n| n == 5 } )
    assert_equal(false, (1...10).one? { |n| n < 5 } )
  end

end  # class TC_Enumerable

