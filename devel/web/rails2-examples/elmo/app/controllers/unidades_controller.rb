class UnidadesController < ApplicationController
  layout 'with3cols'
  
  # GET /unidades
  # GET /unidades.xml
  def index
    @unidades = Unidad.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @unidades }
    end
  end

  def list
    @asignatura = Asignatura.find(params[:id])
    @unidades = @asignatura.unidades

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @unidades }
    end
  end

  # GET /unidades/1
  # GET /unidades/1.xml
  def show
    @unidad = Unidad.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @unidad }
    end
  end

  # GET /unidades/new
  # GET /unidades/new.xml
  def new
    @asignaturas = Asignatura.find(:all)
    @unidad = Unidad.new
    
    if !params[:id].nil? 
      @asignaturas = Asignatura.find_all_by_id(params[:id])
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @unidad }
    end
  end

  # GET /unidades/1/edit
  def edit
    @unidad = Unidad.find(params[:id])
    @asignaturas = Asignatura.find_all_by_id(@unidad.asignatura.id)
  end

  # POST /unidades
  # POST /unidades.xml
  def create
    @unidad = Unidad.new(params[:unidad])
    p params[:unidad]
    
    respond_to do |format|
      if @unidad.save
        flash[:notice] = 'Unidad was successfully created.'
        format.html { redirect_to(@unidad) }
        format.xml  { render :xml => @unidad, :status => :created, :location => @unidad }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @unidad.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /unidades/1
  # PUT /unidades/1.xml
  def update
    @unidad = Unidad.find(params[:id])

    respond_to do |format|
      if @unidad.update_attributes(params[:unidad])
        flash[:notice] = 'Unidad was successfully updated.'
        format.html { redirect_to(@unidad) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @unidad.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /unidades/1
  # DELETE /unidades/1.xml
  def destroy
    @unidad = Unidad.find(params[:id])
    @unidad.destroy

    respond_to do |format|
      format.html { redirect_to(unidades_url) }
      format.xml  { head :ok }
    end
  end
end
