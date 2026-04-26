class UnidadsController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @unidad_pages, @unidads = paginate :unidads, :per_page => 10
  end

  def show
    @unidad = Unidad.find(params[:id])
  end

  def new
    @unidad = Unidad.new
    @modulos = Modulo.find_all
  end

  def create
    @unidad = Unidad.new(params[:unidad])
    if @unidad.save
      flash[:notice] = 'Unidad was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @unidad = Unidad.find(params[:id])
    @modulos = Modulo.find_all
  end

  def update
    @unidad = Unidad.find(params[:id])
    if @unidad.update_attributes(params[:unidad])
      flash[:notice] = 'Unidad was successfully updated.'
      redirect_to :action => 'show', :id => @unidad
    else
      render :action => 'edit'
    end
  end

  def destroy
    Unidad.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
