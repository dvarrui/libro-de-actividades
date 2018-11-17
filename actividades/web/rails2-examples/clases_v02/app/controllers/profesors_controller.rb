class ProfesorsController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @profesor_pages, @profesors = paginate :profesors, :per_page => 10
  end

  def show
    @profesor = Profesor.find(params[:id])
  end

  def new
    @profesor = Profesor.new
  end

  def create
    @profesor = Profesor.new(params[:profesor])
    if @profesor.save
      flash[:notice] = 'Profesor was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @profesor = Profesor.find(params[:id])
  end

  def update
    @profesor = Profesor.find(params[:id])
    if @profesor.update_attributes(params[:profesor])
      flash[:notice] = 'Profesor was successfully updated.'
      redirect_to :action => 'show', :id => @profesor
    else
      render :action => 'edit'
    end
  end

  def destroy
    Profesor.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
