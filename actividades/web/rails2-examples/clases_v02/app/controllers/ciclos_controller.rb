class CiclosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @ciclo_pages, @ciclos = paginate :ciclos, :per_page => 10
  end

  def show
    @ciclo = Ciclo.find(params[:id])
  end

  def new
    @ciclo = Ciclo.new
    @cursos = Curso.find_all
  end

  def create
    @ciclo = Ciclo.new(params[:ciclo])
    if @ciclo.save
      flash[:notice] = 'Ciclo was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @ciclo = Ciclo.find(params[:id])
    @cursos = Curso.find_all
  end

  def update
    @ciclo = Ciclo.find(params[:id])
    if @ciclo.update_attributes(params[:ciclo])
      flash[:notice] = 'Ciclo was successfully updated.'
      redirect_to :action => 'show', :id => @ciclo
    else
      render :action => 'edit'
    end
  end

  def destroy
    Ciclo.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
