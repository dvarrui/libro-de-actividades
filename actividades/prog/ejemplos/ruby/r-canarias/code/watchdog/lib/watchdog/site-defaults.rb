#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# These are, um, the default default values for all the user
# choices. Customizing them here will save people the trouble of
# creating their own config files.
#
# The best way to discover what the different options mean is to run
# watchdog with 'watchdog --help'

require 's4t-utils'

module Watchdog

  hostname = `hostname`
  hostname = 'localhost' unless $? == 0 
  DEFAULT_HOST=hostname.strip

  DEFAULT_JABBER='true'
  DEFAULT_MAIL='true'
  DEFAULT_COMMAND_LINE="true"  # Note: this is the only way you see
                               # command-line output.

  DEFAULT_MAIL_TO="watchlist@#{DEFAULT_HOST}"
  DEFAULT_MAIL_FROM="watchdog@#{DEFAULT_HOST}"
  DEFAULT_SMTP_SERVER=DEFAULT_HOST
  DEFAULT_SMTP_PORT='25'
  DEFAULT_MAIL_ACCOUNT="watchdog"
  DEFAULT_FROM_DOMAIN=DEFAULT_HOST
  DEFAULT_SMTP_PASSWORD="woof"
  DEFAULT_SMTP_AUTH="login"

  DEFAULT_JABBER_TO="watchlist@#{DEFAULT_HOST}/bark"
  DEFAULT_JABBER_ACCOUNT="watchdog@#{DEFAULT_HOST}/bark"
  DEFAULT_JABBER_PASSWORD="woof"
  
  if S4tUtils::on_windows?
    RC_FILE = "watchdog.xml"
  else
    RC_FILE = ".watchdog.xml"
  end

  DEFAULT_SHOW_CHOICES='false'
end
