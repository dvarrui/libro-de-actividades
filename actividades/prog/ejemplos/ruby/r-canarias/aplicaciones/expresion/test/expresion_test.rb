#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070830
#

require 'apps/expresion'
require 'test/unit'

class ExpresionTest < Test::Unit::TestCase

  def test_parametros
    e = Expresion.new "4 + 3"
    v = ['4','+','3']
    assert_equal e.expr_inicio, "4 + 3"
    assert_equal e.expr_actual, "4 + 3"
    e.evaluar
    assert_equal e.expr_inicio, "4 + 3"
    assert_equal e.expr_actual, "7.0"
  end

  def test_suma
    e = Expresion.new "4 + 3"
    assert_equal e.evaluar, "7.0"
    e = Expresion.new "2.2 + 1.1 + 3.3"
    assert_equal e.evaluar, "6.6"
  end

  def test_resta
    e = Expresion.new "4 - 3"
    assert_equal e.evaluar, "1.0"
    e = Expresion.new "3 - 4"
    assert_equal e.evaluar, "-1.0"
  end

  def test_multiplicacion
    e = Expresion.new "4 * 3"
    assert_equal e.evaluar, "12.0"
    e = Expresion.new "2.0 * 3.0"
    assert_equal e.evaluar, "6.0"
    e = Expresion.new "0.5 * -4"
    assert_equal e.evaluar, "-2.0"
    e = Expresion.new "-1 * -2.0 * 3"
    assert_equal e.evaluar, "6.0"
  end

  def test_division
    e = Expresion.new "6 / 3"
    assert_equal e.evaluar, "2.0"
    e = Expresion.new "3 / 6"
    assert_equal e.evaluar, "0.5"
  end

  def test_seno
    e = Expresion.new "sen 0"
    assert_equal e.evaluar, "0.0"
  end

  def test_paso_a_paso
    e = Expresion.new "4 + 3 * 2"
    assert e.evaluar1op
    assert_equal e.expr_actual, "4 + 6.0"
    assert e.evaluar1op
    assert_equal e.expr_actual, "10.0"
    assert e.expr_actual, e.evaluar
    e = Expresion.new "4 - 3 * -2"
    assert e.evaluar1op
    assert_equal e.expr_actual, "4 - -6.0"
    assert e.evaluar1op
    assert_equal e.expr_actual, "10.0"
    e = Expresion.new "3 + 6 / 3"
    assert e.evaluar1op
    assert_equal e.expr_actual, "3 + 2.0"
    assert e.evaluar1op
    assert_equal e.expr_actual, "5.0"
  end

  def test_parentesis
    e = Expresion.new "( 3 ) + 4 / ( 2 )"
    assert_equal e.evaluar, "5.0"
    e = Expresion.new "( 4 + 3 ) * 2"
    assert_equal e.evaluar, "14.0"
    e = Expresion.new "( 4 + ( 3 ) ) * ( 2 / 1 )"
    assert_equal e.evaluar, "14.0"
    e = Expresion.new "( -1 ) * ( 16 ) "
    assert_equal e.evaluar, "-16.0"
  end
end

