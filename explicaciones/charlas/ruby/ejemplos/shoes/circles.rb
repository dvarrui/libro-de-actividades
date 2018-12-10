
require ' shoes'
Shoes.app(width: 300, height: 400) do
   fill rgb(0, 0.6, 0.9, 0.1)
   stroke rgb(0, 0.6, 0.9)
   strokewidth 0.25

   100.times do
     oval(left:   rand(-5..self.width),
          top:    rand(-5..self.height),
          radius: rand(25..50))
   end
 end
