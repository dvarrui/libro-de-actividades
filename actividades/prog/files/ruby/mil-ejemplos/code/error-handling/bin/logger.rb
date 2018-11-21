#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

def log(owner, exception)
  File.open("exception.log", 'a') { | io |  # "a" means append to the file.
    io.puts(Time.now)
    io.puts(exception.class)
    io.puts(owner + ': ' + exception.message)
    io.puts(exception.backtrace)
  }
end



def owned_open(owner, name)
  File.open(name)
rescue Errno::ENOENT => ex
  log(owner, ex)
  raise
end


begin
  f = owned_open('marick', "no-such-file")
  puts f.readlines
rescue Exception => ex
  puts ex.message
end
  
  
