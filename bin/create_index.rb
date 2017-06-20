#!/usr/bin/ruby

puts '# Índice de actividades'
puts ''
puts format('`Fecha UM: %s`',Time.now.to_s)
puts ''

def recorre_dir(parentdir, tab = '')
  items = Dir.entries(parentdir) - [ '.', '..']
  puts format('%s* [%s](%s) %d',tab, File.basename(parentdir), parentdir, items.size)
  tab += '    '
  items.each do |item|
    filepath = File.join(parentdir,item)
    if File.directory?(filepath)
      recorre_dir filepath, tab
    elsif File.extname(item) == '.md'
      puts format('%s* [%s](%s)',tab, item, filepath)
    end
  end
end

recorre_dir 'actividades/add'
recorre_dir 'actividades/idp'
recorre_dir 'actividades/fuw'
