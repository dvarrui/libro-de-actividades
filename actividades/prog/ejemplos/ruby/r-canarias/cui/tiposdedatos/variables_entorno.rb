#!/usr/bin/ruby

 require 'importenv'
 p $USER
 p $HOME
 $USER = "matz"
 p ENV["USER"]
