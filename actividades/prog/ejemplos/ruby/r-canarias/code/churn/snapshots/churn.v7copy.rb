#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

# This is the same as churn.v7.rb. It's a different file because
# of the mechanics of book production. 

def month_before(a_time)
  a_time - 28 * 24 * 60 * 60
end

def header(a_time)
  a_time.strftime("Changes since %Y-%m-%d:")
end

def subsystem_line(subsystem_name, change_count)
  asterisks = asterisks_for(change_count)
  "#{subsystem_name.rjust(14)} #{asterisks} (#{change_count})"
end

def asterisks_for(an_integer)
  '*' * (an_integer / 5.0).round
end

def change_count_for(name, start_date)
  extract_change_count_from(svn_log(name, start_date))
end

def extract_change_count_from(log_text)
  lines = log_text.split("\n") 
  dashed_lines = lines.find_all do | line |   
    line.include?('--------')     
  end
  dashed_lines.length - 1         
end

def svn_log(subsystem, start_date)
  timespan = "--revision 'HEAD:{#{start_date}}'"  #(1)
  root = "svn://rubyforge.org//var/svn/churn-demo"
  
  `svn log #{timespan} #{root}/#{subsystem}`
end

if $0 == __FILE__
  subsystem_names = ['audit', 'fulfillment', 'persistence',
                     'ui', 'util', 'inventory']
  start_date = month_before(Time.now)  #(2)

  puts header(start_date)
  subsystem_names.each do | name |          
    puts subsystem_line(name, change_count_for(name, start_date))  #(3)
  end
end

