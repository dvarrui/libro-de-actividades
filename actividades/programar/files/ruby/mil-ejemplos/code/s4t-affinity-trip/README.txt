This is a project that takes an affinity trip through Amazon.com. Try
this:
% ruby bin/affinity-trip.rb

More importantly, this shows how a simple project (affinity-trip) can
be converted into the "scripting for testers" structure. 

To install:

prompt> rake install            # Run with administrator privileges

- or - 

prompt> ruby setup.rb config
prompt> ruby setup.rb setup
prompt> ruby setup.rb install    # Run with administrator privileges.

Rake tasks:

rake                    -- runs all tests

rake fast               -- runs the fast tests

rake update-peers       -- if this is used as a third-party package
                           in any directory in .., push new version
                           to them.

rake move-on            -- run tests, update version number, push to
                           subversion (if it's used), and update
                           peers. Requires confirmation because command
                           replay makes it too easy to run by accident
                           in the wrong folder.

rake install            -- Install lib into default destination
                           (site_ruby). Runs tests first.
