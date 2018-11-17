#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module S4tUtils

  module_function

  def ask(default_answer, *question_lines)
    puts question_lines
    print "[#{default_answer}] => "
    answer = STDIN.readline.strip
    answer = default_answer.to_s if answer == ''
    answer
  end

end


