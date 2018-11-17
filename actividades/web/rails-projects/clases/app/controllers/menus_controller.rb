class MenusController < ApplicationController
  def index
    redirect_to(:action => 'recursos')
  end

  def recursos
    redirect_to( :controller => 'centros')
  end
  
  def inicio
    redirect_to( :controller => 'cursos')
  end
  
  def diario
    redirect_to( :controller => 'actividads')
  end
  
end
