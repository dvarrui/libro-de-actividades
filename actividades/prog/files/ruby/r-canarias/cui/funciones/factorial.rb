
# Programa para hallar el factorial de un número
def factorial(n)
  if n == 0
    1
  else
    n * factorial(n-1)
  end
end

#print factorial(ARGV[0].to_i), "\n"
n=20
print n.to_s,"! = ",factorial(n), "\n"

