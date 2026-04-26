class TutoresController < ApplicationController
	layout 'default'
	
  # GET /tutores
  # GET /tutores.xml
  def index
    @tutores = Tutor.find(:all, :order=>'nombre')

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @tutores }
    end
  end

  # GET /tutores/1
  # GET /tutores/1.xml
  def show
    @tutor = Tutor.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @tutor }
    end
  end

  # GET /tutores/new
  # GET /tutores/new.xml
  def new
    @tutor = Tutor.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @tutor }
    end
  end

  # GET /tutores/1/edit
  def edit
    @tutor = Tutor.find(params[:id])
  end

  # POST /tutores
  # POST /tutores.xml
  def create
    @tutor = Tutor.new(params[:tutor])
  
    respond_to do |format|
      if @tutor.save
        flash[:notice] = 'Tutor was successfully created.'
        format.html { redirect_to(@tutor) }
        format.xml  { render :xml => @tutor, :status => :created, :location => @tutor }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @tutor.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /tutores/1
  # PUT /tutores/1.xml
  def update
    @tutor = Tutor.find(params[:id])

    respond_to do |format|
      if @tutor.update_attributes(params[:tutor])
        flash[:notice] = 'Tutor was successfully updated.'
        format.html { redirect_to(@tutor) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @tutor.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /tutores/1
  # DELETE /tutores/1.xml
  def destroy
    @tutor = Tutor.find(params[:id])
    
    if (@tutor.matriculas.size==0)
      @tutor.destroy

      respond_to do |format|
        format.html { redirect_to(tutores_url) }
        format.xml  { head :ok }
      end
    else
      flash[:notice] = 'No se puede borrar el tutor. Prueba a darlo de baja.'
      redirect_to(:action=>:error, :id=>@tutor)
    end    
  end
  
  def error
    @tutor = Tutor.find(params[:id])
  end
  
end
