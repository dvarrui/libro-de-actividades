#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 200708026
#

require 'item'
require 'test/unit'

class ItemTest < Test::Unit::TestCase
  def test_atributos
    i = Item.new
    assert_equal i.valor, ''
    assert_equal i.nivel, 0
    i = Item.new('+',1)
    assert_equal i.valor, '+'
    assert_equal i.nivel, 1
    i = Item.new('16',2)
    assert_equal i.valor, '16'
    assert_equal i.nivel, 2
    assert_equal i.to_f, 16.0
  end

  def test_to_f
    i = Item.new('16',4)
    assert_equal i.to_f, 16.0
    assert_equal i.nivel, 4
  end

  def test_to_s
    i = Item.new('99',3)
    assert_equal i.to_s,"99"
    assert_equal i.nivel, 3
  end

end

