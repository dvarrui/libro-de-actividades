#!/usr/bin/env ruby
#
# gconfclock_viewer.rb
#
# Copyright (c) 2003 Masao Mutoh
#
# You can redistribute it and/or modify it under the terms of
# the Ruby's licence.
#
require 'gconf2'
require 'gtk2'

GCONF_TIME_KEY = "/apps/gconfclock/date"

Gtk.init

label = Gtk::Label.new("initializing...")

client = GConf::Client.new
client.add_dir(GCONF_TIME_KEY)
client.notify_add(GCONF_TIME_KEY) {|client, entry|
  label.set_text(GLib.locale_to_utf8(Time.at(entry.value).strftime("%c")))
}

win = Gtk::Window.new.set_default_size(100, 100).add(label).show_all
win.signal_connect("destroy") do
  Gtk.main_quit
end

Gtk.main
