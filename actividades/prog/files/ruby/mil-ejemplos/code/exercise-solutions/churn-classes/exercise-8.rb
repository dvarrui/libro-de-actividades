#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class SubversionRepository

  def initialize(root)
    @root = root
  end

  def date(a_time)
    a_time.strftime("%Y-%m-%d")
  end

  def change_count_for(name, a_time)
    extract_change_count_from(log(name, date(a_time)))
  end

  def extract_change_count_from(log_text)
    lines = log_text.split("\n")
    dashed_lines = lines.find_all do | line |
      line.include?('--------') 
    end
    dashed_lines.length - 1     
  end

  def log(subsystem, start_date)
    timespan = "--revision 'HEAD:{#{start_date}}'"
    
    `svn log #{timespan} #{@root}/#{subsystem}`
  end
end

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



def month_before(a_time)
  a_time - 28 * 24 * 60 * 60
end


if $0 == __FILE__
  subsystem_names = ['audit', 'fulfillment', 'persistence',
                     'ui', 'util', 'inventory']
  root="svn://rubyforge.org//var/svn/churn-demo"
  repository = SubversionRepository.new(root)
  last_month = month_before(Time.now) #(1)
  
  formatter = Formatter.new
  formatter.report_range(last_month, Time.now) #(2)
  subsystem_names.each do | name |  
    formatter.use_subsystem_with_change_count(
        name, repository.change_count_for(name, last_month)) #(3)
  end
  puts formatter.output
end

