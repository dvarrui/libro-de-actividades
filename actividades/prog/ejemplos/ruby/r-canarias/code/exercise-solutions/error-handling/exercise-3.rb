#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
class TestRunner
  def download(tests, machine)
    @tests = tests
    tests.each do | test | 
      puts "Downloading #{test} to #{machine.name}..."
    end
  end

  def run
    @tests.each do | test | 
      puts "Running #{test}..."
      raise "network down" if test == 'test3'
    end
  end
end

class Machine
  attr_reader :name
  def initialize(name)
    @name = name
    raise "Could not reserve #{name}." if ARGV[0]
  end
end
  

class Reserver
  def reserve(machine_name)
    machine = Machine.new(machine_name)
    yield machine
    puts "Reserved #{machine.name}."
    machine
  rescue Exception => ex
    puts "Test failure: #{ex.message}"
  ensure
    release(machine) if machine
  end

  def release(machine)
    puts "Released #{machine.name}."
  end
end


reserver = Reserver.new
test_runner = TestRunner.new
tests = ['test1', 'test2', 'test3']


reserver.reserve('Mycroft') do | machine | 
  test_runner.download(tests, machine)
  test_runner.run
end

