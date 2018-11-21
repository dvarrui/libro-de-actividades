#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
# This is loaded by an executable script in one of two cases:
#
# It's a development version living in the bin part of this directory
# structure (but it can invoked from any place).
#
#   project/
#     bin/
#     lib/
#        project/
#           third-party/
#              s4t-utils/
#                 this file
#
# It's a deployed version living in some random place, with 
# the site_ruby directory in the page.
#
#   site_ruby/1.8/
#      project/
#        third-party/
#           s4t-utils/
#              this file
#
#
# In order for this file to have been required in both cases, the following
# code is executed in the caller:
#
#
# $:.unshift((Pathname.new(__FILE__).parent.parent + 'lib').to_s)
# require 'package/third-party/s4t-utils/load-path-auto-adjuster'
#
# In the first case, that will put something like "../lib" on the load
# path.  In the second case, it will put harmless garbage on the path
# (harmless because it won't contain this file, which will still be
# found somewhere later in the load path). 
#
# The first thing this file does is pop that off, it having done its job.
# In the first (development) case, it puts the following on the load path:
#        project/lib & project/lib/project/third-party & project
# ('project' is added so that <require 'test/util-file'> works.)
#
#    In the second, it adds only the third-party library and takes care
#    to add it just after whatever component in the path contains this
#    file. (It will thus not interfere with clashing packages earlier
#    in the path.) 
#        site_ruby/1.8/project/third-party
#    since site_ruby/1.8 (or the equivalent) is already on there.

require 'rubygems'
require 'pathname'

module S4tUtils
  module Hidden  # This should not interfere with any namespace.

    class Arranger
      def initialize(third_party)
        @third_party_lib = third_party
        @project_lib = third_party.parent.parent
        @test_util_root = @project_lib.parent
      end

      def self.arrange_path_around(third_party)
        new(third_party).arrange
      end

      def arrange
        add_third_party_gems
        if project_lib_already_in_path?
          just_add_third_party_after_project_lib
        else
          add_everything_at_front
        end
      end
        

      def add_third_party_gems
        # When RubyGems 0.8.11 gets clear_paths, it does not clear the
        # cache used by Gem's overriding version of require(), so if
        # this is loaded after the first Gem is required, it will have
        # no effect on later uses of require(). (But it does affect
        # require_gem.)
        # 
        ENV['GEM_PATH']=(@third_party_lib+'gems').to_s
        Gem.clear_paths
      end

      def project_lib_already_in_path?
        $:.include?(@project_lib.to_s)
      end

      def just_add_third_party_after_project_lib
        $:.each_with_index do | path_element, index |
          if path_element == @project_lib.to_s
            $:[index+1,0] = @third_party_lib.to_s
            return
          end
        end
        fail "No place to put third_party library."
      end

      def add_everything_at_front
        $:.unshift(@test_util_root.to_s)  
        $:.unshift(@third_party_lib.to_s)
        $:.unshift(@project_lib.to_s)  # This is now first
      end
    end

    def self.auto_adjust_load_path
      $:.shift   # Remove extra element used to find this file.
      
      # Having loaded us, __FILE__ is something like this:
      # ...lib.../package/third-party/s4t-utils/load-path-auto-adjuster.rb
      relative_third_party = Pathname.new(__FILE__).parent.parent
      # Pathname#real_path doesn't work on Windows (1.8.2). Grr.
      third_party = Pathname.new(File.expand_path(relative_third_party.to_s))
      Arranger.arrange_path_around(third_party)
    end

    auto_adjust_load_path
  end
end
