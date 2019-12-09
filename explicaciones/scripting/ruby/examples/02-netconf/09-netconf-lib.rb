require 'rainbow'

def get_network_information
  # Get network information
  data = {}
  items = %x[ip a | grep 'inet ' | grep -v 'host lo'].split
  data[:ip] = items[1]
  data[:if_name] = items[7]

  items = %x[ip route | grep default].split
  data[:gateway] = items[2]

  ok = system('ping -c1 8.8.4.4 > /dev/null')
  data[:internet] = (ok ? 'Ok' : Rainbow('No access').red)
  return data
end

def display_network_information
  data = get_network_information
  # Display Network information
  puts "[INFO] Showing network configuration"
  puts "  IF name  : #{Rainbow(data[:if_name]).bright}"
  puts "  IP/mask  : #{Rainbow(data[:ip]).bright}"
  puts "  Gateway  : #{Rainbow(data[:gateway]).bright}"
  puts "  Internet : #{Rainbow(data[:internet]).bright}"
end
