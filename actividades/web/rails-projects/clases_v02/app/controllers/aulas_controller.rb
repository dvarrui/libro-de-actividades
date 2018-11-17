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
  end

  def show
    @aula = Aula.find(params[:id])
  end

  def new
    @aula = Aula.new
  end

  def create
    @aula = Aula.new(params[:aula])
    if @aula.save
      flash[:notice] = 'Aula was successfully created.'
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
    if @aula.update_attributes(params[:aula])
      flash[:notice] = 'Aula was successfully updated.'
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
