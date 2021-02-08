#!/usr/bin/ruby

input = `cat carpetas.txt`
subjects = input.split("\n")
base = subjects[0]
subjects.delete_at(0)

puts "[INFO] base     = #{base}"
puts "[INFO] subjects = #{subjects}"

system("mkdir #{base}")
subjects.each do |name|
  system("mkdir #{base}/#{name}")
end
puts "Finish!"
