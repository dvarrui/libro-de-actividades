#!/usr/bin/env ruby

puts "[INFO] Executing avi2mp4..."

inputs = `ls *.avi`.split("\n").sort
inputs.each do |input|
  name = File.basename(input)[0, input.size-4]
  puts "[INFO] #{name}.avi => #{name}.mp4"
  system("ffmpeg -i #{name}.avi #{name}.mp4")
end
# ffmpeg -i input.avi -c:a aac -b:a 128k -c:v libx264 -crf 23 output.mp4

puts "[INFO] Finish!"
