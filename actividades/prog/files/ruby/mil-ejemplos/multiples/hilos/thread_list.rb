#!/usr/bin/ruby -w
# thread_list.rb

Thread.new { sleep 100}
Thread.new {x=0; 1000000.times {x+=1}}
Thread.new { sleep 100 }

#irb> load 'fichero'
#irb> Thread.list

