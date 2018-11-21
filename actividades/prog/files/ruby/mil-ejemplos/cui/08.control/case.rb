#!/usr/bin/ruby

def greet(*names)
 case names.length
 when 0
   "How sad, nobody wants to hear my talk."
 when 1
   "Hello #{names}. At least one wants to hear ab out ruby."
 when 2..5
   "Hello #{names.join(', ')}. Good that all of you are interested."
 when 6..12
   "#{names.length} students. Thats perfect. Welcome to ruby!"
 else
   "Wow #{names.length} students. We’ll have to ﬁnd a bigger room."
 end
end

puts greet('Alexander', 'Holger', 'Zyki', 'Sebastian', 'Johann', 'chenkefei',
  'JetHo eTang', 'Matthias', 'oanap op', 'Andrei', 'Phillip')

