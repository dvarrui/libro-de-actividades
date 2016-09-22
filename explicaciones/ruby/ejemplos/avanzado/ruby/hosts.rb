#!/usr/bin/ruby
# encoding: utf-8

=begin
Author      : David Vargas
Date        : 20-03-2016
Description : Define group of hosts
Disclaimer  : Sorry about my english! I'm Spanish speaker.
=end

def departamento
  equipos=[]
  (1..5).each { |i| equipos << "172.31.#{i}.0" }
  return equipos
end

def aula109old
  equipos=[]
  (1..28).each { |i| equipos << "172.19.#{i}.0" }
  return equipos
end

def aula109
  equipos=[ '172.19.99.69' ]
  (2..18).each { |i| equipos << "172.19.#{i}.0" }
  [70,73,66,65,64,63,68,74,67,62].each { |i| equipos << "172.19.99.#{i}" }
  return equipos
end

def aula109_solonuevos
  equipos=[ ]
  [70,73,66,65,64,63,68,74,67,62].each { |i| equipos << "172.19.99.#{i}" }
  return equipos
end
