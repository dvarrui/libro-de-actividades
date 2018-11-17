class DiapositivasController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @diapositiva_pages, @diapositivas = paginate :diapositivas, :per_page => 10
  end

  def show
    @diapositiva = Diapositiva.find(params[:id])
  end

  def new
    @diapositiva = Diapositiva.new
  end

  def create
    @diapositiva = Diapositiva.new(params[:diapositiva])
    if @diapositiva.save
      flash[:notice] = 'Diapositiva was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @diapositiva = Diapositiva.find(params[:id])
  end

  def update
    @diapositiva = Diapositiva.find(params[:id])
    if @diapositiva.update_attributes(params[:diapositiva])
      flash[:notice] = 'Diapositiva was successfully updated.'
      redirect_to :action => 'show', :id => @diapositiva
    else
      render :action => 'edit'
    end
  end

  def destroy
    Diapositiva.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
