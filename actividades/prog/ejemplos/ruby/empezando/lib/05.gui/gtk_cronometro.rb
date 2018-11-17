#!/usr/bin/ruby -w
#gtk_cronometro.rb
require 'gtk2'

class Cronometro
  LABEL_MARKUP='<span font_desc="16" weight="bold">%s</span>'

  def start
    @accumulated ||= 0
    @elapsed = 0
    @start = Time.now

    @mybutton.label='Stop'
    set_button_handler('clicked') {stop}
    @timer_stopped=false
    @timer=Thread.new do
      until @timer_stopped do
        sleep(0.1)
        tick unless @timer_stopped
      end
    end
  end

  def stop
    @mybutton.label='Start'
    set_button_handler('clicked') {start}
    @timer_stopped=true
    @accumulated += @elapsed
  end

  def reset
    stop
    @accumulated, @elapsed = 0 , 0
    @mylabel.set_markup(LABEL_MARKUP % '00:00:00.0' )
  end

  def tick
    @elapsed = Time.now - @start
    time = @accumulated + @elapsed
    h = sprintf('%02i',(time.to_i / 3600))
    m = sprintf('%02i',((time.to_i % 3600 )/60))
    s = sprintf('%02i', (time.to_i % 60))
    mt = sprintf('%1i', ((time-time.to_i)*10).to_i)
    @mylabel.set_markup(LABEL_MARKUP % "#{h}:#{m}:#{s}:#{mt}" )
  end

  def initialize
    Gtk.init
    root=Gtk::Window.new('GTK Ruby - Cronómetro')

    accel_group=Gtk::AccelGroup.new
    root.add_accel_group(accel_group)
    root.set_border_width 0

    box=Gtk::VBox.new(false,0)
    root.add(box)

    menu_factory=Gtk::ItemFactory.new(Gtk::ItemFactory::TYPE_MENU_BAR,'<main>',nil)
    menu_spec = [
      ['/_Program'],
        ['/Program/_Start','<Item>',nil,nil,lambda{start}],
        ['/Program/S_top','<Item>',nil,nil,lambda{stop}],
        ['/Program/_Exit','<Item>',nil,nil,lambda{Gtk.main_quit}],
      ['/_Reset'],
        ['/Reset/_Reset_Stopwatch','<Item>',nil,nil,lambda{reset}]
    ]
    menu_factory.create_items(menu_spec)
    menu_root=menu_factory.get_widget('<main>')
    box.pack_start(menu_root)

    @mylabel=Gtk::Label.new
    @mylabel.set_markup(LABEL_MARKUP % '00:00:00.0' )
    box.pack_start(@mylabel)

    @mybutton=Gtk::Button.new('Start')
    set_button_handler('clicked') {start}
    box.pack_start(@mybutton)
    root.signal_connect('destroy') {Gtk.main_quit}
    root.show_all

    Gtk.main
  end

  def set_button_handler(event,&block)
    @mybutton.signal_handler_disconnect(@mybutton_handler) if @mybutton_handler
    @mybutton_handler=@mybutton.signal_connect(event,&block)
  end
end

Cronometro.new
