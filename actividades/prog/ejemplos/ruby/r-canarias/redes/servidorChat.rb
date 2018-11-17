#!/usr/bin/ruby -w
require 'socket' # TCP communication
require 'thread' # Multi Threading.
host, port = ARGV[0], ARGV[1]
semaphore = Mutex.new
server = TCPServer.new(host, port)
clients = []
while (socket = server.accept)
  semaphore.synchronize do clients << socket end
  swt = Thread.new(socket) do | the_socket |
    while line = the_socket.gets
      break if /ˆQUIT/ =~ line
      semaphore.synchronize do
        clients.each do | client |
          client.puts line if client != the_socket
        end
      end
    end
    semaphore.synchronize do clients.delete(socket) end
    socket.close
  end
end

