#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'


class AutoAdjusterTests < Test::Unit::TestCase
  class FakePath
    attr_reader :parent
    def initialize(name, parent=nil)
      @name = name
      @parent = parent
    end

    def +(string)
      FakePath.new(self.to_s + '/' + string, self)
    end

    def to_s; @name; end

    def self.ordinary_structure_returning_third_party
      root = FakePath.new('root')
      lib = FakePath.new('lib', root)
      project = FakePath.new('project', lib)
      FakePath.new('third-party', project)
    end
  end

  module PathHandling
    module_function
    def replace_path(path)
      $:.clear
      path.each { | element | 
        $: << element
      }
    end

    def with_path(path)
      old = $:.dup
      replace_path(path)
      yield
    ensure
      replace_path(old)
    end
  end

  include PathHandling
  PathHandling.with_path($:.dup) {
    require 's4t-utils/load-path-auto-adjuster'
  }
  include S4tUtils

  def setup
    @std_third_party = FakePath.ordinary_structure_returning_third_party
  end

  def test_notices_when_library_already_in_path
    with_path(%w{ root lib project third-party}) {
      assert(Hidden::Arranger.new(@std_third_party).project_lib_already_in_path?)
    }
  end

  # The checks that the Gem path is correct are commented out below
  # because checking the Gem path causes subdirectories to be created,
  # thus leaving little "/gem/" droppings all over the test directory
  # or wherever the test is run from. 

  def test_adds_third_party_if_lib_already_in_path
    with_path(%w{ local lib something-later}) {
      Hidden::Arranger.new(@std_third_party).arrange
      assert_equal(%w{local lib third-party something-later},
                   $:)
    }

    # assert(Gem.path.include?("#{@std_third_party.to_s}/gems"))
  end
    

  def test_adds_everything_if_lib_not_already_in_path
    with_path(%w{ /bin /usr/local/bin }) {
      Hidden::Arranger.new(@std_third_party).arrange
      assert_equal(%w{lib third-party root /bin /usr/local/bin},
                   $:)
    }

    # assert(Gem.path.include?("#{@std_third_party.to_s}/gems"))
  end
    

end
