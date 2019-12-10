require 'rainbow'

def get_network_information
  # Get network information
  data = {}
  items = %x[ip a | grep 'inet ' | grep -v 'host lo'].split
  data[:ip] = items[1]
  data[:device] = items.last

  items = %x[ip route | grep default].split
  data[:gateway] = items[2]

  ok = system('ping -c1 8.8.4.4 > /dev/null')
  data[:internet] = (ok ? 'Ok' : Rainbow('No access').red)
  ok = system('host www.nba.com > /dev/null')
  data[:dns] = (ok ? 'Ok' : Rainbow('No access').red)
  return data
end

def display_network_information
  data = get_network_information
  puts "[INFO] Displaying network configuration"
  puts "  Device   : #{Rainbow(data[:device]).bright}"
  puts "  IP/mask  : #{Rainbow(data[:ip]).bright}"
  puts "  Gateway  : #{Rainbow(data[:gateway]).bright}"
  puts "  Internet : #{Rainbow(data[:internet]).bright}"
  puts "  DNS      : #{Rainbow(data[:dns]).bright}"
end

def set_network_information(input)
  current = get_network_information
  puts "[INFO] Setting network configuration"
  puts "  Device          : #{Rainbow(input[:device]).blue}"
  puts "  Current IP/mask : #{Rainbow(current[:ip]).yellow}"
  puts "  New     IP/mask : #{Rainbow(input[:new_ip]).blue}"

  system("ip addr add #{input[:new_ip]} dev #{input[:device]}")
end
