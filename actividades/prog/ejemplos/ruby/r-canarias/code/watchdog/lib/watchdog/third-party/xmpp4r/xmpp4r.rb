#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# =XMPP4R - XMPP Library for Ruby
# License:: Ruby's license (see the LICENSE file) or GNU GPL, at your option.
# Website::http://home.gna.org/xmpp4r/

##
# The Jabber module is the root namespace of the library. You might want
# to Include it in your script to ease your coding. It provides
# a simple debug logging support.
module Jabber
  # XMPP4R Version number
  XMPP4R_VERSION = '0.2'
end

require 'xmpp4r/client'
require 'xmpp4r/component'
require 'xmpp4r/debuglog'
