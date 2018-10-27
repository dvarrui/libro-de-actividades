#!/usr/bin/ruby
# encoding: utf-8
#
# Simulate typing
# * https://www.linuxjournal.com/content/simulate-typing-c-program

require 'rainbow'
require 'pry-byebug'

class SimulateTyping

  def initialize()
    @commands = []
    @prompt = "simulating> "
  end

  def play(input)
    if input.class == Array
      @commands = input
    elsif input.class == String
      @filename = input
      content = `cat #{@filename}`
      @commands = content.split("\n")
    end

    puts "="*50
    @commands.each do |command|
      sleep(0.2)
      print @prompt
      sleep(0.2)
      print_with_delay(command,0.4)
      #  gets
      print "\n"
      sleep(0.3)
      output = execute_this command
      print_with_delay(output,0.04)
    end
    puts "Simulator: Bye-bye!"
    puts "="*50
  end

  def print_with_delay(text, seconds)
    text.each_char do |c|
      sleep(seconds)
  #    print(Rainbow.(c.to_s).color(:blue))
      print(c)
    end
  end

  def execute_this(command)
    output = ''
    begin
      output = `#{command}`
    rescue Exception => e
      puts "[ERROR] comando incorrecto: #{e}"
    end
    output
  end

end

#SimulateTyping.new.play('simulate-typing.txt')
SimulateTyping.new.play([ 'ip a', 'ip route', 'uname -a'])
