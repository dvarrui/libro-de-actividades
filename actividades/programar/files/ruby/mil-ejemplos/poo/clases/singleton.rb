
class SingletonTest
  def size
          print "25\n"
  end
end

test1 = SingletonTest.new
puts test1

test2 = SingletonTest.new
puts test2

def test2.size
  puts "10"
end

puts test1.size

puts test2.size


