#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module S4tUtils

  module_function

  def broaden_test_name(name)
    if /test$/ =~ name
      "{#{name},#{name}s}"
    elsif /tests$/ =~ name
      "{#{name[0...-1]},#{name}}"
    end
  end

  def pending?(test)
    /^pending[.-]/ =~ File.basename(test)
  end

  def desired_subset(tests,
                     pendingp = ENV['S4T_ONLY_PENDING_TESTS'] == 'true')
    if pendingp
      tests
    else
      tests.reject { | test | pending?(test) }
    end
  end

  def run_particular_tests(where, which)
    Dir.chdir(where) { 
      test_description = case which
                         when nil, 'all', 'alltests'
                           "**/*{tests,test}.rb"
                         when 'fast'
                           "**/*-{tests,test}.rb"
                         when 'slow'
                           "**/*-{slowtests,slowtest}.rb"
                         else
                           which = broaden_test_name(which)
                           "**/*-#{which}.rb"
                         end

      tests = desired_subset(Dir.glob(test_description))
      tests.each do | testfile |
        puts testfile
        require testfile
      end
    }
  end

  def increment_version(version_file)
    text = IO.read(version_file)
    text.gsub!(/Version\s*=\s*'(\d+)\.(\d+)\.(\d+)'/) { | match |
      update = "Version = '#{$1}.#{$2}.#{$3.to_i+1}'"
      puts update
      update
    }
    File.open(version_file, 'w') do | io | 
      io.write(text)
    end
  end

  def generated_taskname(string, prefix="__generated__")
    prefix + symbol_safe_name(string)
  end

  def update_peer_subtask_name(filesystem_name)
    generated_taskname(filesystem_name, "__update_peers_")
  end

  def subprocess(args)
    Dir.chdir($test_search_root) do
      cmd = "ruby -S rake #{$rakefile_local} #{args}"  # Windows can't find Rake directly.
      system(cmd) 
      raise "#{cmd} failed" unless $?==0
    end
  end
end
