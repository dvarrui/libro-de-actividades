#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def check_args(args)
  raise "Exactly one argument is required." unless args.length == 1
  only_arg = args[0]
  raise "'#{only_arg}' is not an integer."   unless /^\d+$/ =~ only_arg
end

if $0 == __FILE__
  begin
    check_args(ARGV)
    puts ARGV[0].to_i
  rescue Exception => ex
    puts ex.message
  end
end
