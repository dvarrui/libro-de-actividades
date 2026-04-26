class Centro < ActiveRecord::Base
	belongs_to :municipio
	has_many :aulas, :order => :codigo
	has_many :ciclos, :order => :codigo
	has_many :departamentos, :order => :codigo
	has_many :profesores, :through => :departamentos
	has_many :empresas, :order => :nombre
	has_many :cursos, :order => :inicio_clases

	def horas
		sal = Array.new
		pos = 0
		i = horario.split(',')
		i.each do |j|
			k = j.split('=')
			clave = k[0].strip
			valor = k[1].strip
			if clave[0..0]=='h' || clave[0..0]=='c'
				pos = pos+1
				m = Array.new
				#Número de orden de la hora de clase, hora de comenzo de la clase, tipo de hora=clase
				m[0]=pos; m[1]=valor; m[2]='h';
			elsif clave[0..0]=='d' || clave[0..0]=='b'
				m = Array.new
				#Número de orden de la hora de descanso, hora de comenzo del descanso, tipo de hora=descanso
				m[0]=pos*-1; m[1]=valor; m[2]='d';
			elsif clave[0..0]=='f' || clave[0..0]=='e'
				m = Array.new
				#Número de orden de la hora final, hora de fin de jornada, tipo de hora=fin de jornada
				m[0]=pos*-1; m[1]=valor; m[2]='f';
			end
			sal << m
		end
		
		return sal
	end
end
