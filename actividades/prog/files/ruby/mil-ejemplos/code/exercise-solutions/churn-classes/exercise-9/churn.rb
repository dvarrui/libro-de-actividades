#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require "subversion-repository"
require "formatter"

def month_before(a_time)
  a_time - 28 * 24 * 60 * 60
end



if $0 == __FILE__
  subsystem_names = ['audit', 'fulfillment', 'persistence',
                     'ui', 'util', 'inventory']
  root="svn://rubyforge.org//var/svn/churn-demo"
  repository = SubversionRepository.new(root)
  last_month = month_before(Time.now)
  
  formatter = Formatter.new
  formatter.report_range(last_month, Time.now)
  subsystem_names.each do | name |  
    formatter.use_subsystem_with_change_count(
        name, repository.change_count_for(name, last_month))
  end
  puts formatter.output
end
