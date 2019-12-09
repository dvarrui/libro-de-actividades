#!/usr/bin/env ruby
# Target:
# * Show current IP configuration

system('ip a | grep "inet "')
