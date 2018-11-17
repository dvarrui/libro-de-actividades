#!/usr/bin/ruby

Dir.glob("*.png") do |i|
	n=i.split('.')
	n=n[0]+'.html'
	f=File.open(n.to_s,"w")
	f.write("<html></html>")
	f.close
end
