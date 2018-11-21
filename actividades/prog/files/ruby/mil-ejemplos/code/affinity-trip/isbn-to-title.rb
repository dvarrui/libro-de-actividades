#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'affinity-trip'

isbn = ARGV[0] || '0974514055'
puts scrape_book_info(fetch(url_for(isbn)))[:title]
