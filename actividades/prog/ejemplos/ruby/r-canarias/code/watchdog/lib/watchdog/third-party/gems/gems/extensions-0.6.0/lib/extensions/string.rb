#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# == extensions/string.rb
#
# Adds methods to the builtin String class.
#

require "extensions/_base"


ExtensionsProject.implement(String, :leftmost_indent) do
  class String
    #
    # Returns the size of the smallest indent of any line in the string.
    # Emits a warning if tabs are found, and if <tt>$VERBOSE</tt> is on.
    # You can use #expand_tabs to avoid this.  This method is primarily intended
    # for use by #tabto and is not likely to be all that useful in its own
    # right.
    #
    def leftmost_indent
      tabs_found = false
      scan(/^([ \t]*)\S/).flatten.map { |ws|
        tabs_found = true if ws =~ /\t/
        ws.size
      }.compact.min
    ensure
      if tabs_found and $VERBOSE
        $stderr.puts %{
String#leftmost_indent: warning: tabs treated as spaces
  (value: #{self.inspect[0..30]}...")
          }.strip
      end
    end
    protected :leftmost_indent
  end
end


ExtensionsProject.implement(String, :expand_tabs) do
  class String
    #
    # Expands tabs to +n+ spaces.  Non-destructive.  If +n+ is 0, then tabs are
    # simply removed.  Raises an exception if +n+ is negative.
    #
    #--
    # Thanks to GGaramuno for a more efficient algorithm.  Very nice.
    def expand_tabs(n=8)
      n = n.to_int
      raise ArgumentError, "n must be >= 0" if n < 0
      return gsub(/\t/, "") if n == 0
      return gsub(/\t/, " ") if n == 1
      str = self.dup
      while
        str.gsub!(/^([^\t\n]*)(\t+)/) { |f|
          val = ( n * $2.size - ($1.size % n) )
          $1 << (' ' * val)
        }
      end
      str
    end
  end
end


ExtensionsProject.implement(String, :indent) do
  class String
    #
    # Indents the string +n+ spaces.
    #
    def indent(n)
      n = n.to_int
      return outdent(-n) if n < 0
      gsub(/^/, " "*n)
    end
  end
end


ExtensionsProject.implement(String, :outdent) do
  class String
    #
    # Outdents the string +n+ spaces.  Initial tabs will cause problems and
    # cause a warning to be emitted (if warnings are on).  Relative indendation
    # is always preserved.  Once the block hits the beginning of the line,
    # that's it.  In the following example, <tt>.</tt> represents space from the
    # beginning of the line.
    #
    #   str = %{
    #   ..One
    #   ....Two
    #   }.outdent(4)
    #
    # is
    #
    #   One
    #   ..Two
    #
    def outdent(n)
      n = n.to_int
      return indent(-n) if n < 0
      tabto(leftmost_indent - n)
    end
  end
end


ExtensionsProject.implement(String, :tabto) do
  class String
    #
    # Move the string to the <tt>n</tt>th column.  Relative indentation is preserved.
    # Column indices begin at 0, so the result is that the leftmost character of
    # the string has +n+ spaces before it.
    #
    # Examples:
    #   "xyz".tabto(0)           # -> "xyz"
    #   "xyz".tabto(1)           # -> " xyz"
    #   "xyz".tabto(2)           # -> "  xyz"
    #   "   xyz".tabto(1)        # -> " xyz"
    #
    #   str = <<EOF
    #       Hello, my name
    #     is Gerald.
    #   EOF
    #   str.tabto(5) == <<EOF    # -> true
    #          Hello, my name
    #        is Gerald.
    #   EOF
    #
    def tabto(n)
      n = n.to_int
      n = 0 if n < 0
      find = " " * leftmost_indent()
      replace = " " * (n)
      gsub(/^#{find}/, replace)
    end
  end
end


ExtensionsProject.implement(String, :taballto) do
  class String
    #
    # Tabs all lines in the string to column +n+.  That is, relative indentation
    # is _not_ preserved.
    #
    def taballto(n)
      n = n.to_int
      n = 0 if n < 0
      gsub(/^[ \t]*/, " "*n)
    end
  end
end


ExtensionsProject.implement(String, :trim) do
  class String
    #
    # Trims a string:
    # - removes one initial blank line
    # - removes trailing spaces on each line
    # - if +margin+ is given, removes initial spaces up to and including
    #   the margin on each line, plus one space
    #
    # This is designed specifically for working with inline documents.
    # Here-documents are great, except they tend to go against the indentation
    # of your code.  This method allows a convenient way of using %{}-style
    # documents.  For instance:
    #
    #   USAGE = %{
    #     | usage: prog [-o dir] -h file...
    #     |   where
    #     |     -o dir         outputs to DIR
    #     |     -h             prints this message
    #   }.trim("|")
    #
    #   # USAGE == "usage: prog [-o dir] -h file...\n  where"...
    #   # (note single space to right of margin is deleted)
    #
    # Note carefully that if no margin string is given, then there is no
    # clipping at the beginning of each line and your string will remain
    # indented.  You can use <tt>tabto(0)</tt> to align it with the left of
    # screen (while preserving relative indentation).
    #
    #   USAGE = %{
    #     usage: prog [-o dir] -h file...
    #       where
    #         -o dir         outputs to DIR
    #         -h             prints this message
    #   }.trim.tabto(0)
    #
    #   # USAGE == (same as last example)
    #
    def trim(margin=nil)
      s = self.dup
      # Remove initial blank line.
      s.sub!(/\A[ \t]*\n/, "")
      # Get rid of the margin, if it's specified.
      unless margin.nil?
        margin_re = Regexp.escape(margin || "")
        margin_re = /^[ \t]*#{margin_re} ?/
        s.gsub!(margin_re, "")
      end
      # Remove trailing whitespace on each line
      s.gsub!(/[ \t]+$/, "")
      s
    end
  end
end


ExtensionsProject.implement(String, :starts_with?) do
  class String
    #
    # Returns true iff this string starts with +str+.
    #    "Hello, world".starts_with?("He")         # -> true
    #    "Hello, world".starts_with?("Green")      # -> false
    #
    def starts_with?(str)
      str = str.to_str
      head = self[0, str.length]
      head == str
    end
  end
end


ExtensionsProject.implement(String, :ends_with?) do
  class String
    #
    # Returns true iff this string ends with +str+.
    #    "Hello, world".ends_with?(", world")    # -> true
    #    "Hello, world".ends_with?("Green")      # -> false
    #
    def ends_with?(str)
      str = str.to_str
      tail = self[-str.length, str.length]
      tail == str      
    end
  end
end


ExtensionsProject.implement(String, :line) do
  class String
    #
    # Returns a line or lines from the string.  +args+ can be a single integer,
    # two integers or a range, as per <tt>Array#slice</tt>.  The return value is
    # a single String (a single line), an array of Strings (multiple lines) or
    # +nil+ (out of bounds).  Note that lines themselves do not contain a
    # trailing newline character; that is metadata.  Indexes out of bounds are
    # ignored.
    #
    #   data = " one \n two \n three \n four \n five \n"
    #   data.line(1)              # -> " two "
    #   data.line(0,1)            # -> [" one "]
    #   data.line(3..9)           # -> [" four ", " five "]
    #   data.line(9)              # -> nil
    #
    def line(*args)
      self.split(/\n/).slice(*args)
    rescue TypeError
      raise TypeError,
        "String#line(*args): args must be one Integer, two Integers or a Range"
    rescue ArgumentError
      raise ArgumentError,
        "String#line(*args): args must be one Integer, two Integers or a Range"
    end
  end
end


ExtensionsProject.implement(String, :cmp) do
  class String
    #
    # Compare this string to +other+, returning the first index at which they
    # differ, or +nil+ if they are equal.
    #
    #   "practise".cmp("practice")    # -> 6
    #   "noun".cmp("nouns")           # -> 5 (and vice versa) 
    #   "fly".cmp("fly")              # -> nil 
    #
    def cmp(other)
      other = other.to_str
      if self == other
        return nil
      else
        n = [self.size, other.size].min
        (0..n).each do |i|
          return i unless self[i] == other[i]
        end
      end
    end
  end
end


ExtensionsProject.implement(String, :join) do
  class String
    #
    # Join all the lines of the string together, and compress spaces.  The resulting string
    # will have no surrounding whitespace.
    #
    #   text = %{
    #     Once upon a time,
    #          Little Red Riding Hood ...
    #
    #   }
    #
    #   text.join   # -> "Once upon a time, Little Red Riding Hood ..."
    #
    def join
      gsub(/([ \t]*\n[ \t]*)+/, ' ').strip
    end
  end
end
