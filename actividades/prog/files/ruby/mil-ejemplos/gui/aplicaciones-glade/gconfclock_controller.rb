#!/usr/bin/env ruby
#
# gconfclock_controller.rb
#
# Copyright (c) 2003 Masao Mutoh
#
# You can redistribute it and/or modify it under the terms of
# the Ruby's licence.
#
require 'gconf2'

client = GConf::Client.new
loop {
	time = Time.now
	client["/apps/gconfclock/date"] = time.to_i
	puts time.strftime("%c")
	sleep 1
}
