#!/usr/bin/env ruby
#
# This file is gererated by ruby-glade-create-template 1.1.3.
#
require 'libglade2'
require 'gtkmozembed'

class RubyzillaGlade
  include GetText

  attr :glade
  
  HOME_URL = "http://ruby-gnome2.sourceforge.jp/"

  Gtk::MozEmbed.set_profile_path(ENV["HOME"] + ".mozilla", "RubyZilla")

  def initialize(path_or_data, root = nil, domain = nil, localedir = nil, flag = GladeXML::FILE)
    bindtextdomain(domain, localedir, nil, "UTF-8")
    @glade = GladeXML.new(path_or_data, root, domain, localedir, flag) {|handler| method(handler)}
    @gecko = @glade["gecko"]

    @gecko.location = HOME_URL  #Show HOME_URL first.
  end
  
  def on_forwardbutton_clicked(widget)
    @gecko.go_forward
  end
  def on_backbutton_clicked(widget)
    @gecko.go_back
  end
  def on_gobutton_clicked(widget)
    @gecko.location = @glade["urlentry"].text
  end
  def on_homebutton_clicked(widget)
    @gecko.location = HOME_URL
  end
  def on_refreshbutton_clicked(widget)
    @gecko.reload(Gtk::MozEmbed::RELOADBYPASSCACHE)
  end
  def on_window_delete_event(widget, arg0)
    Gtk.main_quit
  end
end

# Main program
if __FILE__ == $0
  # Set values as your own application. 
  PROG_PATH = "rubyzilla.glade"
  PROG_NAME = "YOUR_APPLICATION_NAME"
  Gtk.init
  RubyzillaGlade.new(PROG_PATH, nil, PROG_NAME)
  Gtk.main
end
#You may need to implement some custom creation methods which return new widget.
#Gtk::MozEmbed.new
