#! /opt/local/bin/ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'pathname'
$:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
require 's4t-utils/load-path-auto-adjuster'

require 's4t-utils'
include S4tUtils

require "fileutils"
include FileUtils
require 'rbconfig'
include Config

user_choices = {}


def copy_silently(from, to, replacements)
  copy(from, to)
  replace(to, replacements)
end

def copy_and_note_edit(from_path, to_prefix, to_rest, replacements)
  copy_silently(from_path, to_prefix+to_rest, replacements)
  puts "=> You will need to edit #{to_rest}."
end

def copy_and_announce(from_path, to_prefix, to_rest, replacements)
  copy_silently(from_path, to_prefix+to_rest, replacements)
  puts "=> You can use #{to_rest} as a template."
end

def replace(in_file, replacements)
  contents = IO.read(in_file)
  replacements.each do | key, value |
    contents.gsub!(key, value)
  end
  File.open(in_file, 'w') { | io | io.puts(contents) }
end
  
  
if $0 == __FILE__
  with_pleasant_exceptions do

    # Orient ourselves. First, assume running in the s4t-utils
    # source tree.
    src_s4t_root = Pathname.new(__FILE__).parent.parent
    src_s4t_lib = src_s4t_root + 'lib'
    src_s4t_templates = src_s4t_root + 'data' + 'make-s4t-project'

    # Otherwise, assume installed with setup.rb
    unless File.directory?(src_s4t_lib) and File.directory?(src_s4t_templates)
      orig_src_s4t_lib = src_s4t_lib
      src_s4t_lib = Pathname.new(CONFIG['sitelibdir'])
      orig_src_s4t_templates = src_s4t_templates
      src_s4t_templates = Pathname.new(CONFIG['datadir']) + 'make-s4t-project'

      user_claims(File.directory?(src_s4t_lib)) {
        "Could not find the folder of s4t-util library files to install.\nThese did not work:
         #{orig_src_s4t_lib}
         #{src_s4t_lib}"
      }

      user_claims(File.directory?(src_s4t_templates)) {
        "Could not find the folder of s4t-util template files to install.\nThese did not work:
         #{orig_src_s4t_templates}
         #{src_s4t_templates}"
      }

    end



    ## Ask some questions about what the user wants.

    answer = ask(".",
                 "In what folder do you want the project?",
                 "(By default, it's the current one.)")
    where = Pathname.new(File.expand_path(answer))
    user_claims(File.directory?(where)) {
      "#{where} is not a directory."
    }
    # realpath doesn't work on windows.
    # where = where.realpath

    file_name = ask('default-project',
                    "What name will a client require to load the project library?",
                    "(The name of a Ruby file without the ending '.rb'.)")
    dest_project_root = (where + file_name)
    user_disputes(File.exist?(dest_project_root)) {
      "#{dest_project_root} already exists."
    }

    module_name = ask("DefaultProject",
                      "If a client wants to include the library, what module name will she use?") 


    ## Set up various variables that depend on the answers.

    replacements = {
      '!REPLACE_ME_FILE!' => file_name,
      '!REPLACE_ME_MODULE!' => module_name
    }

    dest_s4t_lib = dest_project_root + 'lib' + file_name + 'third-party'


    ## OK, here we go!

    mkdir_p(dest_project_root + 'lib')
    mkdir_p(dest_project_root + 'bin')
    mkdir_p(dest_project_root + 'test')
    mkdir_p(dest_s4t_lib + 's4t-utils')

    # I'd rather use 'rake update-peers', but that would assume
    # the person running this uses subversion.
    cp(src_s4t_lib + 's4t-utils.rb', dest_s4t_lib)
    cp(Dir.glob(src_s4t_lib + 's4t-utils' + '*.rb'),
       dest_s4t_lib + 's4t-utils')

    copy_and_note_edit(src_s4t_templates + 'README-skeleton',
                       dest_project_root,'README.txt',
                       replacements)

    cp(src_s4t_templates + 'setup.rb', dest_project_root + 'setup.rb')
    copy_silently(src_s4t_templates + 'version-skeleton',
                  dest_project_root + 'lib' + file_name + 'version.rb',
                  replacements)

    copy_silently(src_s4t_templates + 'Rakefile.example',
                  dest_project_root + 'Rakefile',
                  replacements)

    dest_file_name = File.join('lib', "#{file_name}.rb")
    copy_and_note_edit(src_s4t_templates + 'main-lib-skeleton',
                       dest_project_root, dest_file_name,
                       replacements)
    
    test_file_name = File.join('test', 'test-skeleton')
    copy_and_announce(src_s4t_templates + 'test-skeleton',
                      dest_project_root, test_file_name,
                      replacements)
    copy_silently(src_s4t_templates + 'set-standalone-test-paths.rb',
                  dest_project_root + 'test'+ 'set-standalone-test-paths.rb',
                  replacements)

    copy_and_announce(src_s4t_templates + 'bin-skeleton',
                      dest_project_root, File.join('bin', 'bin-skeleton'),
                      replacements)

    copy_and_announce(src_s4t_templates + 'sub-lib-skeleton',
                      dest_project_root,
                      File.join('lib', file_name, 'lib-skeleton'),
                      replacements)

    puts
    puts "Now would be a good time to put the project under version control."
  end
end
