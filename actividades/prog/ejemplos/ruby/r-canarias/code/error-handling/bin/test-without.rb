#!/usr/bin/ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

### The following adjusts the load path so that the correct version of
### a self-contained package is found, no matter where the script is
### run from. 
require 'pathname'
$:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
require 'error-handling/third-party/s4t-utils/load-path-auto-adjuster'


require 's4t-utils'
include S4tUtils

require 'error-handling'
# You probably want to include your module as well, but I won't assume
# that. 
# include ErrorHandling

if $0 == __FILE__

   without_pleasant_exceptions do
     File.open("no-such-file")
  end

end
