#!/usr/local/bin/ruby -w
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# install-doc.rb
#
#   Quick 'n' dirty script to generate and install the documentation.  It uses
#   (prefix), which would be /usr or /usr/local on a Unix system, and something
#   like C:/ruby on a Windows system, and adds "doc/ruby" to make a guess at a
#   documentation directory.
#
#   It then creates or reuses the directory "extensions-m.n", where m.n is the
#   version number as contained in VERSION.
#
#   The above assumptions mean that this program must be run from the root
#   directory of the "exstensions" project.
#

require "fileutils"
require "rbconfig"

$LOGGING = true

module Kernel
  def log(msg = "")
    puts "LOG: #{msg}" if $LOGGING
  end
end


# 1. Determine the target directory.

include Config
prefix = CONFIG['prefix']
rubydocdir = File.join(prefix, "doc", "ruby")
log "Default ruby doc dir is #{rubydocdir}"

target_base = ARGV.shift
if target_base.nil?
  log "No target directory given; using default"
  target_base = rubydocdir
else
  log "Using target directory given on command line: #{target_base}"
end
unless File.directory?(target_base)
  log "#{target_base} does not exist.  Can't continue."
  exit!
end

version = File.read("VERSION").strip
log "Version: #{version}"
target_doc_dir = File.join(target_base, "extensions-#{version}")
log "Target directory: #{target_doc_dir}"

unless File.directory?(target_doc_dir)
  FileUtils.mkdir(target_doc_dir)
  log "Created target directory: #{target_doc_dir}"
end

 
# 2. Generate the documentation.

command = %w{rake rerdoc}
log "Command: #{command.join(' ')}"
unless system(*command)
  log "Command failed; exiting."
  exit!
end


# 3. Clean out the target directory.
 
### Not really needed?
###
### Dir.chdir(target_rdoc_dir) do
###   FileUtils.rm_rf(".")
### end 


# 4. Copy the documentation to the target directory.

FileUtils.cp_r("build/rdoc", target_doc_dir, :verbose => true)
FileUtils.cp("README.1st", target_doc_dir, :verbose => true)
FileUtils.cp("HISTORY", target_doc_dir, :verbose => true)
FileUtils.cp("ChangeLog", target_doc_dir, :verbose => true)


# 5. Finished.

log "Done."
