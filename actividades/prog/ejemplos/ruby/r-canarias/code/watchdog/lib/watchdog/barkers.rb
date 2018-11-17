#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'net/smtp'
require 'xmpp4r'

require 'extensions/string'

module Watchdog

  class Barker
    def bark(subject, body); subclass_responsibility; end
    def name; subclass_responsibility; end
    def symbol; :this_barker_can_never_be_chosen; end

    def validate; []; end


    attr_reader :errors



    def initialize(user_choices = {})
      @user_choices = user_choices

      @errors = self.validate
    end



    def invite_into(kennel)
      return unless errors.empty?
      return unless wanted?

      kennel.add(self)   

    end



    def wanted?
      @user_choices[self.symbol]
    end



    Subclasses = []
    
    def self.inherited(new_child_class)
      Subclasses << new_child_class
    end

  end

  class StdoutBarker < Barker
    def name; "terminal"; end
    def symbol; :command_line; end

    def bark(subject, body)
      all = [subject, body].join("\n")
      puts all.indent(2)
    end
  end
  
  class JabberBarker < Barker
    include Jabber

    def name; "jabber"; end

    def symbol; :jabber; end


    def validate
      errors = []
      unless @user_choices[:jabber_account]
        errors << "There is no Jabber account to log in as. (jabber_account)"
      end

      unless @user_choices[:jabber_password]
        errors << "There is no Jabber password. (jabber_password)"
      end

      jabber_to = @user_choices[:jabber_to]
      if jabber_to == nil or jabber_to.empty?
        errors << "There is no destination for Jabber messages. (jabber_to)"
      end
      errors
    end


    # This shows a possible implementation for the book:
    def invite_into(kennel)
      kennel.add(self) if @user_choices[:jabber]
    end


    # Use the superclass method for real.
    def invite_into(kennel); super; end

    def bark(subject, body)
      my_jid = JID.new(@user_choices[:jabber_account])
      cl = Client.new(my_jid, false)
      cl.connect
      cl.auth(@user_choices[:jabber_password])
      body = [subject, body].join("\n")
      @user_choices[:jabber_to].each do | recipient | 
        m = Message::new(recipient, body).
          set_type(:normal).set_id('1').set_subject(subject)
        cl.send(m)
      end
      cl.close
    end

  end

  class SmtpBarker < Barker

    def name; 'mail'; end
    def symbol; :mail; end

    def validate
      errors = []
      mail_to = @user_choices[:mail_to]
      if mail_to == nil or mail_to.empty?
        errors << "There are no recipients. (mail_to)"
      end

      unless @user_choices[:mail_from]
        errors << "There is nothing for the From line. (mail_from)"
      end

      unless @user_choices[:mail_server]
        errors << "There is no server to send to. (mail_server)"
      end

      unless @user_choices[:mail_account]
        errors << "There is no SMTP account to send mail through. (mail_account)"
      end

      unless @user_choices[:mail_password]
        errors << "There is no password for the SMTP account. (mail_password)"
      end

      unless @user_choices[:mail_port]
        errors << "There is no port for the SMTP host. (mail_port)"
      end

      unless @user_choices[:mail_from_domain]
        errors << "There is no domain to use as the From domain. (mail_from_domain)"
      end

      unless @user_choices[:mail_authentication]
        errors << "No authentication method was chosen. (mail_authentication)"
      end
      errors
    end

    
    def bark(subject, message)

      from = @user_choices[:mail_from]
      to = @user_choices[:mail_to]

      Net::SMTP.start(@user_choices[:mail_server], @user_choices[:mail_port],
                      @user_choices[:mail_from_domain],
                      @user_choices[:mail_account],
                      @user_choices[:mail_password],
                      @user_choices[:mail_authentication]) { | smtp |
        mail = "
                . From: #{from}
                . To: #{to.join(', ')}
                . Subject: [watchdog] #{subject}
                .
                . #{message}
               ".trim('.')
        smtp.send_message(mail, from, to)
      }
    end
  end


end
