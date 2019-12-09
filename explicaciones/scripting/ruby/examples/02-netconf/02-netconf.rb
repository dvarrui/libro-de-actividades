#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Filter only real IP

system('ip a | grep "inet " | grep -v "host lo"')
