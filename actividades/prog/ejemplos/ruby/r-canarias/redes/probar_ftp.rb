#!/usr/bin/ruby

require 'net/ftp'


ftp = Net::FTP.new('iescesarmanrique.org')
ftp.login 
#files = ftp.chdir('http_docs')
files = ftp.list('*')
files.each {|f| print f}

#ftp.getbinaryfile('nif.rb-0.91.gz', 'nif.gz', 1024)
ftp.close


