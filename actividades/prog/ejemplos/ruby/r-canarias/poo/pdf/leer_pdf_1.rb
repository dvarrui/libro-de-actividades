# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

print "Leyendo el PDF ",ARGV[0],"\n"

require 'rubygems'
require 'pdf/reader'
#require 'pdf/writer'
#require 'spec'

class PageTextReceiver
  attr_accessor :content

  def initialize
    @content = []
  end

  # Called when page parsing starts
  def begin_page(arg = nil)
    @content << ""
  end

  def show_text(string, *params)
    @content.last << string.strip
  end

  # there's a few text callbacks, so make sure we process them all
  alias :super_show_text :show_text
  alias :move_to_next_line_and_show_text :show_text
  alias :set_spacing_next_line_show_text :show_text

end

# process the PDF
receiver = PageTextReceiver.new
PDF::Reader.file(ARGV[0], receiver)
receiver.content.each do |i|
  print "Página ",i,"\n"
end

=begin
context "My generated PDF" do
  specify "should have the correct text on 2 pages" do

    # generate our PDF
    pdf = PDF::Writer.new
    pdf.text "Chunky", :font_size => 32, :justification => :center
    pdf.start_new_page
    pdf.text "Bacon", :font_size => 32, :justification => :center
    pdf.save_as("chunkybacon.pdf")

    # process the PDF
    receiver = PageTextReceiver.new
    PDF::Reader.file("chunkybacon.pdf", receiver)

    # confirm the text appears on the correct pages
    receiver.content.size.should eql(2)
    receiver.content[0].should eql("Chunky")
    receiver.content[1].should eql("Bacon")
  end
end

=end

