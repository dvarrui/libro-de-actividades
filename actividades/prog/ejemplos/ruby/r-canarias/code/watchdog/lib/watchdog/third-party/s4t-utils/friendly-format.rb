#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# Note: see also <http://englishext.rubyforge.org/>

module S4tUtils

  module_function

  def friendly_list(connector, array)
    quoted = array.collect { | elt | "'" + elt.to_s + "'" }
    case array.length
    when 0
      ""
    when 1
      quoted[0]
    when 2
      quoted[0] + " #{connector} " + quoted[1]
    else
      quoted[0...-1].join(", ") + ", #{connector} #{quoted.last}"
    end
  end

  # Produces a version of a string that can be typed after a :
  # (Can also be safely given at a command-line prompt.
  def symbol_safe_name(name)
    name.to_s.gsub(/\W/, '')
  end
end

