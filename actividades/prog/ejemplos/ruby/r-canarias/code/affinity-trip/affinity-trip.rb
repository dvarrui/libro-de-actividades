#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'uri'
require 'open-uri'
require 'csv'


### This does the bulk of the work

# This simple version has some problems, but is the 
# framework for the more complete version.

def trip(url, steps=10)   # (1) 

  steps.times do         # (2) 
    page = fetch(url)
    book_info = scrape_book_info(page)
    puts format_output(book_info)
  
    next_book = scrape_affinity_list(page)[0]

    url = next_book[:url]
  end
end

# This more complete version replaces the simple version above.

def trip(url, steps=10)
  so_far = []            # (3) 

  steps.times do       
    page = fetch(url)
    book_info = scrape_book_info(page)
    so_far << book_info[:title]
    puts format_output(book_info)
  
    next_book = scrape_affinity_list(page).find do | possible | # (4) 
      not so_far.include?(possible[:title])
    end

    url = next_book[:url]
  end
end



### Fetching Amazon book pages


def url_for(isbn)
  "http://www.amazon.com/gp/product/" + isbn
end

def fetch(url)
  open(url) { | response |
    response.read
  }
end


### Scraping information out of Amazon book pages


def scrape_book_info(html)
  retval = {}
  html = restrict(html,
                  /<div.+class\s*=\s*"buying".*?>\s*<b/,
                  %r{</div\s*>})
  retval[:title] = scrape_title(html)
  retval[:authors] = scrape_authors(html)
  retval
end


def scrape_title(html)
  %r{<b.*?>(.*?)</b\s*>}m =~ html
  clean_title($1)
end


def scrape_authors(html)
  author_anchor = %r{<a.*?href=".*?field-author-exact.*?".*?>(.+?)</a\s*>}m
  html.scan(author_anchor).flatten.collect do | author |
    clean_author(author)
  end
end


def restrict(html, starting_regexp, stopping_regexp)
  start = html.index(starting_regexp)
  stop = html.index(stopping_regexp, start)
  html[start..stop]
end


def clean_title(amazon_title)
  # The regexps below have duplication. It could be removed, but
  # regexps are hard enough to understand as it is.

  paper = /\(\s*Paperback\s*\)\s*$/m
  hard = /\(\s*Hardcover\s*\)\s*$/m
  amazon_title.gsub(paper, '').gsub(hard, '').strip.squeeze(' ')
end

def clean_author(amazon_author)
  amazon_author.squeeze(' ')
end

def scrape_affinity_list(html)
  result = []
  whole_list_matches = %r{also\s+bought.*?</ul\s*>}m
  one_element_matches = %r{<li.*?>.*?</li\s*>}m

  html_affinity_list = html[whole_list_matches]
  html_affinity_list.scan(one_element_matches).collect do | item |
    { :url => /href\s*=\s*"(.*?)"/.match(item)[1],
      :title => %r{<a.*?>(.*?)</a\s*>}.match(item)[1] }
  end
end

# How to print


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



if $0 == __FILE__

  if ARGV[0] == '--csv'
    FORMAT_STYLE = :csv_string
    ARGV.shift
  else
    FORMAT_STYLE = :normal_string
  end
    
  starting_isbn = ARGV[0] || '0974514055'
  trip(url_for(starting_isbn))
end


