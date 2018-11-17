#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'stringio'

module S4tUtils

  module_function

  def capturing_stderr
    old_stderr = $stderr
    new_stderr = StringIO.new
    begin
      $stderr = new_stderr
      yield
    ensure
      $stderr = old_stderr
    end
    new_stderr.string
  end

  def with_environment_vars(settings)
    begin
      old = {}
      settings.each { | key, value |
        old[key] = ENV[key]
        ENV[key] = value
      }
      yield
    ensure
      settings.each_key { | key |
        ENV[key] = old[key]
      }
    end
  end

  def with_home_right_here
    begin
      old_home = ENV['HOME']
      ENV['HOME'] = '.'
      yield
    ensure
      ENV['HOME'] = @old_home
    end
  end

  def erasing_local_config_file(file)
    with_home_right_here { 
      begin
        File.delete(file) if File.exist?(file)
        yield
      ensure
        File.delete(file) if File.exist?(file)
      end
    }
  end

  def with_local_config_file(file, contents)
    erasing_local_config_file(file) do
      File.open(file, 'w') do | io |
        io.puts(contents.to_s)
      end
      yield
    end
  end

  def with_command_args(string)
    begin
      old_argv = ARGV.dup
      ARGV.replace(string.split)
      yield
    rescue SystemExit => ex
      replacement = StandardError.new(ex.message)
      replacement.set_backtrace(ex.backtrace)
      raise replacement
    ensure
      ARGV.replace(old_argv)
    end
  end

end
