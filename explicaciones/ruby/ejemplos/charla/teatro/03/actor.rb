
require 'rainbow'

class Actor
  def initialize(name, color=:green)
    @color = color
    @name = Rainbow(name).color(color).bright
    @tab = ' ' * name.size
    @num_frases = 0
    @num_palabras = 0
  end

  def dice(texto)
#    dice_tranquilo Rainbow(texto).color(@color).bright
    dice_tranquilo texto
  end

  def dice_gritando(texto)
    dice_tranquilo Rainbow(texto.upcase).red.bright
  end

  def dice_tranquilo(texto)
    frases = texto.split('.')
    # Decir la primera frase
    puts @name + ' : ' + frases[0].strip + '.'
    frases.delete_at 0
    # Decir el resto
    frases.each do |frase|
      puts @tab + ' : ' + frase.strip + '.'
    end
    contar_las_palabras_del texto
  end


  def info
    puts @name.strip
    puts " * He tenido #{@num_frases} frases."
    puts " * He dicho  #{@num_palabras} palabras."
  end

  private

  def contar_las_palabras_del(texto)
    @num_frases += 1
    @num_palabras += texto.split(' ').count
  end

end
