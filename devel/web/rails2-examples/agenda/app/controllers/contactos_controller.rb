class ContactosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @contacto_pages, @contactos = paginate :contactos, :per_page => 10
  end

  def show
    @contacto = Contacto.find(params[:id])
  end

  def new
    @contacto = Contacto.new
    @categorias = Categoria.find(:all)
  end

  def create
    @contacto = Contacto.new(params[:contacto])
    @contacto.categoria = Categoria.find_first
    if @contacto.save
      flash[:notice] = 'Contacto was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @contacto = Contacto.find(params[:id])
    @categorias = Categoria.find(:all)
  end

  def update
    @contacto = Contacto.find(params[:id])
    if @contacto.update_attributes(params[:contacto])
      flash[:notice] = 'Contacto was successfully updated.'
      redirect_to :action => 'show', :id => @contacto
    else
      render :action => 'edit'
    end
  end

  def destroy
    Contacto.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
