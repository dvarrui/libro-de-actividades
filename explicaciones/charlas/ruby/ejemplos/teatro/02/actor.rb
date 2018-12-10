
class Actor
  def initialize(name)
    @name = name
    @num_frases = 0
    @num_palabras = 0
  end

  def dice(texto)
    info = @name
    frases = texto.split('.')
    # Decir la primera frase
    puts info + ' : ' + frases[0].strip + '.'
    frases.delete_at 0
    info = ' '* @name.size
    frases.each do |frase|
      puts info + ' : ' + frase.strip + '.'
    end
    contar_las_palabras_del texto
  end

  def dice_gritando(texto)
    dice texto.upcase
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
