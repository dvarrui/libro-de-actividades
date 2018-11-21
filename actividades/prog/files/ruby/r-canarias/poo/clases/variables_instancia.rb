
class InstTest
  def set_foo(n)
          @foo = n
  end
  def set_bar(n)
          @bar = n
  end
  def info
     print self
     print " foo=",@foo
     print " bar=",@bar,"\n"
  end
end

i = InstTest.new
print i
print "\n"
i.info

puts defined? i

i.set_foo(2)
print i
print "\n"
i.info

i.set_bar(4)
print i 
print "\n"
i.info

