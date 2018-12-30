
# I love ruby

![](images/iloveruby.png)

---

# Variables locales

Integer y Float:
```
age   = 42            # Integer
price = 9.99          # Float
42.to_s               # "42"
```

String:

```
name = "David"        # String
name.upcase           # DAVID
name + "ubi"          # Davidubi
name.gsub('v','b')    # Dabid
name.chars            # ["D", "a", "v", "i", "d"]
"16".to_i             # 16
```

```
puts "My name is #{name}. I'm #{age} years old."
```

Array:
```
jedis = [ 'obiwan', 'yoda', 'quigon'] # Array
jedis.size                            # 3
jedis[1]                              # yoda

numbers = []       # []
numbers << 16      # [16]
numbers << 11      # [16, 11]
numbers << 70      # [16, 11, 70]

```

Hash:
```
me    = { name: 'david', age: 42 }       # Hash
me    = { :name => 'david', :age => 42 } # Hash
```

---

# Variables globales

Variables de instancia:

Variables de clase:

Constantes:
