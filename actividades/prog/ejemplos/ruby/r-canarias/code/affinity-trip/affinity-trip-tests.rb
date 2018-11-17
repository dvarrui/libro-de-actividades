#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'affinity-trip'

class TestAffinityTrip < Test::Unit::TestCase

  def test_url_for_is_at_amazon
    assert_equal('http://www.amazon.com/gp/product/ISBN',
                 url_for('ISBN'))
  end

  def test_book_info_can_be_scraped_out
    expected = "Programming Ruby: The Pragmatic Programmers' Guide, Second Edition"
    actual = scrape_book_info(IO.read('programming-ruby-amazon-page.html'))
    assert_equal(expected, actual[:title])

    expected_authors = ['Dave Thomas', 'Chad Fowler', 'Andy Hunt']
    assert_equal(expected_authors, actual[:authors])
  end

  def test_book_title_that_spans_lines_can_be_scraped_out
    actual = scrape_book_info(IO.read('cell-amazon-page.html'))
    assert_equal("Cell: A Novel", actual[:title])
    assert_equal(['Stephen King'], actual[:authors])
  end

  def test_book_info_with_extra_text_after_authors_gets_authors_right
    title = "Professional Ajax (Programmer to Programmer)"
    authors = ['Nicholas C. Zakas', 'Jeremy McPeak', 'Joe Fawcett']

    actual = scrape_book_info(IO.read('professional-ajax-amazon-page.html'))
    assert_equal(title, actual[:title])
    assert_equal(authors, actual[:authors])
  end

  def test_clean_title_removes_paperback_and_hardcover
    assert_equal("A book", clean_title("A book (Paperback) "))
    assert_equal("A book", clean_title("A book(Hardcover)"))
    assert_equal("A book", clean_title("A book"))
  end

  def test_clean_title_removes_trailing_blank_lines
    assert_equal("A book", clean_title("A book (Paperback)\n\n\n"))
    assert_equal("A book", clean_title("A book\n\n\n"))
  end

  def test_clean_title_squeezes_blanks
    assert_equal("A book", clean_title("A  book "))
  end

  def test_clean_author_squeezes_blanks
    assert_equal("J. Random Author", clean_author("J.   Random Author"))
  end

  def test_affinity_list_can_be_scraped_out
    list = scrape_affinity_list(IO.read('programming-ruby-amazon-page.html'))
    assert_equal("Agile Web Development with Rails : A Pragmatic Guide (The Facets of Ruby Series)",
                 list[0][:title])

    assert_equal("http://www.amazon.com/exec/obidos/tg/detail/-/097669400X/ref=pd_sim_b_1/002-4184610-5440038?_encoding=UTF8&v=glance",
                 list[0][:url])

    assert_equal("Pragmatic Version Control Using Subversion",
                 list[-1][:title])
    assert_equal("http://www.amazon.com/exec/obidos/tg/detail/-/0974514063/ref=pd_sim_b_5/002-4184610-5440038?_encoding=UTF8&v=glance",
                 list[-1][:url])
    
  end

end

