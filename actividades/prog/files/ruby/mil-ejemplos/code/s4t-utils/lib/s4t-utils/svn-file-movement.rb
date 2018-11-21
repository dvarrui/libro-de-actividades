#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'fileutils'
require 'pathname'

module S4tUtils

  # This transfers, taking care not to copy Subversion directories
  # and other things not of interest.

  # These are not particularly well tested. Use at your own risk.

  module SvnFileTransfer
    include FileUtils

    module_function

    def transfer_dir(src, dest, leave_extras=false)
      # puts "transfer dir #{src} to #{dest}."
      unless File.exist?(dest)
        svn_mkdir dest
      end
      src_files = interesting(Dir.chdir(src) { Dir.glob('*') }).sort
      dest_files = interesting(Dir.chdir(dest) { Dir.glob('*')}).sort
      # puts "src_files = #{src_files.inspect}"
      # puts "dest_files = #{dest_files.inspect}"
      src_files.each do | src_file | 
        if dest_files.delete(src_file)
          update_existing_destination(src, dest, src_file)
        else
          create_and_transfer(src, dest, src_file)
        end
      end
      unless leave_extras
        dest_files.each do | dest_file | 
          svn_rm(dest+dest_file)
        end
      end
    end

    def update_existing_destination(src, dest, common_name)
      src = src+common_name
      dest = dest+common_name
      user_claims(File.directory?(src) == File.directory?(dest)) { 
        "#{src} is a different type of file than #{dest}."
      }
      if File.directory?(src)
        transfer_dir(src, dest)
      elsif not uptodate?(dest, [src])
        cp(src, dest, :verbose=>true)
      end
    end

    def create_and_transfer(src, dest, new_name)
      src = src+new_name
      dest = dest+new_name
      if File.directory?(src)
        transfer_dir(src, dest)
      else
        cp(src, dest, :verbose=>true)
        svn_add(dest)
      end
    end

    def under_svn_control?(pathname)
      File.exist?(Pathname.new(pathname).parent + '.svn')
    end

    def svn_cmd(cmd, pathname)
      cmd = "svn #{cmd} #{pathname}"
      puts cmd
      system(cmd)
      exit if $? != 0
    end

    def svn_rm(pathname)
      svn_cmd('rm --force', pathname) if under_svn_control?(pathname)
      rm_rf pathname
    end

    def svn_add(pathname)
      svn_cmd('add', pathname) if under_svn_control?(pathname)
    end

    def svn_mkdir(pathname)
      if under_svn_control?(pathname)
        svn_cmd('mkdir', pathname)
      else
        mkdir pathname
      end
    end

    def interesting(basenames)
      basenames.reject do | basename | 
        basename[0,1] == '.' or
          basename =~ /^\#.*\#$/ or
          basename =~ /.*~$/ or
          basename == 'third-party'
      end
    end
  end
  
end
