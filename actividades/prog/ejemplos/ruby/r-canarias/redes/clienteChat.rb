#!/usr/bin/ruby -w
require 'socket'
require 'thread'
host = ARGV[0] || 'localhost'
port = ARGV[1] || 1111
socket = TCPSocket.new(host, port)
t = Thread.new do # Receiver thread
  while line = socket.gets
    puts "Received: #{line}"
  end
  socket.close
end
while line = $stdin.gets # Read input
  break if /ˆexit/ =~ line
  socket.puts line
end
socket.puts 'QUIT' # Request disconnect
t.join # Wait for receiver thread to ﬁnish

