#!/usr/bin/ruby

require 'pry-byebug'

def show_title
  puts '# Índice de actividades'
  puts ''
  puts format('`Fecha UM: %s`',Time.now.to_s)
  puts ''
end

def resume_dirs(dirpaths)
  puts "\# Resumen\n\n"
  puts '| Categoría | Cantidad de actividaes |'
  puts '| --------- | ---------------------- |'

  dirpaths.each do |dirpath|
    items = Dir.entries(dirpath) - [ '.', '..', 'REV']
    puts format('| [%s](%s) | %d |',
                File.basename(dirpath),
                dirpath,
                items.size)
  end
  puts "\n"
  dirpaths.each { |dirpath| show_dir dirpath }
end

def show_dir(parentdir)
  items = Dir.entries(parentdir) - [ '.', '..', 'REV']
  items.sort!
  puts format('## [%s](%s)',
              File.basename(parentdir),
              parentdir)
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
resume_dirs( ['actividades/hardware',
              'actividades/sistemas.2',
              'actividades/sistemas.3'] )
