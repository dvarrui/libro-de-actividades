class ActividadesController < ApplicationController
  # GET /actividades
  # GET /actividades.xml
  def index
    @actividades = Actividad.find(:all)
    @nav_from = :actividades
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @actividads }
    end
  end

  # GET /actividades/1
  # GET /actividades/1.xml
  def show
    @actividad = Actividad.find(params[:id])
    @nav_from = @actividad
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @actividad }
    end
  end

  # GET /actividades/new
  # GET /actividades/new.xml
  def new
    @unidades = Unidad.find(:all)
    @pesos = Peso.find(:all)
    @actividad = Actividad.new
    @nav_from = :actividades
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @actividad }
    end
  end

  # GET /actividades/1/edit
  def edit
    @unidades = Unidad.find(:all)
    @pesos = Peso.find(:all)
    @actividad = Actividad.find(params[:id])
    @nav_from = @actividad
  end

  # POST /actividades
  # POST /actividades.xml
  def create
    @actividad = Actividad.new(params[:actividad])

    respond_to do |format|
      if @actividad.save
        flash[:notice] = 'La Actividad se creó correctamente.'
        format.html { redirect_to(@actividad) }
        format.xml  { render :xml => @actividad, :status => :created, :location => @actividad }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @actividad.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /actividades/1
  # PUT /actividades/1.xml
  def update
    @unidades = Unidad.find(:all)
    @pesos = Peso.find(:all)
    @actividad = Actividad.find(params[:id])

    respond_to do |format|
      if @actividad.update_attributes(params[:actividad])
        flash[:notice] = 'La Actividad se modificó correctamente.'
        format.html { redirect_to(@actividad) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @actividad.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /actividades/1
  # DELETE /actividades/1.xml
  def destroy
    @actividad = Actividad.find(params[:id])
    @actividad.destroy

    respond_to do |format|
      format.html { redirect_to(actividads_url) }
      format.xml  { head :ok }
    end
  end
end
