#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# $GEM_PATH ||= []
# $GEM_PATH.unshift(File.join(File.dirname(File.expand_path(__FILE__)), "mock", "gems"))
require 'rubygems'
Gem::manage_gems
Gem.use_paths("test/mock/gems")
