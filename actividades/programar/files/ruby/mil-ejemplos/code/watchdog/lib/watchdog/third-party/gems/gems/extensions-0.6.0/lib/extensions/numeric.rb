#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# == extensions/integer.rb
#
# Adds methods to the builtin Numeric and Integer classes.
#

require "extensions/_base"

#
# * Integer#even?
#
ExtensionsProject.implement(Integer, :even?) do
  class Integer
    #
    # Returns true if this integer is even, false otherwise.
    #   14.even?          # -> true
    #   15.even?          # -> false
    #
    def even?
      self % 2 == 0
    end
  end
end


#
# * Integer#odd?
#
ExtensionsProject.implement(Integer, :odd?) do
  class Integer
    #
    # Returns true if this integer is odd, false otherwise.
    #   -99.odd?          # -> true
    #   -98.odd?          # -> false
    #
    def odd?
      self % 2 == 1
    end
  end
end

#
# This code arose from discussions with Francis Hwang.  Leaving it here for future work.
#
#   class Numeric
#     def precision_format(nplaces, flag = :pad)
#       format = "%.#{nplaces}f"
#       result = sprintf(format, self)
#       case flag
#       when :pad
#       when :nopad
#         result.sub!(/\.?0*$/, '')
#       else
#         raise ArgumentError, "Invalid value for flag: #{flag.inspect}"
#       end
#       result
#     end
#   end
#
#   100.precision_format(2)                 # -> "100.00"
#   100.precision_format(2, :nopad)         # -> "100"
#   100.1.precision_format(2)               # -> "100.10"
#   100.1.precision_format(2, :nopad)       # -> "100.1"
#   100.1.precision_format(2, false)
#         # -> "ArgumentError: Invalid value for flag: false"
#


ExtensionsProject.implement(Numeric, :format_s) do
  #--
  # Copyright © 2003 Austin Ziegler
  #
  # Permission is hereby granted, free of charge, to any person obtaining a copy
  # of this software and associated documentation files (the "Software"), to
  # deal in the Software without restriction, including without limitation the
  # rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
  # sell copies of the Software, and to permit persons to whom the Software is
  # furnished to do so, subject to the following conditions:
  # 
  # The above copyright notice and this permission notice shall be included in
  # all copies or substantial portions of the Software.
  #
  # THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  # IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  # FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  # AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  # LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
  # FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
  # IN THE SOFTWARE.
  #++
  class Numeric
    #
    # Provides the base formatting styles for #format_s. See #format_s for
    # more details. Two keys provided that are not supported in the
    # #format_s arguments are:
    #
    # <tt>:style</tt>:: Allows a style to inherit from other styles. Styles
    #                   will be applied in oldest-first order in the event
    #                   of multiple inheritance layers.
    # <tt>:id</tt>::    This must be provided on any default style created
    #                   or provided so as to provide a stop marker so that
    #                   recursive styles do not result in an infinite loop.
    #
    # This is an implementation detail, not important for users of the class.
    #
    FORMAT_STYLES = {
      :us       => { :sep => ',', :dec => '.', :id => :us },
      :usd      => { :style => :us, :currency => { :id => "$", :pos => :before }, :id => :usd },
      :eu       => { :sep => ' ', :dec => ',', :id => :us },
      :euro     => { :style => :eu, :currency => { :id => "€", :pos => :before }, :id => :euro },
      :percent  => { :style => :us, :currency => { :id => "%%", :pos => :after }, :id => :percent }
    }

    #
    # Format a number as a string, using US or European conventions, and
    # allowing for the accounting format of representing negative numbers.
    # Optionally, currency formatting options can be provided.
    #
    # For example:
    #   x = -10259.8937
    #   x.format_s                         # => "-10,259.8937"
    #   x.format_s(:us)                    # => "-10,259.8937" 
    #   x.format_s(:usd)                   # => "$-10,259.8937"
    #   x.format_s(:eu)                    # => "-10 259,8937" 
    #   x.format_s(:euro)                  # => "€-10 259,8937"
    #   x.format_s(:us, :acct => true)     # => "(10,259.8937)"
    #   x.format_s(:eu, :acct => true)     # => "(10 259,8937)"
    #   x.format_s(:usd, :acct => true)    # => "$(10,259.8937)"
    #   x.format_s(:euro, :acct => true)   # => "€(10 259,8937)"
    #   x.format_s(:percent)               # => "-10,259.8937%"
    #
    # You may configure several aspects of the formatting by providing keyword
    # arguments after the country and accounting arguments. One example of that
    # is the :acct keyword. A more insane example is:
    #
    #   x = -10259.8937
    #   x.format_s(:us,
    #              :sep => ' ', :dec => ',',
    #              :neg => '<%s>', :size => 2, 
    #              :fd => true)                    # -> "<1 02 59,89 37>"
    # 
    # The keyword parameters are as follows:
    #
    # <tt>:acct</tt>::      If +true+, then use accounting style for negative
    #                       numbers. This overrides any value for
    #                       <tt>:neg</tt>.
    # <tt>:sep</tt>::       Default "," for US, " " for Euro. Separate the
    #                       number groups from each other with this string.
    # <tt>:dec</tt>::       Default "." for US, "," for Euro. Separate the
    #                       number's integer part from the fractional part
    #                       with this string.
    # <tt>:neg</tt>::       Default <tt>"-%s"</tt>. The format string used to
    #                       represent negative numbers. If <tt>:acct</tt> is
    #                       +true+, this is set to <tt>"(%s)"</tt>.
    # <tt>:size</tt>::      The number of digits per group. Defaults to
    #                       thousands (3).
    # <tt>:fd</tt>::        Indicates whether the decimal portion of the
    #                       number should be formatted the same way as the
    #                       integer portion of the number. ("fd" == "format
    #                       decimal".) Defaults to +false+.
    # <tt>:currency</tt>::  This is an optional hash with two keys,
    #                       <tt>:id</tt> and <tt>:pos</tt>. <tt>:id</tt> is
    #                       the string value of the currency (e.g.,
    #                       <tt>"$"</tt>, <tt>"€"</tt>, <tt>"USD&nbsp;"</tt>);
    #                       <tt>:pos</tt> is either <tt>:before</tt> or
    #                       <tt>:after</tt>, referring to the position of the
    #                       currency indicator. The default <tt>:pos</tt> is
    #                       <tt>:before</tt>.
    #
    def format_s(style = :us, configs={})
      style = FORMAT_STYLES[style].dup # Adopt US style by default.

        # Deal with recursive styles.
      if style[:style]
        styles = []
        s = style
        while s[:style]
          s = FORMAT_STYLES[s[:style]].dup
          styles << s
          break if s[:style] = s[:id]
        end
        styles.reverse_each { |s| style.merge!(s)  }
      end
        # Merge the configured style.
      style.merge!(configs)

      sm = style[:sep] || ','
      dp = style[:dec] || '.'
      if style[:acct]
        fmt = '(%s)'
      else
        fmt = style[:neg] || '-%s'
      end
      sz = style[:size] || 3
      format_decimal = style[:fd]
      ng = (self < 0)
      fmt = "%s" if not ng

      dec, frac = self.abs.to_s.split(/\./)

      dec.reverse!
      dec.gsub!(/\d{#{sz}}/) { |m| "#{m}#{sm}" }
      dec.gsub!(/#{sm}$/, '')
      dec.reverse!

      if format_decimal and not frac.nil?
        frac.gsub!(/\d{#{sz}}/) { |m| "#{m}#{sm}" }
        frac.gsub!(/#{sm}$/, '')
      end

      if frac.nil?
        val = dec
      else
        val = "#{dec}#{dp}#{frac}"
      end

      if style[:currency]
        if style[:currency][:pos].nil? or style[:currency][:pos] == :before
          fmt = "#{style[:currency][:id]}#{fmt}"
        elsif style[:currency][:pos] == :after
          fmt = "#{fmt}#{style[:currency][:id]}"
        end
      end

      fmt % val
    end
  end  # class Numeric
end  # ExtensionsProject.implement

