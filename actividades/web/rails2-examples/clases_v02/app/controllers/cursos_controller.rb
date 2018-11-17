class CursosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @curso_pages, @cursos = paginate :cursos, :per_page => 10
  end

  def show
    @curso = Curso.find(params[:id])
  end

  def new
    @curso = Curso.new
  end

  def create
    @curso = Curso.new(params[:curso])
    if @curso.save
      flash[:notice] = 'Curso was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @curso = Curso.find(params[:id])
  end

  def update
    @curso = Curso.find(params[:id])
    if @curso.update_attributes(params[:curso])
      flash[:notice] = 'Curso was successfully updated.'
      redirect_to :action => 'show', :id => @curso
    else
      render :action => 'edit'
    end
  end

  def destroy
    Curso.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
