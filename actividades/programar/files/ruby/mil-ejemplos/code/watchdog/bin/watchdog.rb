#! /opt/local/bin/ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'pathname'
$:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
require 'watchdog/third-party/s4t-utils/load-path-auto-adjuster'

require 'extensions/string'
require 'open3'
require 's4t-utils'
include S4tUtils

require 'watchdog'
include Watchdog


class WatchdogCommand < Command

  def command_string(command_to_watch =
                       @user_choices[:command_to_watch])
    command_to_watch.join(' ')
  end

  def command_name(command_to_watch = @user_choices[:command_to_watch])
    progname = if command_to_watch[0] == 'ruby'
                 command_to_watch[1]
               else
                 command_to_watch[0]
               end
    File.basename(progname)
  end

  def message(duration, text)
    [
      "Duration: #{duration} seconds.",
      "Command: #{command_string}",
      "Output:",
      text.indent(2),
    ].join("\n")
  end

  def execute   # (1)
    duration, text = Watchdog.time {  # (2)
      `#{self.command_string} 2>&1`   # (3) 
    }
    title = "Program #{self.command_name} finished."
    @kennel.bark(title, message(duration, text))   # (4)
  end

end    

if $0 == __FILE__
  with_pleasant_exceptions do
    WatchdogCommand.new.execute  # (5)
  end
end

