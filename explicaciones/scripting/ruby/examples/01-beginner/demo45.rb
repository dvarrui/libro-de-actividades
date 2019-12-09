#!/usr/bin/ruby

input = `cat carpetas.txt`
subjects = input.split("\n")
root = subjects[0]
subjects.delete(0)

puts "[INFO] root     = #{root}"
puts "[INFO] subjects = #{subjects}"

system("mkdir #{root}")
subjects.each do |name|
  system("mkdir #{root}/#{name}")
end
puts "Finish!"
