#!/usr/bin/ruby

require 'pry-byebug'

def show_title
  puts '# Índice de actividades'
  puts ''
  puts format('`Fecha UM: %s`',Time.now.to_s)
  puts ''
end

def show_dir(parentdir)
  items = Dir.entries(parentdir) - [ '.', '..', 'REV']
  items.sort!
  puts format('## [%s](%s) %d',
              File.basename(parentdir),
              parentdir,
              items.size)
  puts "\n"
  puts '| ID | Sección | Cantidad | Actividaes |'
  puts '| -- | ------- | -------- | ---------- |'

  items.each_with_index do |item, index|
    filepath = File.join(parentdir, item)
    if File.directory?(filepath)
      subitems = get_activity_names(filepath)
      puts format('| %d | [%s](%s) | %d | %s |',
                  index,
                  item,
                  filepath,
                  subitems.size,
                  subitems.join(', '))
    end
  end
  puts "\n"
end

def get_activity_names(dirpath)
  output = []
  output << Dir.entries(dirpath).select { |name| name.include?('.md') }
  dirs = Dir.entries(dirpath) - ['.', '..', 'files', 'images', 'REV', 'REVISAR']
  output << dirs.select do |name|
    File.directory?(File.join(dirpath,name))
  end
  output.flatten!.sort!
  output.map { |name| "[#{name}](#{File.join(dirpath,name)})" }
end

show_title
show_dir 'actividades/hardware'
show_dir 'actividades/sistemas.2'
show_dir 'actividades/sistemas.3'
