#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'user-choices'
require 'pp'
require 'watchdog/site-defaults'
require 'watchdog/kennel'

module Watchdog
  include UserChoices

  class WatchdogCommand < Command

    def add_sources(builder)
      builder.add_source(PosixCommandLineChoices, :usage,
            "Usage: ruby #{$0} [options] program args...",
            "Site-wide defaults are noted below.",
            "Override them in the '#{RC_FILE}' file in your home folder.")
      builder.add_source(XmlConfigFileChoices, :from_file, RC_FILE)  # (1)
    end



    def add_choices(builder)
      builder.add_choice(:jabber,
                         :type => :boolean,
                         :default => DEFAULT_JABBER) { | command_line |
        command_line.uses_switch('-j', "--jabber",
                                 "Control IM notification.",
                                 "Defaults to #{DEFAULT_JABBER}.")
      }

      builder.add_choice(:mail,
                         :type => :boolean,
                         :default => DEFAULT_MAIL) { | command_line |
        command_line.uses_switch('-m', "--mail",
                                 "Control mail notification.",
                                 "Defaults to #{DEFAULT_MAIL}.")
      }


      builder.add_choice(:command_line,
                         :type => :boolean,
                         :default => DEFAULT_COMMAND_LINE) { | command_line |
        command_line.uses_switch("-t", "--terminal", '-s',
                                 "Control display to terminal (standard output).",
                                 "Defaults to #{DEFAULT_COMMAND_LINE}.")
      }

      builder.add_choice(:mail_to,
                         :default => DEFAULT_MAIL_TO,
                         :type => [:string]) { | command_line |
        command_line.uses_option("--mail-to RECIPIENTS",
                                 "Recipients of mail.",
                                 "This can be a comma-separated list.",
                                 "Defaults to #{DEFAULT_MAIL_TO}."
                                 )
      }

      builder.add_choice(:mail_from,
                         :default => DEFAULT_MAIL_FROM) { | command_line |
        command_line.uses_option("--mail-from SENDER", 
                                 "The sender of the mail (appears in From line).",
                                 "Defaults to #{DEFAULT_MAIL_FROM}."
                                 )
      }

      builder.add_choice(:mail_server,
                         :default => DEFAULT_SMTP_SERVER) { | command_line |
        command_line.uses_option("--mail-server HOSTNAME", 
                                 "SMTP server. Defaults to #{DEFAULT_SMTP_SERVER}."
                                 )
      }

      builder.add_choice(:mail_port,
                         :type => :integer,
                         :default => DEFAULT_SMTP_PORT) { | command_line |
        command_line.uses_option("--mail-port NUMBER", 
                                 "Mail port on that server. (Defaults to #{DEFAULT_SMTP_PORT}.)"
                                 )
      }

      builder.add_choice(:mail_account,
                         :default => DEFAULT_MAIL_ACCOUNT) { | command_line |
        command_line.uses_option("--mail-account USERNAME", 
                                 "Your account name on the SMTP server.",
                                 "Defaults to #{DEFAULT_MAIL_ACCOUNT}."
                                 )
      }

      builder.add_choice(:mail_from_domain,
                         :default => DEFAULT_FROM_DOMAIN) { | command_line |
        command_line.uses_option("--mail-from-domain HOSTNAME", 
                                 "The server the mail supposedly comes from.",
                                 "(Not necessarily the SMTP server.)",
                                 "Defaults to #{DEFAULT_FROM_DOMAIN}."
                                 )
      }

      builder.add_choice(:mail_password,
                         :default => DEFAULT_SMTP_PASSWORD) { | command_line |
        command_line.uses_option("--mail-password HOSTNAME", 
                                 "Your password on the SMTP server.",
                                 "Defaults to #{DEFAULT_SMTP_PASSWORD}."
                                 )
      }

      auth_types = ['plain', 'login', 'cram_md5']
      builder.add_choice(:mail_authentication,
                         :type => auth_types,
                         :default => DEFAULT_SMTP_AUTH) { | command_line |
        command_line.uses_option("--mail-authentication TYPE",
                                 "The kind of authentication your SMTP server uses.",
                                 "One of #{friendly_list('or', auth_types)}.",
                                 "Defaults to #{DEFAULT_SMTP_AUTH}."
                                 )
      }


      builder.add_choice(:jabber_to,
                         :default => DEFAULT_JABBER_TO,
                         :type => [:string]) { | command_line |
        command_line.uses_option("--jabber-to RECIPIENTS",
                                 "Recipients of Jabber instant messages.",
                                 "This can be a comma-separated list.",
                                 "Defaults to #{DEFAULT_JABBER_TO}."
                                 )
      }

      builder.add_choice(:jabber_account,
                         :default => DEFAULT_JABBER_ACCOUNT) { | command_line |
        command_line.uses_option("--jabber-account SENDER", 
                                 "Your Jabber account.",
                                 "Note that this includes the Jabber server.",
                                 "Defaults to #{DEFAULT_JABBER_ACCOUNT}."
                                 )
      }

      builder.add_choice(:jabber_password,
                         :default => DEFAULT_JABBER_PASSWORD) { | command_line |
        command_line.uses_option("--jabber-password PASSWORD", 
                                 "Your Jabber password.",
                                 "Defaults to #{DEFAULT_JABBER_PASSWORD}."
                                 )
      }




      builder.add_choice(:command_to_watch) { | command_line |
        command_line.uses_arglist
      }
                         

      builder.add_choice(:choices,
                         :default => DEFAULT_SHOW_CHOICES,
                         :type => :boolean) { | command_line |
        command_line.uses_switch('-c', "--choices",
                                 "Show all configuration choices.")
      }
                         

    end


    def postprocess_user_choices
      if @user_choices[:choices]
        puts "Choices gathered from all sources:"
        pp @user_choices
        puts "Looking for configuration information in:"
        puts File.join(S4tUtils.find_home, RC_FILE)
      end


      @user_choices[:mail_authentication] =
        @user_choices[:mail_authentication].to_sym
      errors = []

      # This is checked here, instead of giving a range to uses_arglist,
      # so that the actual choices can be printed out with -c without
      # having to give an argument.
      if @user_choices[:command_to_watch].empty?
        errors << "No command to run was given."
      end


      @kennel = Kennel.new   #(2) 
      Barker::Subclasses.each do | barker_class | #(3) 
        barker = barker_class.new(@user_choices)  #(4) 
        errors += barker.errors  #(5) 
        barker.invite_into(@kennel) #(6) 
      end

      raise errors.join("\n") unless errors.empty?  #(7) 


    end
  end
end
