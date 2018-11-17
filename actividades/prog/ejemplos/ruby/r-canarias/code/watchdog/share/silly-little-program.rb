#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
puts 'I am a silly little program.'
puts 'Here are my arguments:'
puts "   #{ARGV.join(' ')}"
puts "I mostly write to standard output."
$stdout.flush
$stderr.puts "I also write to standard error."
