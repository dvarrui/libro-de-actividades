class ModulosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @modulo_pages, @modulos = paginate :modulos, :per_page => 10
  end

  def show
    @modulo = Modulo.find(params[:id])
  end

  def new
    @modulo = Modulo.new
    @ciclos = Ciclo.find_all
    @profesores = Profesor.find_all
  end

  def create
    @modulo = Modulo.new(params[:modulo])
    if @modulo.save
      flash[:notice] = 'Modulo was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @modulo = Modulo.find(params[:id])
    @ciclos = Ciclo.find_all
    @profesores = Profesor.find_all
  end

  def update
    @modulo = Modulo.find(params[:id])
    if @modulo.update_attributes(params[:modulo])
      flash[:notice] = 'Modulo was successfully updated.'
      redirect_to :action => 'show', :id => @modulo
    else
      render :action => 'edit'
    end
  end

  def destroy
    Modulo.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
