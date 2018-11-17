The watchdog program watches a program execute and "barks" via one of
several means (IM, mail, etc.) when it finishes.

It can be configured with a site-wide configuration file, a per-user
configuration file, or the command line. See watchdog.rb --help for a
list of things to tweak.

To install:

prompt> rake install            # Run with administrator privileges

- or - 

prompt> ruby setup.rb config
prompt> ruby setup.rb setup
prompt> ruby setup.rb install    # Run with administrator privileges.


After installation, change site defaults by editing
lib/watchdog/site-defaults.rb.



