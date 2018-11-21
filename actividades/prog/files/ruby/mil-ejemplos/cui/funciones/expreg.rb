# necesita un terminal ANSI!!!
st = "\033[7m"
en = "\033[m"
while TRUE
  print "str> "
  STDOUT.flush
  str = gets
  break if not str
  str.chop!
  print "pat> "
  STDOUT.flush
  re = gets
  break if not re
  re.chop!
  str.gsub! re, "#{st}\\&#{en}"
  print str, "\n"
end
print "\n"

