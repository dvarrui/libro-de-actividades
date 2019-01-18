#!/usr/bin/ruby

puts '# Índice de actividades'
puts ''
puts format('`Fecha UM: %s`',Time.now.to_s)
puts ''

def recorre_dir(parentdir, tab = '', input = [])
  filter = ['files, ''images']
  items = Dir.entries(parentdir) - [ '.', '..']
  items.sort!
  output = input
  output << format('%s* [%s](%s) %d',tab, File.basename(parentdir), parentdir, items.size)
  tab += '    '
  items.each do |item|
    filepath = File.join(parentdir,item)
    if File.directory?(filepath) and !filter.include?(filepath)
        output << recorre_dir(filepath, tab)
    elsif File.extname(item) == '.md'
        output << format('%s* [%s](%s)',tab, item, filepath)
    end
  end
  output
end

puts recorre_dir 'actividades/idp'
puts recorre_dir 'actividades/fuw'
puts recorre_dir 'actividades/add'
