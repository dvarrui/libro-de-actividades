#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Execute command wiht system(command)
# * Filter only real IP with grep and grep -v

system('ip a | grep "inet " | grep -v "host lo"')
