#! /opt/local/bin/ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'pathname'
$:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
require 's4t-affinity-trip/third-party/s4t-utils/load-path-auto-adjuster'


require 's4t-utils'
include S4tUtils

require 's4t-affinity-trip'
include AffinityTrip

def trip(url, steps=10)
  so_far = []

  steps.times do
    page = fetch(url)
    book_info = scrape_book_info(page)
    so_far << book_info[:title]
    puts format_output(book_info)
    
    next_book = scrape_affinity_list(page).find do | possible |
      not so_far.include?(possible[:title])
    end
    
    url = next_book[:url]
  end
end

if $0 == __FILE__
   with_pleasant_exceptions do
    if ARGV[0] == '--csv'
      FORMAT_STYLE = :csv_string
      ARGV.shift
    else
      FORMAT_STYLE = :normal_string
    end
    
    starting_isbn = ARGV[0] || '0974514055'
    trip(url_for(starting_isbn))
  end
end
