#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
Dir.glob("*.txt").each do |file|
  htmlfile = File.basename(file)[0...-(File.extname(file).size)]+".html"
  `wiki2html -b . -s doc.css -t "RubyGems" -o #{htmlfile} #{file}`
end