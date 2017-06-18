
# Crear nuestro paquete rpm

Enlaces de interés:
* [Build rpm packages with the rpmbuild command](http://www.linuxintro.org/wiki/Build_rpm_packages_with_the_rpmbuild_command)

---

# ANEXO

Enlaces de interés:
* [OpenSUSE – Build a rpm package](https://eureka.ykyuen.info/2009/12/28/opensuse-build-a-rpm-package/)
* [Build RPM Package for Installation and Management by System Package Manager](https://www.ordinatechnic.com/os-specific-guides/opensuse/build-rpm-package-for-local-installation)

```
Rebuilding an existing src.rpm is probably the easiest. I would definitely not go the way of configure, make, make install because (a) that doesn't scale, (b) doesn't provide for easy removal / upgrading of the package and (c) is not atomic.

Building an RPM is not so hard. There used to be a pretty good beginners tutorial at Linux.com, before they destroyed the site. You can try this one, but it's a lot of text, not easy to digest. Still, it's better than nothing.

I'll give you a few pointers, out of the back of my head:

   setup an .rpmmacros file in ~
   create ~/rpmbuild/{RPMS,SRPMS,SPECS,BUILD,BUILDROOT,SOURCES}
   drop the source tarball in ~/rpmbuild/SOURCES
   drop the the spec file(s) in ~/rpmbuild/SPECS
   run rpmbuild -bp YOURSPEC in ~/rpmbuild/SPECS (runs prepare phase)
   run rpmbuild -bc YOURSPEC in ~/rpmbuild/SPECS (above and runs compile phase)
   run rpmbuild -bb YOURSPEC in ~/rpmbuild/SPECS (above and builds actual package)

If everything worked out, your RPM will have appeared in ~/rpmbuild/RPMS/${arch}.

I tend to use 5, 6 and 7 separately when creating new RPM's, because I like to inspect the ouput in detail. You might want to go for 7 immediately if you have a vendor provided specfile.

I usually have something like this in my .rpmmacros.

%_topdir /home/YOURNAME/rpmbuild
%packager YOUR NAME <YOUR@EMA.IL>
%_tmppath /tmp

As for the syntax of the specfile: it is not that hard. There exists a very detailed, be it very old, reference work called 'Maximum RPM'. Everything you want to know is in there.
```
