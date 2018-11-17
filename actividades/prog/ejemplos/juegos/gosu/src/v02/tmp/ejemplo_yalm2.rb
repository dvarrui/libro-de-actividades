#!/usr/bin/ruby

require 'yaml'

class Person
  attr_accessor :name, :age
end

yaml_string = <<END_OF_DATA
---
- !ruby/object:Person
  age: 45
  name: Jimmy
- !ruby/object:Person
  age: 23
  name: Laura Smith
END_OF_DATA

test_data = YAML::load(yaml_string)
puts test_data[0].name
puts test_data[1].name
