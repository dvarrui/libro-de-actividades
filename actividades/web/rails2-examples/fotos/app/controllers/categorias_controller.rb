class CategoriasController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @categoria_pages, @categorias = paginate :categorias, :per_page => 10
  end

  def show
    @categoria = Categoria.find(params[:id])
  end

  def new
    @categoria = Categoria.new
  end

  def create
    @categoria = Categoria.new(params[:categoria])
    if @categoria.save
      flash[:notice] = 'Categoria was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @categoria = Categoria.find(params[:id])
  end

  def update
    @categoria = Categoria.find(params[:id])
    if @categoria.update_attributes(params[:categoria])
      flash[:notice] = 'Categoria was successfully updated.'
      redirect_to :action => 'show', :id => @categoria
    else
      render :action => 'edit'
    end
  end

  def destroy
    Categoria.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
