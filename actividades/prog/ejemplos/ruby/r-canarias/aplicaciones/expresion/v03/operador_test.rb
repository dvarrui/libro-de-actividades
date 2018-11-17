#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070827
#

require 'item'
require 'operador'
require 'test/unit'

class OperadorTest < Test::Unit::TestCase
  def test_suma
    o = Operador.new
    ve = []
    ve << Item.new('3')
    ve << Item.new('+')
    ve << Item.new('4')
    e = Array.new ve
    traza = []
    assert o.numero?(e[0].valor)
    assert o.operador?(e[1].valor)
    assert o.numero?(e[2].valor)
    assert_equal o.expr2str(e), "3 + 4"
    assert o.calcular(1,e,traza)
    assert_equal e[0].valor, "7.0"
    e = Array.new ve
    assert o.num_op_num(1,e)
    assert_equal e[0].valor, "7.0"
  end

  def test_seno
    o = Operador.new
    ve = []
    ve << Item.new('sen')
    ve << Item.new('0')
    e = Array.new ve
    traza = []
    assert o.operador?(e[0].valor)
    assert o.numero?(e[1].valor)
    assert_equal o.expr2str(e), "sen 0"
    assert o.calcular(0,e,traza)
    assert_equal e[0].valor, "0.0"
    e = Array.new ve
    assert o.op_num(0,e)
    assert_equal e[0].valor, "0.0" 
  end

  def test_parentesis
    o = Operador.new
    ve = []
    ve << Item.new('(')
    ve << Item.new('16')
    ve << Item.new(')')
    e = Array.new ve
    traza = []
    assert o.parentesis?(e[0].valor)
    assert o.numero?(e[1].valor)
    assert o.parentesis?(e[2].valor)
    assert_equal o.expr2str(e), "( 16 )"
    assert o.calcular(0,e,traza)
    assert_equal e[0].valor, "16"
    e = Array.new ve
    assert o.parentesis(0,e)
    assert_equal e[0].valor, "16" 
  end
end

