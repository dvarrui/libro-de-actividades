#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/numeric'

class TC_Numeric < Test::Unit::TestCase
  def test_even
    (-100..100).step(2) do |n|
      assert(n.even? == true)
    end
    (-101..101).step(2) do |n|
      assert(n.even? == false)
    end
  end

  def test_odd
    (-101..101).step(2) do |n|
      assert(n.odd? == true)
    end
    (-100..100).step(2) do |n|
      assert(n.odd? == false)
    end
  end
end  # class TC_Numeric

# Part of Austin Ziegler's code; see copyright notice above.
class TC_FormatNumber < Test::Unit::TestCase
  def test_integer
    assert_equal("1", 1.format_s(:us))
    assert_equal("12", 12.format_s(:us))
    assert_equal("123", 123.format_s(:us))
    assert_equal("1,234", 1234.format_s(:us))
    assert_equal("12,345", 12345.format_s(:us))
    assert_equal("123,456", 123456.format_s(:us))
    assert_equal("1,234,567", 1234567.format_s(:us))
    assert_equal("12,345,678", 12345678.format_s(:us))
    assert_equal("123,456,789", 123456789.format_s(:us))
    assert_equal("1,234,567,890", 1234567890.format_s(:us))
    assert_equal("1", 1.format_s)
    assert_equal("12", 12.format_s)
    assert_equal("123", 123.format_s)
    assert_equal("1,234", 1234.format_s)
    assert_equal("12,345", 12345.format_s)
    assert_equal("123,456", 123456.format_s)
    assert_equal("1,234,567", 1234567.format_s)
    assert_equal("12,345,678", 12345678.format_s)
    assert_equal("123,456,789", 123456789.format_s)
    assert_equal("1,234,567,890", 1234567890.format_s)
  end

  def test_usd_integer
    assert_equal("$1", 1.format_s(:usd))
    assert_equal("$12", 12.format_s(:usd))
    assert_equal("$123", 123.format_s(:usd))
    assert_equal("$1,234", 1234.format_s(:usd))
    assert_equal("$12,345", 12345.format_s(:usd))
    assert_equal("$123,456", 123456.format_s(:usd))
    assert_equal("$1,234,567", 1234567.format_s(:usd))
    assert_equal("$12,345,678", 12345678.format_s(:usd))
    assert_equal("$123,456,789", 123456789.format_s(:usd))
    assert_equal("$1,234,567,890", 1234567890.format_s(:usd))
  end

  def test_percent_integer
    assert_equal("1%", 1.format_s(:percent))
    assert_equal("12%", 12.format_s(:percent))
    assert_equal("123%", 123.format_s(:percent))
    assert_equal("1,234%", 1234.format_s(:percent))
    assert_equal("12,345%", 12345.format_s(:percent))
    assert_equal("123,456%", 123456.format_s(:percent))
    assert_equal("1,234,567%", 1234567.format_s(:percent))
    assert_equal("12,345,678%", 12345678.format_s(:percent))
    assert_equal("123,456,789%", 123456789.format_s(:percent))
    assert_equal("1,234,567,890%", 1234567890.format_s(:percent))
  end

  def test_euro_integer
    assert_equal("€1", 1.format_s(:euro))
    assert_equal("€12", 12.format_s(:euro))
    assert_equal("€123", 123.format_s(:euro))
    assert_equal("€1 234", 1234.format_s(:euro))
    assert_equal("€12 345", 12345.format_s(:euro))
    assert_equal("€123 456", 123456.format_s(:euro))
    assert_equal("€1 234 567", 1234567.format_s(:euro))
    assert_equal("€12 345 678", 12345678.format_s(:euro))
    assert_equal("€123 456 789", 123456789.format_s(:euro))
    assert_equal("€1 234 567 890", 1234567890.format_s(:euro))
  end

  def test_negative_integer
    assert_equal("-1", -1.format_s(:us))
    assert_equal("-12", -12.format_s(:us))
    assert_equal("-123", -123.format_s(:us))
    assert_equal("-1,234", -1234.format_s(:us))
    assert_equal("-12,345", -12345.format_s(:us))
    assert_equal("-123,456", -123456.format_s(:us))
    assert_equal("-1,234,567", -1234567.format_s(:us))
    assert_equal("-12,345,678", -12345678.format_s(:us))
    assert_equal("-123,456,789", -123456789.format_s(:us))
    assert_equal("-1,234,567,890", -1234567890.format_s(:us))
    assert_equal("-1", -1.format_s)
    assert_equal("-12", -12.format_s)
    assert_equal("-123", -123.format_s)
    assert_equal("-1,234", -1234.format_s)
    assert_equal("-12,345", -12345.format_s)
    assert_equal("-123,456", -123456.format_s)
    assert_equal("-1,234,567", -1234567.format_s)
    assert_equal("-12,345,678", -12345678.format_s)
    assert_equal("-123,456,789", -123456789.format_s)
    assert_equal("-1,234,567,890", -1234567890.format_s)
  end

  def test_negative_usd_integer
    assert_equal("$-1", -1.format_s(:usd))
    assert_equal("$-12", -12.format_s(:usd))
    assert_equal("$-123", -123.format_s(:usd))
    assert_equal("$-1,234", -1234.format_s(:usd))
    assert_equal("$-12,345", -12345.format_s(:usd))
    assert_equal("$-123,456", -123456.format_s(:usd))
    assert_equal("$-1,234,567", -1234567.format_s(:usd))
    assert_equal("$-12,345,678", -12345678.format_s(:usd))
    assert_equal("$-123,456,789", -123456789.format_s(:usd))
    assert_equal("$-1,234,567,890", -1234567890.format_s(:usd))
  end

  def test_negative_percent_integer
    assert_equal("-1%", -1.format_s(:percent))
    assert_equal("-12%", -12.format_s(:percent))
    assert_equal("-123%", -123.format_s(:percent))
    assert_equal("-1,234%", -1234.format_s(:percent))
    assert_equal("-12,345%", -12345.format_s(:percent))
    assert_equal("-123,456%", -123456.format_s(:percent))
    assert_equal("-1,234,567%", -1234567.format_s(:percent))
    assert_equal("-12,345,678%", -12345678.format_s(:percent))
    assert_equal("-123,456,789%", -123456789.format_s(:percent))
    assert_equal("-1,234,567,890%", -1234567890.format_s(:percent))
  end

  def test_negative_euro_integer
    assert_equal("€-1", -1.format_s(:euro))
    assert_equal("€-12", -12.format_s(:euro))
    assert_equal("€-123", -123.format_s(:euro))
    assert_equal("€-1 234", -1234.format_s(:euro))
    assert_equal("€-12 345", -12345.format_s(:euro))
    assert_equal("€-123 456", -123456.format_s(:euro))
    assert_equal("€-1 234 567", -1234567.format_s(:euro))
    assert_equal("€-12 345 678", -12345678.format_s(:euro))
    assert_equal("€-123 456 789", -123456789.format_s(:euro))
    assert_equal("€-1 234 567 890", -1234567890.format_s(:euro))
  end

  def test_spaces
    assert_equal("1", 1.format_s(:us, :sep => " "))
    assert_equal("12", 12.format_s(:us, :sep => " "))
    assert_equal("123", 123.format_s(:us, :sep => " "))
    assert_equal("1 234", 1234.format_s(:us, :sep => " "))
    assert_equal("12 345", 12345.format_s(:us, :sep => " "))
    assert_equal("123 456", 123456.format_s(:us, :sep => " "))
    assert_equal("1 234 567", 1234567.format_s(:us, :sep => " "))
    assert_equal("12 345 678", 12345678.format_s(:us, :sep => " "))
    assert_equal("123 456 789", 123456789.format_s(:us, :sep => " "))
    assert_equal("1 234 567 890", 1234567890.format_s(:us, :sep => " "))
  end

  def test_negative_spaces
    assert_equal("-1", -1.format_s(:us, :sep => " "))
    assert_equal("-12", -12.format_s(:us, :sep => " "))
    assert_equal("-123", -123.format_s(:us, :sep => " "))
    assert_equal("-1 234", -1234.format_s(:us, :sep => " "))
    assert_equal("-12 345", -12345.format_s(:us, :sep => " "))
    assert_equal("-123 456", -123456.format_s(:us, :sep => " "))
    assert_equal("-1 234 567", -1234567.format_s(:us, :sep => " "))
    assert_equal("-12 345 678", -12345678.format_s(:us, :sep => " "))
    assert_equal("-123 456 789", -123456789.format_s(:us, :sep => " "))
    assert_equal("-1 234 567 890", -1234567890.format_s(:us, :sep => " "))
  end

  def test_size
    assert_equal("1", 1.format_s(:us, :size => 2))
    assert_equal("12", 12.format_s(:us, :size => 2))
    assert_equal("1,23", 123.format_s(:us, :size => 2))
    assert_equal("12,34", 1234.format_s(:us, :size => 2))
    assert_equal("1,23,45", 12345.format_s(:us, :size => 2))
    assert_equal("12,34,56", 123456.format_s(:us, :size => 2))
    assert_equal("1,23,45,67", 1234567.format_s(:us, :size => 2))
    assert_equal("12,34,56,78", 12345678.format_s(:us, :size => 2))
    assert_equal("1,23,45,67,89", 123456789.format_s(:us, :size => 2))
    assert_equal("12,34,56,78,90", 1234567890.format_s(:us, :size => 2))
  end

  def test_negative_size
    assert_equal("-1", -1.format_s(:us, :size => 2))
    assert_equal("-12", -12.format_s(:us, :size => 2))
    assert_equal("-1,23", -123.format_s(:us, :size => 2))
    assert_equal("-12,34", -1234.format_s(:us, :size => 2))
    assert_equal("-1,23,45", -12345.format_s(:us, :size => 2))
    assert_equal("-12,34,56", -123456.format_s(:us, :size => 2))
    assert_equal("-1,23,45,67", -1234567.format_s(:us, :size => 2))
    assert_equal("-12,34,56,78", -12345678.format_s(:us, :size => 2))
    assert_equal("-1,23,45,67,89", -123456789.format_s(:us, :size => 2))
    assert_equal("-12,34,56,78,90", -1234567890.format_s(:us, :size => 2))
  end

  def test_usd_decimal
    assert_equal("$1.1", 1.1.format_s(:usd))
    assert_equal("$12.12", 12.12.format_s(:usd))
    assert_equal("$123.123", 123.123.format_s(:usd))
    assert_equal("$1,234.1234", 1234.1234.format_s(:usd))
    assert_equal("$12,345.12345", 12345.12345.format_s(:usd))
    assert_equal("$123,456.123456", 123456.123456.format_s(:usd))
    assert_equal("$1,234,567.1234567", 1234567.1234567.format_s(:usd))
    assert_equal("$12,345,678.1234568", 12345678.12345678.format_s(:usd))
    assert_equal("$123,456,789.123457", 123456789.123456789.format_s(:usd))
    assert_equal("$1,234,567,890.01235", 1234567890.0123456789.format_s(:usd))
  end

  def test_percent_decimal
    assert_equal("1.1%", 1.1.format_s(:percent))
    assert_equal("12.12%", 12.12.format_s(:percent))
    assert_equal("123.123%", 123.123.format_s(:percent))
    assert_equal("1,234.1234%", 1234.1234.format_s(:percent))
    assert_equal("12,345.12345%", 12345.12345.format_s(:percent))
    assert_equal("123,456.123456%", 123456.123456.format_s(:percent))
    assert_equal("1,234,567.1234567%", 1234567.1234567.format_s(:percent))
    assert_equal("12,345,678.1234568%", 12345678.12345678.format_s(:percent))
    assert_equal("123,456,789.123457%", 123456789.123456789.format_s(:percent))
    assert_equal("1,234,567,890.01235%", 1234567890.0123456789.format_s(:percent))
  end

  def test_euro_decimal
    assert_equal("€1,1", 1.1.format_s(:euro))
    assert_equal("€12,12", 12.12.format_s(:euro))
    assert_equal("€123,123", 123.123.format_s(:euro))
    assert_equal("€1 234,1234", 1234.1234.format_s(:euro))
    assert_equal("€12 345,12345", 12345.12345.format_s(:euro))
    assert_equal("€123 456,123456", 123456.123456.format_s(:euro))
    assert_equal("€1 234 567,1234567", 1234567.1234567.format_s(:euro))
    assert_equal("€12 345 678,1234568", 12345678.12345678.format_s(:euro))
    assert_equal("€123 456 789,123457", 123456789.123456789.format_s(:euro))
    assert_equal("€1 234 567 890,01235", 1234567890.0123456789.format_s(:euro))
  end

  def test_decimal
    assert_equal("1.1", 1.1.format_s(:us))
    assert_equal("12.12", 12.12.format_s(:us))
    assert_equal("123.123", 123.123.format_s(:us))
    assert_equal("1,234.1234", 1234.1234.format_s(:us))
    assert_equal("12,345.12345", 12345.12345.format_s(:us))
    assert_equal("123,456.123456", 123456.123456.format_s(:us))
    assert_equal("1,234,567.1234567", 1234567.1234567.format_s(:us))
    assert_equal("12,345,678.1234568", 12345678.12345678.format_s(:us))
    assert_equal("123,456,789.123457", 123456789.123456789.format_s(:us))
    assert_equal("1,234,567,890.01235", 1234567890.0123456789.format_s(:us))
    assert_equal("1.1", 1.1.format_s)
    assert_equal("12.12", 12.12.format_s)
    assert_equal("123.123", 123.123.format_s)
    assert_equal("1,234.1234", 1234.1234.format_s)
    assert_equal("12,345.12345", 12345.12345.format_s)
    assert_equal("123,456.123456", 123456.123456.format_s)
    assert_equal("1,234,567.1234567", 1234567.1234567.format_s)
    assert_equal("12,345,678.1234568", 12345678.12345678.format_s)
    assert_equal("123,456,789.123457", 123456789.123456789.format_s)
    assert_equal("1,234,567,890.01235", 1234567890.0123456789.format_s)
  end

  def test_negative_decimal
    assert_equal("-1.1", -1.1.format_s(:us))
    assert_equal("-12.12", -12.12.format_s(:us))
    assert_equal("-123.123", -123.123.format_s(:us))
    assert_equal("-1,234.1234", -1234.1234.format_s(:us))
    assert_equal("-12,345.12345", -12345.12345.format_s(:us))
    assert_equal("-123,456.123456", -123456.123456.format_s(:us))
    assert_equal("-1,234,567.1234567", -1234567.1234567.format_s(:us))
    assert_equal("-12,345,678.1234568", -12345678.12345678.format_s(:us))
    assert_equal("-123,456,789.123457", -123456789.123456789.format_s(:us))
    assert_equal("-1,234,567,890.01235", -1234567890.0123456789.format_s(:us))
  end

  def test_negative_usd_decimal
    assert_equal("$-1.1", -1.1.format_s(:usd))
    assert_equal("$-12.12", -12.12.format_s(:usd))
    assert_equal("$-123.123", -123.123.format_s(:usd))
    assert_equal("$-1,234.1234", -1234.1234.format_s(:usd))
    assert_equal("$-12,345.12345", -12345.12345.format_s(:usd))
    assert_equal("$-123,456.123456", -123456.123456.format_s(:usd))
    assert_equal("$-1,234,567.1234567", -1234567.1234567.format_s(:usd))
    assert_equal("$-12,345,678.1234568", -12345678.12345678.format_s(:usd))
    assert_equal("$-123,456,789.123457", -123456789.123456789.format_s(:usd))
    assert_equal("$-1,234,567,890.01235", -1234567890.0123456789.format_s(:usd))
  end

  def test_negative_percent_decimal
    assert_equal("-1.1%", -1.1.format_s(:percent))
    assert_equal("-12.12%", -12.12.format_s(:percent))
    assert_equal("-123.123%", -123.123.format_s(:percent))
    assert_equal("-1,234.1234%", -1234.1234.format_s(:percent))
    assert_equal("-12,345.12345%", -12345.12345.format_s(:percent))
    assert_equal("-123,456.123456%", -123456.123456.format_s(:percent))
    assert_equal("-1,234,567.1234567%", -1234567.1234567.format_s(:percent))
    assert_equal("-12,345,678.1234568%", -12345678.12345678.format_s(:percent))
    assert_equal("-123,456,789.123457%", -123456789.123456789.format_s(:percent))
    assert_equal("-1,234,567,890.01235%", -1234567890.0123456789.format_s(:percent))
  end

  def test_negative_euro_decimal
    assert_equal("€-1,1", -1.1.format_s(:euro))
    assert_equal("€-12,12", -12.12.format_s(:euro))
    assert_equal("€-123,123", -123.123.format_s(:euro))
    assert_equal("€-1 234,1234", -1234.1234.format_s(:euro))
    assert_equal("€-12 345,12345", -12345.12345.format_s(:euro))
    assert_equal("€-123 456,123456", -123456.123456.format_s(:euro))
    assert_equal("€-1 234 567,1234567", -1234567.1234567.format_s(:euro))
    assert_equal("€-12 345 678,1234568", -12345678.12345678.format_s(:euro))
    assert_equal("€-123 456 789,123457", -123456789.123456789.format_s(:euro))
    assert_equal("€-1 234 567 890,01235", -1234567890.0123456789.format_s(:euro))
  end

  def test_european
    assert_equal("1,1", 1.1.format_s(:eu))
    assert_equal("12,12", 12.12.format_s(:eu))
    assert_equal("123,123", 123.123.format_s(:eu))
    assert_equal("1 234,1234", 1234.1234.format_s(:eu))
    assert_equal("12 345,12345", 12345.12345.format_s(:eu))
    assert_equal("123 456,123456", 123456.123456.format_s(:eu))
    assert_equal("1 234 567,1234567", 1234567.1234567.format_s(:eu))
    assert_equal("12 345 678,1234568", 12345678.12345678.format_s(:eu))
    assert_equal("123 456 789,123457", 123456789.123456789.format_s(:eu))
    assert_equal("1 234 567 890,01235", 1234567890.0123456789.format_s(:eu))
  end

  def test_negative_european
    assert_equal("-1,1", -1.1.format_s(:eu))
    assert_equal("-12,12", -12.12.format_s(:eu))
    assert_equal("-123,123", -123.123.format_s(:eu))
    assert_equal("-1 234,1234", -1234.1234.format_s(:eu))
    assert_equal("-12 345,12345", -12345.12345.format_s(:eu))
    assert_equal("-123 456,123456", -123456.123456.format_s(:eu))
    assert_equal("-1 234 567,1234567", -1234567.1234567.format_s(:eu))
    assert_equal("-12 345 678,1234568", -12345678.12345678.format_s(:eu))
    assert_equal("-123 456 789,123457", -123456789.123456789.format_s(:eu))
    assert_equal("-1 234 567 890,01235", -1234567890.0123456789.format_s(:eu))
  end

  def test_negative_accountant
    assert_equal("(1.1)", -1.1.format_s(:us, :acct => true))
    assert_equal("(12.12)", -12.12.format_s(:us, :acct => true))
    assert_equal("(123.123)", -123.123.format_s(:us, :acct => true))
    assert_equal("(1,234.1234)", -1234.1234.format_s(:us, :acct => true))
    assert_equal("(12,345.12345)", -12345.12345.format_s(:us, :acct => true))
    assert_equal("(123,456.123456)", -123456.123456.format_s(:us, :acct => true))
    assert_equal("(1,234,567.1234567)", -1234567.1234567.format_s(:us, :acct => true))
    assert_equal("(12,345,678.1234568)", -12345678.12345678.format_s(:us, :acct => true))
    assert_equal("(123,456,789.123457)", -123456789.123456789.format_s(:us, :acct => true))
    assert_equal("(1,234,567,890.01235)", -1234567890.0123456789.format_s(:us, :acct => true))
  end

  def test_negative_accountant_usd
    assert_equal("$(1.1)", -1.1.format_s(:usd, :acct => true))
    assert_equal("$(12.12)", -12.12.format_s(:usd, :acct => true))
    assert_equal("$(123.123)", -123.123.format_s(:usd, :acct => true))
    assert_equal("$(1,234.1234)", -1234.1234.format_s(:usd, :acct => true))
    assert_equal("$(12,345.12345)", -12345.12345.format_s(:usd, :acct => true))
    assert_equal("$(123,456.123456)", -123456.123456.format_s(:usd, :acct => true))
    assert_equal("$(1,234,567.1234567)", -1234567.1234567.format_s(:usd, :acct => true))
    assert_equal("$(12,345,678.1234568)", -12345678.12345678.format_s(:usd, :acct => true))
    assert_equal("$(123,456,789.123457)", -123456789.123456789.format_s(:usd, :acct => true))
    assert_equal("$(1,234,567,890.01235)", -1234567890.0123456789.format_s(:usd, :acct => true))
  end

  def test_negative_accountant_percent
    assert_equal("(1.1)%", -1.1.format_s(:percent, :acct => true))
    assert_equal("(12.12)%", -12.12.format_s(:percent, :acct => true))
    assert_equal("(123.123)%", -123.123.format_s(:percent, :acct => true))
    assert_equal("(1,234.1234)%", -1234.1234.format_s(:percent, :acct => true))
    assert_equal("(12,345.12345)%", -12345.12345.format_s(:percent, :acct => true))
    assert_equal("(123,456.123456)%", -123456.123456.format_s(:percent, :acct => true))
    assert_equal("(1,234,567.1234567)%", -1234567.1234567.format_s(:percent, :acct => true))
    assert_equal("(12,345,678.1234568)%", -12345678.12345678.format_s(:percent, :acct => true))
    assert_equal("(123,456,789.123457)%", -123456789.123456789.format_s(:percent, :acct => true))
    assert_equal("(1,234,567,890.01235)%", -1234567890.0123456789.format_s(:percent, :acct => true))
  end

  def test_negative_european_accountant_euro
    assert_equal("€(1,1)",  -1.1.format_s(:euro, :acct => true))
    assert_equal("€(12,12)",  -12.12.format_s(:euro, :acct => true))
    assert_equal("€(123,123)",  -123.123.format_s(:euro, :acct => true))
    assert_equal("€(1 234,1234)",  -1234.1234.format_s(:euro, :acct => true))
    assert_equal("€(12 345,12345)",  -12345.12345.format_s(:euro, :acct => true))
    assert_equal("€(123 456,123456)",  -123456.123456.format_s(:euro, :acct => true))
    assert_equal("€(1 234 567,1234567)",  -1234567.1234567.format_s(:euro, :acct => true))
    assert_equal("€(12 345 678,1234568)",  -12345678.12345678.format_s(:euro, :acct => true))
    assert_equal("€(123 456 789,123457)",  -123456789.123456789.format_s(:euro, :acct => true))
    assert_equal("€(1 234 567 890,01235)",  -1234567890.0123456789.format_s(:euro, :acct => true))
  end

  def test_negative_european_accountant
    assert_equal("(1,1)",  -1.1.format_s(:eu, :acct => true))
    assert_equal("(12,12)",  -12.12.format_s(:eu, :acct => true))
    assert_equal("(123,123)",  -123.123.format_s(:eu, :acct => true))
    assert_equal("(1 234,1234)",  -1234.1234.format_s(:eu, :acct => true))
    assert_equal("(12 345,12345)",  -12345.12345.format_s(:eu, :acct => true))
    assert_equal("(123 456,123456)",  -123456.123456.format_s(:eu, :acct => true))
    assert_equal("(1 234 567,1234567)",  -1234567.1234567.format_s(:eu, :acct => true))
    assert_equal("(12 345 678,1234568)",  -12345678.12345678.format_s(:eu, :acct => true))
    assert_equal("(123 456 789,123457)",  -123456789.123456789.format_s(:eu, :acct => true))
    assert_equal("(1 234 567 890,01235)",  -1234567890.0123456789.format_s(:eu, :acct => true))
  end

  def test_decimal_formatted
    assert_equal("1.1", 1.1.format_s(:us, :fd => true))
    assert_equal("12.12", 12.12.format_s(:us, :fd => true))
    assert_equal("123.123", 123.123.format_s(:us, :fd => true))
    assert_equal("1,234.123,4", 1234.1234.format_s(:us, :fd => true))
    assert_equal("12,345.123,45", 12345.12345.format_s(:us, :fd => true))
    assert_equal("123,456.123,456", 123456.123456.format_s(:us, :fd => true))
    assert_equal("1,234,567.123,456,7", 1234567.1234567.format_s(:us, :fd => true))
    assert_equal("12,345,678.123,456,8", 12345678.12345678.format_s(:us, :fd => true))
    assert_equal("123,456,789.123,457", 123456789.123456789.format_s(:us, :fd => true))
    assert_equal("1,234,567,890.012,35", 1234567890.0123456789.format_s(:us, :fd => true))
  end

  def test_negative_decimal_formatted
    assert_equal("-1.1", -1.1.format_s(:us, :fd => true))
    assert_equal("-12.12", -12.12.format_s(:us, :fd => true))
    assert_equal("-123.123", -123.123.format_s(:us, :fd => true))
    assert_equal("-1,234.123,4", -1234.1234.format_s(:us, :fd => true))
    assert_equal("-12,345.123,45", -12345.12345.format_s(:us, :fd => true))
    assert_equal("-123,456.123,456", -123456.123456.format_s(:us, :fd => true))
    assert_equal("-1,234,567.123,456,7", -1234567.1234567.format_s(:us, :fd => true))
    assert_equal("-12,345,678.123,456,8", -12345678.12345678.format_s(:us, :fd => true))
    assert_equal("-123,456,789.123,457", -123456789.123456789.format_s(:us, :fd => true))
    assert_equal("-1,234,567,890.012,35", -1234567890.0123456789.format_s(:us, :fd => true))
  end

end  # class TC_FormatNumber

