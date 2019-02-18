#!/usr/bin/ruby -w
require 'socket' # TCP communication
require 'thread' # Multi Threading
class ChatServer
  def initialize(host, port)
    @server = TCPServer.new(host, port)
    @semaphore = Mutex.new
    @clients = []
  end

  def serve
    while (socket = @server.accept)
      client = ClientThread.new(socket)
      client.on_received do | c, l |
        distribute(c, l)
      end
      add_client(client)
      client.listen
    end
  end

  def distribute(client, line)
    @semaphore.synchronize do
      @clients.each do | c |
        c.send(line) if c != client
      end
    end
  end

  def add_client(client)
    @semaphore.synchronize do
      @clients << client
    end
    client.on_terminate do | c |
      remove_client(c)
    end
  end

  def remove_client(client)
    @semaphore.synchronize do
      @clients.delete(client)
    end
  end
end

class ClientThread
  def initialize(socket)
    @socket = socket
    @on_received = @on_terminate = nil
  end
  def listen
    @listen_thread = Thread.new do
      while line = @socket.gets
        break if /ˆQUIT/ =~ line
        @on_received.call(self, line) if @on_received
      end
      @on_terminate.call(self) if @on_terminate
      @socket.close
    end
  end

  def send(line)
    @socket.puts(line)
  end

  def on_received(&on_received)
    @on_received = on_received
  end

  def on_terminate(&on_terminate)
    @on_terminate = on_terminate
  end
end

host, port = ARGV[0], ARGV[1]
cs = ChatServer.new(host, port)
cs.serve

