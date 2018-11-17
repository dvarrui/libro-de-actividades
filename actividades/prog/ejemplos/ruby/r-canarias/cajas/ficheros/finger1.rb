#!/usr/bin/ruby

File::open("/etc/passwd").each{|linea| print linea }
