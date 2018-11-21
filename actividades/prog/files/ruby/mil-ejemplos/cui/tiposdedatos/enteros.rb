class Integer
  def fac
   raise "Faculty undeﬁned for #{self}" if self < 0
   return (1..self).inject(1) { |result, i| result * i }
  end
end

puts (0..13).map { |i| i.fac }.join(', ')
