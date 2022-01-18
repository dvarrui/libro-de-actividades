#!/usr/bin/env ruby

options = [ 'destroy', 'halt', 'up', 'port']

if ARGV.size.zero?
  puts "Options: #{options.sort.join(', ')}"
  exit 1
end

def apply(action)
  filepaths = Dir.glob('*/Vagrantfile').sort

  filepaths.each do |filepath|
    dirname = File.dirname(filepath)
    puts "\n[vagrant #{action}] #{dirname}"
    system("cd #{dirname}; vagrant #{action}")
  end
end

if options.include? ARGV.first
  apply ARGV.first
  exit 0
end

puts "[ERROR] Unkown option #{ARGV.first}"
exit 2
