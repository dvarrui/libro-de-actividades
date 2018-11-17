
bin_PROGRAMS = aliens

EXTRA_DIST =			\
	CHANGES			\
	autogen.sh		\
	VisualC.zip		\
	MPWmake.sea.hqx	

aliens_SOURCES =		\
	aliens.c

aliens_LDADD = -lSDL_mixer -lSDL_image

dist-hook:
	cp -rp data $(distdir)
	
