#!/usr/bin/env ruby

filenames = Dir.glob('images/*.png').sort

for filename in filenames
  puts "![](#{filename})"
end
