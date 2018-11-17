#!/usr/bin/ruby

require 'operacion'
require 'test/unit'

class OperacionTest < Test::Unit::TestCase
  def test_numero1
    ope = Operacion.new
    ope.numero1 = 2
    assert_equal 2, ope.numero1
  end

  def test_numero2
    ope = Operacion.new
    ope.numero2 = 3
    assert_equal 3, ope.numero2
  end

  def test_sumar
    ope = Operacion.new
    ope.numero1=2
    ope.numero2=5
    assert_equal 7, ope.sumar
    ope.numero1=4
    ope.numero2=-6
    assert_equal -2, ope.sumar
  end
end
