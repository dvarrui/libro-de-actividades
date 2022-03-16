#!/usr/bin/env ruby

groups = []
groups[0] = %w(cmake make nasm qemu pkg-config wget gperf autoconf flex autogen po4a expat openssl automake)
groups[1] = %w(gettext-devel bison flex perl-HTML-Parser libtool perl-Pod-Xhtml gperf libpng-devel patch)
groups[2] = %w(aclocal libfuse-dev libhtml-parser-perl perl-Pod-Html)


def install_group(names)
  errs = []
  names.sort.each do |name|
    system("zypper info #{name}|grep Instalado|grep S")
    unless $?.exitstatus.zero?
      ok = system("sudo zypper install -y #{name}")
      errs << name unless ok
    end
  end
  errs
end

errs = []
# errs << install_group(groups[0])
# errs << install_group(groups[1])
errs << install_group(groups[2])
puts "[x] Errores: "
puts errs.sort.join(',')

exit 0

