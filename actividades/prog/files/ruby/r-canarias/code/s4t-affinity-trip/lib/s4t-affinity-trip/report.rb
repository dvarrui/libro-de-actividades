#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'csv'

module AffinityTrip

  def format_output(book_info)
    self.send(FORMAT_STYLE, book_info)
  end


  def normal_string(book_info)
    book_info[:title]  # omit authors
  end

  def csv_string(book_info)
    title = book_info[:title]
    authors = book_info[:authors].join(', ')
    CSV.generate_line([title, authors])
  end

end
