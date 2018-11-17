class AulasController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @aula_pages, @aulas = paginate :aulas, :per_page => 10
    @aulas = Aula.find(:all, :order => 'nombre')
  end

  def show
    @aula = Aula.find(params[:id])
  end

  def new
    @aula = Aula.new
    @aula.fecha_um=Time.now
  end

  def create
    @aula = Aula.new(params[:aula])
    #@aula.fecha_um=Time.now
    
    if @aula.save
      flash[:notice] = 'El Aula se creó correctamente.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @aula = Aula.find(params[:id])
  end

  def update
    @aula = Aula.find(params[:id])
    @aula.fecha_um=Time.now
    
    if @aula.update_attributes(params[:aula])
      flash[:notice] = 'Aula se modificó correctamente.'
      redirect_to :action => 'show', :id => @aula
    else
      render :action => 'edit'
    end
  end

  def destroy
    Aula.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
