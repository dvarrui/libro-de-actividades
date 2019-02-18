# Last Modified: Thu Mar 10 17:43:56 2016
#include <tunables/global>

/home/david/temp/aa/copy.rb {
  #include <abstractions/base>
  #include <abstractions/consoles>
  #include <abstractions/ruby>

  /bin/bash rix,
  /home/david/temp/aa/copy.rb r,
  /home/david/temp/aa/source/ r,
  /home/david/temp/aa/source/* r,
  /home/david/temp/aa/target/* w,
  /proc/*/status r,
  /proc/filesystems r,
  /proc/meminfo r,
  /usr/bin/ls rix,
  /usr/bin/ruby ix,
  /usr/lib{,32,64}/** mr,

}
