class HorariosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @horario_pages, @horarios = paginate :horarios, :per_page => 10
  end

  def show
    @horario = Horario.find(params[:id])
  end

  def new
    @horario = Horario.new
    @aulas = Aula.find_all
    @modulos = Modulo.find_all
    @profesores = Profesor.find_all
  end

  def create
    @horario = Horario.new(params[:horario])
    if @horario.save
      flash[:notice] = 'Horario was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @horario = Horario.find(params[:id])
    @aulas = Aula.find_all
    @modulos = Modulo.find_all
    @profesores = Profesor.find_all
  end

  def update
    @horario = Horario.find(params[:id])
    if @horario.update_attributes(params[:horario])
      flash[:notice] = 'Horario was successfully updated.'
      redirect_to :action => 'show', :id => @horario
    else
      render :action => 'edit'
    end
  end

  def destroy
    Horario.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
