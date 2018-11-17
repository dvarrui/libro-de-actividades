#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module Test
  module Unit

    module Assertions
      def assert_true(boolean, message = nil)
        assert(boolean, message)
      end

      def assert_false(boolean, message = nil)
        _wrap_assertion do
          assert_block(build_message(message, "<?> should be false or nil.", boolean)) { !boolean }
        end
      end

      def assert_raise_with_matching_message(exception_class, message, &block)
        exception = assert_raise(exception_class, &block)
        assert_match(message, exception.message)
      end
      alias_method :assert_raises_with_matching_message,
                   :assert_raise_with_matching_message

      def assert_wants_to_exit
        assert_raise(SystemExit) do
          yield
        end
      end
    end
  end
end
