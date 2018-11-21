#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'affinity-trip'

isbn = ARGV[0] || '0974514055'
scrape_affinity_list(fetch(url_for(isbn))).each do | book |
  puts book[:title]
end
