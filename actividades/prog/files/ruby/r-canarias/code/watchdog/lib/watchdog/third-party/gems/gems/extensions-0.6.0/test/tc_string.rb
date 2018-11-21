#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

  require 'test/unit'
  require 'extensions/string'

  #
  # Create the setup method for the unit tests, so they can share the
  # same data.
  #
  module TC_String_Setup
    def setup
      @tabs = <<-EOF

\tOne tab
 \tOne space and one tab
      \t Six spaces, a tab, and a space
      EOF

      @usage = %{
        | usage: prog [-o dir] [-h] file...
        |   where
        |     -o dir         outputs to DIR
        |     -h             prints this message
      }

      @poem1 = <<-EOF
    I must go down to the seas again
      The lonely sea and the sky
    And all I want is a tall ship
      And a star to steer her by
      EOF

      @poem2 = <<-EOF
         "Eek!"
      She cried
        As the mouse quietly scurried
    by.
      EOF

      @poem3 = <<-EOF
        * I must go down to the seas again
        *   The lonely sea and the sky
        * And all I want is a tall ship
        *   And a star to steer her by
      EOF
    end  # def setup
  end  # module TC_String_Setup

  #
  # Test the expand_tabs method.
  #
  class TC_String_1 < Test::Unit::TestCase

    include TC_String_Setup

    def test_expand_tabs_1
      expected = <<-EOF

        One tab
        One space and one tab
         Six spaces, a tab, and a space
      EOF
      assert_equal(expected, @tabs.expand_tabs)
      assert_equal(expected, @tabs.expand_tabs(8))
    end

    def test_expand_tabs_2
      expected = <<-EOF

    One tab
    One space and one tab
         Six spaces, a tab, and a space
      EOF
      assert_equal(expected, @tabs.expand_tabs(4))
    end

    def test_expand_tabs_3
      expected = <<-EOF

                One tab
                One space and one tab
                 Six spaces, a tab, and a space
      EOF
      assert_equal(expected, @tabs.expand_tabs(16))
    end

    def test_expand_tabs_4
      expected = <<-EOF

 One tab
  One space and one tab
        Six spaces, a tab, and a space
      EOF
      assert_equal(expected, @tabs.expand_tabs(1))
    end

    def test_expand_tabs_5
      expected = <<-EOF

One tab
 One space and one tab
       Six spaces, a tab, and a space
      EOF
      assert_equal(expected, @tabs.expand_tabs(0))
    end
  end  # class TC_String_1


  # ===========================================================================


  #
  # Test the indent-style methods.
  #
  class TC_String_2 < Test::Unit::TestCase

    include TC_String_Setup

    def test_indent_0
      assert_equal("xyz", "xyz".indent(-1))
      assert_equal("xyz", "xyz".indent(0))
      assert_equal(" xyz", "xyz".indent(1))
      assert_equal("  xyz", "xyz".indent(2))
      assert_equal("   xyz", "xyz".indent(3))
      assert_equal("     xyz", "xyz".indent(3).indent(2))
    end

    def test_indent_1
      expected = <<-EOF
        I must go down to the seas again
          The lonely sea and the sky
        And all I want is a tall ship
          And a star to steer her by
      EOF
      actual = @poem1.indent(4)
      assert_equal(expected, actual)
    end

    def test_indent_2
      expected = <<-EOF
   I must go down to the seas again
     The lonely sea and the sky
   And all I want is a tall ship
     And a star to steer her by
      EOF
      actual = @poem1.indent(-1)
      assert_equal(expected, actual)
    end

    def test_outdent_0
      assert_equal("    xyz", "   xyz".outdent(-1))
      assert_equal("   xyz", "   xyz".outdent(0))
      assert_equal("  xyz", "   xyz".outdent(1))
      assert_equal(" xyz", "   xyz".outdent(2))
      assert_equal("xyz", "   xyz".outdent(3))
      assert_equal("xyz", "   xyz".outdent(4))
    end

    def test_outdent_1
      expected = <<-EOF
   I must go down to the seas again
     The lonely sea and the sky
   And all I want is a tall ship
     And a star to steer her by
      EOF
      actual = @poem1.outdent(1)
      assert_equal(expected, actual)
    end

    def test_outdent_2
      expected = <<-EOF
I must go down to the seas again
  The lonely sea and the sky
And all I want is a tall ship
  And a star to steer her by
      EOF
      actual = @poem1.outdent(6)
      assert_equal(expected, actual)
    end

    def test_outdent_3
      str = @poem1.gsub(/^ *\|/, "")   # Remove |s to test relative indentation
      expected = <<-EOF
I must go down to the seas again
  The lonely sea and the sky
And all I want is a tall ship
  And a star to steer her by
      EOF
      actual = str.outdent(6)
      assert_equal(expected, actual)
    end

    def test_outdent_4
      expected = <<-EOF
     "Eek!"
  She cried
    As the mouse quietly scurried
by.
      EOF
      actual = @poem2.outdent(100)
      assert_equal(expected, actual)
    end

    def test_tabto_0
      assert_equal("xyz",  "xyz".tabto(0))
      assert_equal(" xyz",  "xyz".tabto(1))
      assert_equal("  xyz", "xyz".tabto(2))
      assert_equal("  xyz", "      xyz".tabto(2))
    end

    def test_tabto_1
      expected = <<-EOF
   I must go down to the seas again
     The lonely sea and the sky
   And all I want is a tall ship
     And a star to steer her by
      EOF
      actual = @poem1.tabto(3)
      assert_equal(expected, actual)
    end
    
    def test_tabto_2
      expected = <<-EOF
     "Eek!"
  She cried
    As the mouse quietly scurried
by.
      EOF
      actual = @poem2.tabto(-5)
      assert_equal(expected, actual)
    end
    
    def test_tabto_3
      expected = <<-EOF
               "Eek!"
            She cried
              As the mouse quietly scurried
          by.
      EOF
      actual = @poem2.tabto(10)
      assert_equal(expected, actual)
    end

    def test_tabto_4
      # Note blank lines in data.  Should not prevent #tabto from doing its work.
      str = <<-EOF

        abc

        123
      EOF
      expected = <<-EOF

  abc

  123
      EOF
      actual = str.tabto(2)
      assert_equal(expected, actual)
    end

    def test_tabto_5
      # Note blank lines in data.  Should not prevent #tabto from doing its work.
      str = <<EOF

1.  All files (summary) in alphabetical order.
      * General report on everything.

2.  All 'core' files (summary) in alphabetical order.
      * Shows what we've achieved and what's left to be done.
EOF

      expected = <<EOF
  
  1.  All files (summary) in alphabetical order.
        * General report on everything.
  
  2.  All 'core' files (summary) in alphabetical order.
        * Shows what we've achieved and what's left to be done.
EOF
      assert_equal(expected, str.tabto(2))
    end

    def test_taballto_0
      assert_equal("xyz", " xyz".taballto(-1))
      assert_equal("xyz", "xyz".taballto(0))
      assert_equal("xyz", "   xyz".taballto(0))
      assert_equal("   foo\n   bar\n", "foo\n      bar\n".taballto(3))
    end

    def test_taballto_1
      expected = <<-EOF
      "Eek!"
      She cried
      As the mouse quietly scurried
      by.
      EOF
      assert_equal(expected, @poem2.taballto(6))
    end
    
  end  # class TC_String_2


  # ===========================================================================


  #
  # Test the trim method.
  #
  class TC_String_3 < Test::Unit::TestCase
    
    include TC_String_Setup

    def test_trim_0
      assert_equal("",         "".trim)
      assert_equal("xyz",      "xyz".trim)
      assert_equal("xyz",      "\nxyz".trim)
      assert_equal("\nxyz",    "\n\nxyz".trim)
      assert_equal("xyz",      "   \nxyz".trim)
      assert_equal("  xyz",    "  xyz  ".trim)
      assert_equal("yz",       "\n   xyz".trim('x'))
      assert_equal("yz",       "\nxyz".trim('x'))
      assert_equal("xyz",      "|xyz".trim('|'))
      assert_equal("xyz",      "| xyz".trim('|'))
      assert_equal(" xyz",      "|  xyz".trim('|'))
      assert_equal("xyz",      "|  xyz".trim('| '))
      assert_equal("",         "   \n              | ".trim("| "))
      assert_equal("   xyz\n   xyz\n", "   \n   xyz\n   xyz\n".trim('|'))
    end

    def test_trim_1
      expected = <<-EOF
I must go down to the seas again
  The lonely sea and the sky
And all I want is a tall ship
  And a star to steer her by
      EOF
      actual = @poem3.trim('*')
      assert_equal(expected, actual)
    end

    def test_trim_2
      expected = <<EOF
usage: prog [-o dir] [-h] file...
  where
    -o dir         outputs to DIR
    -h             prints this message
EOF
      assert_equal(expected, @usage.trim("|"))
    end

    def test_trim_3
      expected = <<EOF
        | usage: prog [-o dir] [-h] file...
        |   where
        |     -o dir         outputs to DIR
        |     -h             prints this message
EOF
      assert_equal(expected, @usage.trim)
    end

  end  # class TC_String_3


  # ===========================================================================


  #
  # Test the starts_with?, ends_with?, line, cmp, and join methods.
  #
  class TC_String_4 < Test::Unit::TestCase

    include TC_String_Setup

    def test_starts_with
      assert(@poem1.strip.starts_with?("I must go down"))
      assert(! @poem1.strip.starts_with?("Hello stranger"))
    end

    def test_ends_with
      assert(@poem1.strip.ends_with?("steer her by"))
      assert(! @poem1.strip.ends_with?("I must be going"))
    end

    def test_line
      data = <<-EOF
1 o'clock, 2 o'clock, 3 o'clock, rock!
5, 6, 7 o'clock, 8 o'clock, rock!
9, 10, 11 o'clock, 12 o'clock, rock!
We're gonna rock around the clock tonight!
EOF
      expected = "9, 10, 11 o'clock, 12 o'clock, rock!"
      assert_equal(expected, data.line(2))
      assert_equal([expected], data.line(2, 1))

      expected = [
        "9, 10, 11 o'clock, 12 o'clock, rock!",
        "We're gonna rock around the clock tonight!",
      ]
      assert_equal(expected, data.line(2..3))
      assert_equal(expected, data.line(2..7))
      assert_equal(expected, data.line(2, 2))
      assert_equal(expected, data.line(2, 3))
      assert_equal(expected, data.line(2, 4))

      assert_equal(nil, data.line(10))
      assert_equal(nil, data.line(10, 10))
      assert_equal(nil, data.line(10..20))

      assert_raises(TypeError) { data.line("fish") }
      assert_raises(ArgumentError) { data.line(0,1,2) }
    end

    def test_cmp
      assert_equal(nil, @usage.cmp(@usage))
      assert_equal(@usage.size, @usage.cmp(@usage + "XYZ"))
      assert_equal(@usage.size, (@usage + "XYZ").cmp(@usage))
      usage2 = @usage.dup
      usage2[5] = '$'
      assert_equal(5, usage2.cmp(@usage))
      assert_equal(5, @usage.cmp(usage2))
    end

    def test_join
      assert_equal('', ''.join)
      assert_equal('x', 'x'.join)
      assert_equal("x x", "x \t\n\n x    \t \n ".join)
      assert_equal("x", " \t\n\n x    \t \n ".join)
      assert_equal('One fine day she came my way', "One fine day she  \n  came my way".join)
      assert_equal('One fine    day she came my way', "One fine    day she  \n  came my way".join)
      assert_equal('One fine day she came my way', "  One fine day she  \n  came my way".join)
      assert_equal('One fine day she came my way', "\nOne fine day she came my way".join)
      assert_equal('One fine day she came my way', "\nOne fine day she \n    came my way".join)
      assert_equal('One fine day she came my way', "One fine day she \n\n\n  \n    came my way".join)
      assert_equal('One fine day she came my way', "One fine day she \n\n\n  \n    came my way\n\n".join)
    end

  end  # class TC_String_4

