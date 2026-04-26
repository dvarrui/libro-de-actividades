class AlumnosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @alumno_pages, @alumnos = paginate :alumnos, :per_page => 10
  end

  def show
    @alumno = Alumno.find(params[:id])
  end

  def new
    @alumno = Alumno.new
  end

  def create
    @alumno = Alumno.new(params[:alumno])
    if @alumno.save
      flash[:notice] = 'Alumno was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @alumno = Alumno.find(params[:id])
  end

  def update
    @alumno = Alumno.find(params[:id])
    if @alumno.update_attributes(params[:alumno])
      flash[:notice] = 'Alumno was successfully updated.'
      redirect_to :action => 'show', :id => @alumno
    else
      render :action => 'edit'
    end
  end

  def destroy
    Alumno.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
