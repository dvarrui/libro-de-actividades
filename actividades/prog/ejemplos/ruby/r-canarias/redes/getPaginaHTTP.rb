#!/usr/bin/ruby

require 'net/http'
Net::HTTP.get_print 'www.iescesarmanrique.org', '/index.html'

