#!/bin/sh
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

#
# Copies index.html and the whole rdoc directory to the website.
#
# This can only be run from the directory that it's in (etc/website), because
# it uses relative directories to find the files its after.
#

target_dir=gsinclair@rubyforge.org:/var/www/gforge-projects/extensions
index=index.html
rdoc_dir=../../build/rdoc

[ -d $rdoc_dir ] && [ -f $index ] || {
  echo "Can't find the files!  Looking for:"
  echo "   $index"
  echo "   $rdoc_dir"
  exit 1
}

scp $index $target_dir
scp -r $rdoc_dir $target_dir


# vim: tw=0
