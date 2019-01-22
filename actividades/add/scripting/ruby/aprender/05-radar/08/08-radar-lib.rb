
require 'rainbow'

def scan(ips)
  results = []
  ips.each_with_index do |ip, index|
    state = system("ping -c2 #{ip} > /dev/null")
    if state
      results << "ON  Host #{Rainbow(ip).bright}"
    else
      results << "#{Rainbow('OFF').red.bright} Host #{Rainbow(ip).bright}"
    end
  end
  return results
end

def show_results(results)
  system('clear')
  results.each_with_index do |line, index|
    puts "#{index} : #{line}"
  end
end
