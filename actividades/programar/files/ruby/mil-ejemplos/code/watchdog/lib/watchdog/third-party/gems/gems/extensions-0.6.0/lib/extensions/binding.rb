#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/binding.rb
#
# Adds methods to the builtin Binding class.
#

require "extensions/_base"
require "extensions/continuation"

  #
  # Ruby's built-in Binding class doesn't contain any methods.  It is merely a "context" object
  # that can be used in calls to <tt>Kernel.eval</tt>, like this:
  #
  #   def example(_binding)
  #     return eval("x", _binding)
  #   end
  #
  #   x = 55
  #   current_binding = Kernel.binding
  #   example(current_binding)                # -> 55
  #
  # The most useful method introduced to Binding by the _extensions_ package is
  # Binding.of_caller.  It allows you to access the binding of the calling method, thus
  # enabling you to access local variables in that scope.  The other methods are a convenient
  # object-oriented facade for operations that you can already do with #eval as demonstrated
  # above.  Here is an example that showcases all of the Binding methods included in
  # _extensions_.
  #
  #   def example
  #     Binding.of_caller do |b|
  #       puts "x + y = #{b.eval('x + y')}"
  #       puts "x = #{b[:x]}"
  #       puts "Local variables: " + b.local_variables.join(', ')
  #       b[:y] += 1
  #       puts "Changed value of y in calling context to #{b[:y]}"
  #       puts "Is 'z' defined in calling context?  " + (b.defined?(:z) ? 'Yes' : 'No')
  #     end
  #   end
  #
  #   x = 5
  #   y = 17
  #   example
  #   y              # -> 18
  #
  # Binding.of_caller was written by Florian Gross.  The other methods were written by Tom
  # Sawyer.
  #
class Binding
end

#
# * Binding.of_caller
#
ExtensionsProject.implement(Binding, :of_caller, :class) do
  class Binding
      #
      # This method returns the binding of the method that called your method, enabling you to
      # access its local variables.  If you call it without being in a method, it will raise an
      # Exception.
      #
      # === Example
      #
      #   def inc_counter
      #     Binding.of_caller do |b|
      #       eval("counter += 1", b)
      #     end
      #     #              <--- line (A)
      #   end
      #   counter = 0
      #   inc_counter
      #   inc_counter
      #   counter           # -> 2
      #
      # === Warning
      #
      # <tt>Binding.of_caller</tt> must be the _last_ method call in the method.  For example,
      # if you insert some code at line *A* in the example above, an Exception will be raised.
      # You'll get away with a simple assignment, but anything involving a method call is
      # trouble.
      #
      # === Explanation
      #
      # It works by installing a temporary trace_func (see Kernel.set_trace_func).  This makes
      # available -- to the trace function -- the binding of a method after it has returned.
      # Using a continuation, <tt>Binding.of_caller</tt> will let _your_ method return,
      # retrieve the binding, and return to the <tt>of_caller</tt> call with that binding in
      # hand.  This time it executes the block.
      #
      # Because it is actually running <tt>Binding.of_caller</tt> twice, and returning from
      # your method twice, any code between the <tt>of_caller</tt> call and the end of your
      # method will be run twice.  This is obviously not desirable, so an Exception is raised
      # if any code is found.
      #
      # See the thread around ruby-talk:109607 for more discussion.
      #
      # === Extra Warning
      #
      # If you have a trace function in place, <tt>Binding.of_caller</tt> will destroy that.
      # Ruby does not allow you to access the current trace function, so it can't be restored
      # afterwards.  XXX: will this clash with the profiler and/or debugger?
      #
      # === Credits
      #
      # <tt>Binding.of_caller</tt> was written by Florian Frank.
      #
    def Binding.of_caller(&block)
      old_critical = Thread.critical
      Thread.critical = true
      count = 0
      cc, result, error = Continuation.create(nil, nil)
      error.call if error

      tracer = lambda do |*args|
        type, context = args[0], args[4]
        if type == "return"
          count += 1
          # First this method and then calling one will return --
          # the trace event of the second event gets the context
          # of the method which called the method that called this
          # method.
          if count == 2
            # It would be nice if we could restore the trace_func
            # that was set before we swapped in our own one, but
            # this is impossible without overloading set_trace_func
            # in current Ruby.
            set_trace_func(nil)
            cc.call(eval("binding", context), nil)
          end
        elsif type != "line"
          set_trace_func(nil)
          error_msg = "Binding.of_caller used in non-method context or " +
            "trailing statements of method using it aren't in the block."
          cc.call(nil, lambda { raise(Exception, error_msg ) })
        end
      end

      unless result
        set_trace_func(tracer)
        return nil
      else
        Thread.critical = old_critical
        yield result
      end
    end
  end  # class Binding
end


#
# * Binding#eval
#
ExtensionsProject.implement(Binding, :eval, :instance) do
  class Binding
      #
      # Evaluates the given string in the context of this binding.
      #
    def eval(str)
      Kernel.eval(str, self)
    end
  end
end


#
# * Binding#local_variables
#
ExtensionsProject.implement(Binding, :local_variables, :instance) do
  class Binding
      #
      # Returns the variables that are local to this binding.
      #
    def local_variables
      self.eval('local_variables')
    end
  end
end


#
# * Binding#[]
#
ExtensionsProject.implement(Binding, :[], :instance) do
  class Binding
      #
      # Returns the value of the given variable in this binding.
      #
    def [](variable)
      self.eval(variable.to_s)
    end
  end
end


#
# * Binding#[]=
#
ExtensionsProject.implement(Binding, :[]=, :instance) do
  class Binding
      #
      # Sets the given variable (in this binding) to the given value.
      #
    def []=(variable, value)
      self.eval("lambda { |v| #{variable} = v }").call(value)
    end
  end
end


#
# * Binding#defined?
#
ExtensionsProject.implement(Binding, :defined?, :instance) do
  class Binding
      #
      # Evaluates <tt>defined?</tt> in this binding.
      #
    def defined?(variable)
      self.eval("defined?(#{variable})")
    end
  end
end


