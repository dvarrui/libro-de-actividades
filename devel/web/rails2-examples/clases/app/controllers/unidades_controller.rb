class UnidadesController < ApplicationController
  layout 'default'
  
  # GET /unidades
  # GET /unidades.xml
  def index
    @unidades = Unidad.find(:all)
    @tab = :inicio
    @nav = :unidades

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @unidades }
    end
  end

  # GET /unidades/1
  # GET /unidades/1.xml
  def show
    @unidad = Unidad.find(params[:id])
    @tab = :inicio
    @nav = @unidad

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @unidad }
    end
  end

  # GET /unidades/new
  # GET /unidades/new.xml
  def new
    @programaciones = Programacion.find(:all)
    @unidad = Unidad.new
    @unidad.peso=1
    @tab = :inicio
    @nav = :unidades

   if !params[:id].nil? 
      @programaciones = Programacion.find_all_by_id(params[:id])
      @nav = @programaciones[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @unidad }
    end
  end

  # GET /unidades/1/edit
  def edit
    @programaciones = Programacion.find(:all)
    @unidad = Unidad.find(params[:id])
    @tab = :inicio
    @nav = @unidad
  end

  # POST /unidades
  # POST /unidades.xml
  def create
    @unidad = Unidad.new(params[:unidad])

    respond_to do |format|
      if @unidad.save
        flash[:notice] = 'La Unidad se creó correctamente.'
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
        flash[:notice] = 'La Unidad se modificó correctamente.'
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
      format.html { redirect_to(unidads_url) }
      format.xml  { head :ok }
    end
  end

  def list
    @programacion = Programacion.find(params[:id])
    @unidades = @programacion.unidades
    @tab = :inicio
    @nav = @programacion
  end
end
