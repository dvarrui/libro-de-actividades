class DepartamentosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @departamento_pages, @departamentos = paginate :departamentos, :per_page => 10
    @departamentos = Departamento.find(:all, :order => 'nombre')
  end

  def show
    @departamento = Departamento.find(params[:id])
  end

  def new
    @departamento = Departamento.new
  end

  def create
    @departamento = Departamento.new(params[:departamento])
    if @departamento.save
      flash[:notice] = 'Departamento creado correctamente.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @departamento = Departamento.find(params[:id])
  end

  def update
    @departamento = Departamento.find(params[:id])
    if @departamento.update_attributes(params[:departamento])
      flash[:notice] = 'Departamento modificado correctamente.'
      redirect_to :action => 'show', :id => @departamento
    else
      render :action => 'edit'
    end
  end

  def destroy
    Departamento.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
