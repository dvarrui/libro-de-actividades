
module Observable
 def register(event=nil, &callback)
   @observers ||= Hash.new
   @observers[event] ||= []
   @observers[event] << callback
   self
 end
 protected
 def signal_event(event = nil, *args)
   @observers ||= Hash.new
   @observers[event] ||= []
   @observers[event].each do | callback |
     callback.call(self, *args)
   end
 end
end

class Observed
  include Observable
  def foo=(a_foo)
    signal_event(:changed, @foo, a_foo)
    @foo = a_foo
  end
end

observed = Observed.new
observed.register(:changed) do | o, old, new |
  puts "#{old} -> #{new}"
end

observed.foo = 'Yukihiro'
observed.foo = 'Matsumoto'
observed.foo = 'Matz'

# −> Yukihiro
#Yukihiro −> Yukihiro Matsumoto
#Yukihiro Matsumoto −> Matz

