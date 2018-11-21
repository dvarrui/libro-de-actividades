#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'rexml/document'
include REXML    # You don't need to understand this to understand the test.
                 # See the chapter on modules if you want to.

class XHTMLExample < Test::Unit::TestCase

  def test_xhtml_included_in_document
    page_text = IO.read("www.w3.org.html")     # (1)
    document = Document.new(page_text)         # (2)
    topics = XPath.match(document, '//li/a/abbr')  # (3)
    assert(topics.find { | topic | topic.text.strip == "XML" })  # (4)
  end

end
