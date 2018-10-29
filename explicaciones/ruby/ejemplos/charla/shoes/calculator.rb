require 'shoes'

Shoes.app(title: "My calculator", width: 200, height: 240) do
  number_field = nil
  @number = 0
  @op = nil
  @previous = 0

  flow width: 200, height: 240 do
    flow width: 0.7, height: 0.2 do
      background rgb(0, 157, 228)
      number_field = para @number, margin: 10
    end

    flow width: 0.3, height: 0.2 do
      button 'Clr', width: 1.0, height: 1.0 do
        @number = 0
        number_field.replace(@number)
      end
    end

    flow width: 1.0, height: 0.8 do
      background rgb(139, 206, 236)
      %w(7 8 9 + 4 5 6 - 1 2 3 / 0 . = *).each do |btn|
        button btn, width: 50, height: 50 do
          case btn
            when /[0-9]/
              @number = @number.to_i * 10 + btn.to_i

            when '='
              @number = @previous.send(@op, @number)
            else
              @previous, @number = @number, nil
              @op = btn
          end
          number_field.replace(@number)
        end
      end
    end
  end
end
