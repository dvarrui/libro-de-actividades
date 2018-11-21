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

if $0 == __FILE__
   with_pleasant_exceptions do
    isbn = ARGV[0] || '0974514055'
    puts scrape_book_info(fetch(url_for(isbn)))[:title]
  end
end

