#!/usr/bin/env ruby

require_relative '08-radar-lib'

ips = [ '127.0.0.1', '192.168.1.1', '8.8.4.4', '172.16.1.1']

max = 1
max = ARGV[0].to_i if not ARGV.nil?

(1..max).each do |i|
  data = scan ips
  show_results data
  puts "Iteración [#{i}/#{max}]"
end
