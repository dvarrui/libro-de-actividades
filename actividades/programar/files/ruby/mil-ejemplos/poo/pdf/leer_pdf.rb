#!/usr/bin/env ruby
=begin
Fichero: pdf/leer_pdf.rb

=end 

print " * Leyendo PDF ",ARGV[0],"\n"

require 'rubygems'
require 'pdf/reader'

class PageTextReceiver
  attr_accessor :content

  def initialize
    @content = []
  end

  # Called when page parsing starts
  def begin_page(arg = nil)
    @content << " * Empezando la página...\n"
  end

  def show_text(string, *params)
    @content.last << string.strip
  end

  def move_to_next_line_and_show_text(string, *params)
    @content.last << string.strip
    #@content.last << "Movetonextline\n"
  end
  
  def set_spacing_next_line_show_text(string, *params)
    @content.last << string.strip
    #@content.last << "setspacing\n"
  end

  # there's a few text callbacks, so make sure we process them all
  alias :super_show_text :show_text

end

# process the PDF
receiver = PageTextReceiver.new
PDF::Reader.file(ARGV[0], receiver)
receiver.content.each_with_index do |item,index|
  print " * Página ",index,"\n",item,"\n"
end


