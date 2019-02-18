#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/continuation.rb
#
# Adds methods to the builtin Continuation class. 
#

require "extensions/_base"

#
# * Continuation.create
#
ExtensionsProject.implement(Continuation, :create, :class) do
  class Continuation
    #
    # <tt>Continuation.create</tt> offers a nicer interface for creating continuations than
    # <tt>Kernel.callcc</tt>.
    # 
    # === Example
    #
    # Count down from 10 to 0 using a continuation.
    #
    #   continuation, counter = Continuation.create(10)
    #   puts counter
    #   continuation.call(counter - 1) if counter > 0
    #
    # Implement a workalike of <tt>Array#inject</tt> using continuations.  For simplicity's
    # sake, this is not fully compatible with the real <tt>#inject</tt>.
    #
    #   class Array
    #     def cc_inject( value=nil )
    #       copy = self.clone
    #       cc, result, item = Continuation.create( value, nil )
    #       next_item = copy.shift 
    #       if result and item
    #         cc.call( yield(result, item), next_item ) 
    #       elsif next_item
    #         cc.call( next_item, result ) 
    #       end
    #       result 
    #     end 
    #   end
    #
    #   [1,2,3,4,5].cc_inject { |acc, n| acc + n }       # -> 15
    #
    # === Explanation
    #
    # I've got no idea how it works.  TODO: work it out.  In particular, what do the arguments
    # do?  And what the hell is going on in #cc_inject???!?
    #
    # === See Also
    #
    # This method is included in the 'extensions' package primarily to support
    # Binding.of_caller.
    #
    # === Credits
    #
    # <tt>Continuation.create</tt> was written and demonstrated by Florian Gross.  See
    # ruby-talk:94681.
    #
    def Continuation.create(*args, &block)
      cc = nil
      result = callcc { |c|
        cc = c
        block.call(cc) if block and args.empty?
      }
      result ||= args
      return *[cc, *result]
    end
  end
end
