#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 0.2.0 20080108
#

require 'clases/polinomio'
require 'test/unit'

class PolinomioTest < Test::Unit::TestCase

  def test_constructor
    p = Polinomio.new 2
    assert_equal p.grado, 2
    assert_equal p.get_coeficiente(0), 0
    assert_equal p.get_coeficiente(1), 0
    assert_equal p.get_coeficiente(2), 0
    assert_equal p.get_coeficientes(), "0,0,0"
    assert_equal p.to_s, "0"
  end

  def test_setterandgetter
    p = Polinomio.new 2
    p.set_coeficientes("1,2,3")
    assert_equal p.grado, 2
    assert_equal p.get_coeficientes, "1,2,3"
    assert_equal p.get_coeficiente(0), 1
    assert_equal p.get_coeficiente(1), 2
    assert_equal p.get_coeficiente(2), 3
    p.set_coeficientes("4,3,2,1")
    assert_equal p.grado, 3
    assert_equal p.get_coeficientes, "4,3,2,1"
  end

  def test_fdex
    p = Polinomio.new 
    p.set_coeficientes("-1,1")
    assert_equal p.fdex(-1), -2
    assert_equal p.fdex(0), -1
    assert_equal p.fdex(1), 0
    assert_equal p.fdex(2), 1
  end

  def test_sumar
    p1 = Polinomio.new
    p2 = Polinomio.new
    p1.set_coeficientes("1,0,2")
    assert_equal p1.to_s, "+2x^2 +1"

    p2.set_coeficientes("-3,-2,1")
    assert_equal p2.to_s, "+x^2 -2x -3"
    p1.sumar(p2)
    assert_equal p1.es_igual?(p2), false
    assert_equal p1.es_igual?(p1), true
    assert_equal p1.get_coeficientes, "-2,-2,3"
  end

  def test_multiplicar
    p = Polinomio.new
    p.set_coeficientes("-1,0,2")
    assert_equal p.get_coeficientes, "-1,0,2"
    assert_equal p.to_s, "+2x^2 -1"
    p.multiplicar(2)
    assert_equal p.get_coeficientes, "-2,0,4"
    assert_equal p.to_s, "+4x^2 -2"
  end

end

