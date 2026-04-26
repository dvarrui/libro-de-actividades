class FotosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @foto_pages, @fotos = paginate :fotos, :per_page => 10 
  end

  def show
    @foto = Foto.find(params[:id])
  end

  def new
    @foto = Foto.new
  end

  def create
    @foto = Foto.new(params[:foto])
    if @foto.save
      flash[:notice] = 'La Foto se creó correctamente.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @foto = Foto.find(params[:id])
  end

  def update
    @foto = Foto.find(params[:id])
    if @foto.update_attributes(params[:foto])
      flash[:notice] = 'La Foto se modificó correctamente.'
      redirect_to :action => 'show', :id => @foto
    else
      render :action => 'edit'
    end
  end

  def destroy
    Foto.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
