#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Execute command wiht system(command)

system('ip a | grep "inet "')
