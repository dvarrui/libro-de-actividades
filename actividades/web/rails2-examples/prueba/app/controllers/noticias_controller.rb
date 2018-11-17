class NoticiasController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @noticia_pages, @noticias = paginate :noticias, :per_page => 10
  end

  def show
    @noticia = Noticia.find(params[:id])
  end

  def new
    @noticia = Noticia.new
  end

  def create
    @noticia = Noticia.new(params[:noticia])
    if @noticia.save
      flash[:notice] = 'Noticia was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @noticia = Noticia.find(params[:id])
  end

  def update
    @noticia = Noticia.find(params[:id])
    if @noticia.update_attributes(params[:noticia])
      flash[:notice] = 'Noticia was successfully updated.'
      redirect_to :action => 'show', :id => @noticia
    else
      render :action => 'edit'
    end
  end

  def destroy
    Noticia.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
