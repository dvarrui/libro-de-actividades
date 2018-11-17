#!/usr/bin/env ruby
#
# Simple Text Editor
#
# Copyright (c) 2003 Masao Mutoh
#
# You can redistribute it and/or modify it under the terms of
# the Ruby's licence.
#
$KCODE = "U"

require 'libglade2'

class SimpleTextEditor
  TITLE = "Simple Text Editor"
  NAME = "SimpleTextEditor"
  VERSION = "1.0"

  #
  # Common
  #
  def iter_on_screen(iter, mark_str)
    @buffer.place_cursor(iter) 
    @textview.scroll_mark_onscreen(@buffer.get_mark(mark_str))
  end

  def update_appbar
    @appbar.pop
    iter = @buffer.get_iter_at_mark(@buffer.get_mark("insert"))
    @appbar.push("Line: #{iter.line + 1}, Column: #{iter.line_offset + 1}")
  end

  def initialize(path)
    @glade = GladeXML.new(path) {|handler| method(handler)}
    @appwindow = @glade.get_widget("appwindow")
    @appbar = @glade.get_widget("appbar")
    @textview = @glade.get_widget("textview")
    @fileselection = @glade.get_widget("fileselection")
    @find_dialog = @glade.get_widget("find_dialog")
    @replace_dialog = @glade.get_widget("replace_dialog")
  
    @filename = nil

    @undopool = Array.new
    @redopool = Array.new
    
    @buffer = @textview.buffer
    @buffer.signal_connect("insert_text") do |w, iter, text, length|
      if @user_action
        @undopool <<  ["insert_text", iter.offset, iter.offset + text.scan(/./).size, text]
        @redopool.clear
      end
    end
    @buffer.signal_connect("delete_range") do |w, start_iter, end_iter|
      text = @buffer.get_text(start_iter, end_iter)
      @undopool <<  ["delete_range", start_iter.offset, end_iter.offset, text] if @user_action
    end
    @buffer.signal_connect("begin_user_action") do
      @user_action = true
    end
    @buffer.signal_connect("end_user_action") do
      @user_action = false
    end
    @buffer.signal_connect("changed") do |w|
      update_appbar
    end
    @buffer.signal_connect("mark-set") do |w, iter, mark|
      update_appbar
    end
    @textview.signal_connect("move-cursor") do
      update_appbar
    end

    update_appbar
  end

  # Edit textbuffer
  def on_clear(widget)
    @buffer.set_text("")
  end
  def on_cut(widget)
     @textview.signal_emit("cut_clipboard")
  end
  def on_paste(widget)
     @textview.signal_emit("paste_clipboard")
  end
  def on_copy(widget)
     @textview.signal_emit("copy_clipboard")
  end

  def on_quit(*widget)
    Gtk.main_quit
  end

  def on_selectall(widget)
    @buffer.place_cursor(@buffer.end_iter)
    @buffer.move_mark(@buffer.get_mark("selection_bound"), @buffer.start_iter)
  end

  #
  # File access
  #
  def save_file
    File.open(@filename, "w"){|f| 
      f.write(@buffer.get_text(*@buffer.bounds)) 
    }
  end

  def read_file
    File.open(@filename){|f| ret = f.readlines.join }
  end

  def select_file
    @fileselection.set_filename(Dir.pwd + "/")
    ret = @fileselection.run
    if ret == Gtk::Dialog::RESPONSE_OK
      if File.directory?(@fileselection.filename)
        dialog = Gtk::MessageDialog.new(@appwindow, Gtk::Dialog::MODAL, 
                                        Gtk::MessageDialog::ERROR, 
                                        Gtk::MessageDialog::BUTTONS_CLOSE, 
                                        "Directory was selected. Select a text file.")
        dialog.run
        dialog.destroy
        @fileselection.hide
        select_file
      else
        @filename = @fileselection.filename
        @appwindow.set_title(TITLE + ":" + @filename)
        @fileselection.hide
      end
    else
      @fileselection.hide
    end
  end

  def on_open_file(widget)
    select_file
    if @filename
      text = read_file
      @buffer.set_text(text)
    end
    @buffer.place_cursor(@buffer.start_iter)
    @textview.has_focus = true
  end

  def on_new_file(widget)
    @filename = nil
    on_clear(widget)
  end

  def on_save_as_file(widget)
    select_file
    save_file if @filename
  end

  def on_save_file(widget)
    if @filename
      save_file
    else
      on_save_as_file(widget)
    end
  end

  #
  # Unfo, Redo
  #
  def on_undo(widget)
    return if @undopool.size == 0
    action = @undopool.pop 
    case action[0]
    when "insert_text"
      start_iter = @buffer.get_iter_at_offset(action[1])
      end_iter = @buffer.get_iter_at_offset(action[2])
      @buffer.delete(start_iter, end_iter)
    when "delete_range"
      start_iter = @buffer.get_iter_at_offset(action[1])
      @buffer.insert(start_iter, action[3])
    end
    iter_on_screen(start_iter, "insert")
    @redopool << action
  end

  def on_redo(widget)
    return if @redopool.size == 0
    action = @redopool.pop 
    case action[0]
    when "insert_text"
      start_iter = @buffer.get_iter_at_offset(action[1])
      end_iter = @buffer.get_iter_at_offset(action[2])
      @buffer.insert(start_iter, action[3])
    when "delete_range"
      start_iter = @buffer.get_iter_at_offset(action[1])
      end_iter = @buffer.get_iter_at_offset(action[2])
      @buffer.delete(start_iter, end_iter)
    end
    iter_on_screen(start_iter, "insert")
    @undopool << action
  end

  #
  # Find, Replace
  #
  def replace_selected_text(str, start_iter, end_iter)
    @buffer.begin_user_action
    @buffer.delete(start_iter, end_iter)
    @buffer.insert(start_iter, str)
    @buffer.end_user_action
    iter_on_screen(start_iter, "insert")
  end

  def find_and_select(find, backwards, parent)
    text = @glade.get_widget(find).text
    search_flags = Gtk::TextIter::SEARCH_TEXT_ONLY
    iter = @buffer.get_iter_at_mark(@buffer.get_mark("insert"))
    if @glade.get_widget(backwards).active?
      match_iters = iter.backward_search(text, search_flags)
      next_iter = match_iters if match_iters
    else
      match_iters = iter.forward_search(text, search_flags)
      next_iter = [match_iters[1], match_iters[0]] if match_iters
    end

    if match_iters
      iter_on_screen(next_iter[0], "insert")
      @buffer.move_mark("selection_bound", next_iter[1])
    else 
      dialog = Gtk::MessageDialog.new(parent, Gtk::Dialog::MODAL, 
                                      Gtk::MessageDialog::INFO, 
                                      Gtk::MessageDialog::BUTTONS_CLOSE, 
                                      "The string #{text} has not been found.")
      dialog.run
      dialog.destroy
    end
  end
    
  # Find dialog
  def on_find(widget)
    @find_dialog.show
  end
  def on_find_quit(widget)
    @find_dialog.hide
  end
  def on_find_execute(widget)
    find_and_select("find_entry", "backwards_checkbutton", @find_dialog)
  end

  #Replace dialog
  def on_replace(widget)
    @replace_dialog.show
  end
  def on_replace_quit(widget)
    @replace_dialog.hide
  end
  def on_replace_execute(widget)
    iter = @buffer.get_iter_at_mark(@buffer.get_mark("insert"))
    sel_bound = @buffer.get_iter_at_mark(@buffer.get_mark("selection_bound"))
    unless iter == sel_bound
      replace_selected_text(@glade.get_widget("replace_replace_entry").text, 
                            iter, sel_bound)
    end
    find_and_select("replace_find_entry", 
                    "replace_backwards_checkbutton", @replace_dialog)
  end

  #
  # Misc
  #
  def on_about(widget)
    Gnome::About.new(TITLE, VERSION ,
                     "Copyright (C) 2003 Ruby-GNOME2 Project Team",
                     "Simple editor for Ruby-GNOME2",
                     ["Masao Mutoh"], ["Masao Mutoh"], nil).show
  end
end

Gnome::Program.new(SimpleTextEditor::NAME, SimpleTextEditor::VERSION)

SimpleTextEditor.new(File.dirname($0) + "/simple-editor.glade")
Gtk.main
