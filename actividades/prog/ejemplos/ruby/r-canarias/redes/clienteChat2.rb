#!/usr/bin/ruby -w
require 'socket'
require 'thread'

class ChatClient
  def initialize(host, port)
   @socket = TCPSocket.new(host, port)
   @on_receive = nil
  end

  def on_receive(&on_receive)
   @on_receive = on_receive
  end

  def listen
   @listen_thread = Thread.new do
     while line = @socket.gets
        @on_receive.call(line) if @on_receive
     end
   end
  end

  def send(line)
    @socket.puts(line)
  end

  def close
    send('QUIT')
    @listen_thread.join
  end
end

host = ARGV[0] || 'localhost'
port = ARGV[1] || 1111
client = ChatClient.new(host, port)
client.on_receive do | line | puts "Recibido:#{line}" end
client.listen
# Input
while line = $stdin.gets
  break if /ˆexit/ =~ line
  client.send(line)
end
client.close

