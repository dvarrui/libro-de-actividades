#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class Formatter

  # Public interface 
  def initialize
    @lines = []
  end

  def report_range(from, to)
    @from = from
    @to = to
  end

  def use_subsystem_with_change_count(name, count)
    @lines.push(subsystem_line(name, count))
  end

  def output
    ([header] + lines_ordered_by_descending_change_count).join("\n")
  end


  # Helpers   

  def date(time)
    date_format = "%B %d, %Y"
    time.strftime(date_format).sub(' 0', ' ')
  end
  
  def header
    "Changes between #{date(@from)}, and #{date(@to)}:"
  end

  def subsystem_line(subsystem_name, change_count)
    asterisks = asterisks_for(change_count)
    "#{subsystem_name.rjust(14)} #{asterisks} (#{change_count})"
  end

  def asterisks_for(an_integer)
    '*' * (an_integer / 5.0).round
  end

  def lines_ordered_by_descending_change_count
    @lines.sort do | first, second |
      first_count = churn_line_to_int(first)
      second_count = churn_line_to_int(second)
      - (first_count <=> second_count)
    end
  end

  def churn_line_to_int(line)
    /\((\d+)\)/.match(line)[1].to_i
  end
end

