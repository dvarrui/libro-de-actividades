class PasesController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @pase_pages, @pases = paginate :pases, :per_page => 10
  end

  def show
    @pase = Pase.find(params[:id])
  end

  def new
    @pase = Pase.new
  end

  def create
    @pase = Pase.new(params[:pase])
    if @pase.save
      flash[:notice] = 'Pase was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @pase = Pase.find(params[:id])
  end

  def update
    @pase = Pase.find(params[:id])
    if @pase.update_attributes(params[:pase])
      flash[:notice] = 'Pase was successfully updated.'
      redirect_to :action => 'show', :id => @pase
    else
      render :action => 'edit'
    end
  end

  def destroy
    Pase.find(params[:id]).destroy
    redirect_to :action => 'list'
  end
end
