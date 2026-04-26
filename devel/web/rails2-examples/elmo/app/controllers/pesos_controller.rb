class PesosController < ApplicationController
	layout 'with3cols'
	
  # GET /pesos
  # GET /pesos.xml
  def index
    @pesos = Peso.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @pesos }
    end
  end

  def list
    @asignatura = Asignatura.find(params[:id])
    @pesos = @asignatura.pesos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @pesos }
    end
  end
  # GET /pesos/1
  # GET /pesos/1.xml
  def show
    @peso = Peso.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @peso }
    end
  end

  # GET /pesos/new
  # GET /pesos/new.xml
  def new
    @asignaturas = Asignatura.find(:all)
    @peso = Peso.new
    
    if !params[:id].nil? 
      @asignaturas = Asignatura.find_all_by_id(params[:id])
    end
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @peso }
    end
  end

  # GET /pesos/1/edit
  def edit
    @peso = Peso.find(params[:id])
     @asignaturas = Asignatura.find_all_by_id(@peso.asignatura.id)
  end

  # POST /pesos
  # POST /pesos.xml
  def create
    @peso = Peso.new(params[:peso])
    p params[:peso]

    respond_to do |format|
      if @peso.save
        flash[:notice] = 'Peso was successfully created.'
        format.html { redirect_to(@peso) }
        format.xml  { render :xml => @peso, :status => :created, :location => @peso }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @peso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /pesos/1
  # PUT /pesos/1.xml
  def update
    @peso = Peso.find(params[:id])

    respond_to do |format|
      if @peso.update_attributes(params[:peso])
        flash[:notice] = 'Peso was successfully updated.'
        format.html { redirect_to(@peso) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @peso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /pesos/1
  # DELETE /pesos/1.xml
  def destroy
    @peso = Peso.find(params[:id])
    @peso.destroy

    respond_to do |format|
      format.html { redirect_to(pesos_url) }
      format.xml  { head :ok }
    end
  end
end
