
query = "select a.login acceso, a.nombre as el_nombre, a.password from usuarios"
query =~ /^select(.*?)\bfrom\b/i

puts "query = #{query}"

puts "\nSepara por las comas:"
columns = $1.strip.split(/\s*,\s*/)
columns.each {|c| puts c }

puts "\nResultado:"
columns.map {|c| puts c.split(/[ .]/)[-1]}

