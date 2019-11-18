#!/usr/bin/ruby

puts '# Índice de actividades'
puts ''
puts format('`Fecha UM: %s`',Time.now.to_s)
puts ''

def recorre_dir(parentdir)
  items = Dir.entries(parentdir) - [ '.', '..']
  items.sort!
  puts format('## [%s](%s) %d',
              File.basename(parentdir),
              parentdir,
              items.size)
  puts "\n"
  puts '| Sections | Size | Activities |'
  puts '| -------- | ---- | ---------- |'

  items.each do |item|
    filepath = File.join(parentdir, item)
    if File.directory?(filepath)
      subitems = get_activity_names(filepath)
      puts format('|%s | %d   | %s |',
                  item,
                  subitems.size,
                  subitems.join(', '))
    end
  end
  puts "\n"
end

def get_activity_names(dir)
  items = Dir.entries(dir)
  items.select! { |name| name.include?('.md') }
  items.sort!
  items
end

recorre_dir 'actividades/hardware'
recorre_dir 'actividades/sistemas.2'
recorre_dir 'actividades/sistemas.3'
