#!/usr/bin/tclsh
# ^ You may have to change this path to work on your system.

# Print "Hello, world!" using the two string types.
puts "Hello, world! (using quotes)"
puts {Hello, world! (using braces)}

# Demonstrate simple variable substitution.
set foo {Foo!}
puts "This is the variable foo: $foo"

# This won't be substituted, since the string is in curly braces.
puts {This is not the variable foo: $foo}

# Change the value of the variable foo.
set foo {Bar!}
puts "The variable foo is now $foo."

# Read a string from the console (stdin).
puts -nonewline {Please enter your name: }
flush stdout
set name [gets stdin]
puts "You entered: $name"
