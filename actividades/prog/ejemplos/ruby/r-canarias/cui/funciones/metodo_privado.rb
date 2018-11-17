class Test
  def times_two(a)
          print a," dos veces es ",engine(a),"\n"
  end
  def engine(b)
          b*2
  end
  private:engine # esto oculta engine a los usuarios
end

t=Test.new
#t.engine(6)
t.times_two(6)


