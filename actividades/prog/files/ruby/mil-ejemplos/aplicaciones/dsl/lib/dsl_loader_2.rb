# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

require "lib/my_dsl_2"

md = MyDsl.load(ARGV.shift)
p md
p md.instance_variables
