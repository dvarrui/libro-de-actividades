#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# =XMPP4R - XMPP Library for Ruby
# License:: Ruby's license (see the LICENSE file) or GNU GPL, at your option.
# Website::http://home.gna.org/xmpp4r/

module Jabber
  ##
  # This exception can be raised by Helpers when they
  # receive answers with <tt>type='error'</tt>
  #
  # The ErrorException carries a Jabber::Error element
  class ErrorException < RuntimeError
    ##
    # The error element which caused this exception
    attr_reader :error

    ##
    # Initialize an ErrorException
    # error:: [Error]
    def initialize(error)
      @error = error
    end

    ##
    # Textual output
    #
    # Sample:
    #  subscription-required: Please subscribe first
    def to_s
      "#{@error.error}: #{@error.text}"
    end
  end
end
