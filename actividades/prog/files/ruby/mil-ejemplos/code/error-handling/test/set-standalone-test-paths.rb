#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'pathname'

PACKAGE_ROOT = Pathname.new(__FILE__).parent.parent.to_s
$:.unshift("#{PACKAGE_ROOT}/lib")
require 'error-handling/third-party/s4t-utils/load-path-auto-adjuster'
