class TareasController < ApplicationController
	layout 'with3cols'
	
  # GET /tareas
  # GET /tareas.xml
  def index
    @tareas = Tarea.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @tareas }
    end
  end

  def list
    @unidad = Unidad.find(params[:id])
    @tareas = @unidad.tareas

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @tareas }
    end
  end

  # GET /tareas/1
  # GET /tareas/1.xml
  def show
    @tarea = Tarea.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @tarea }
    end
  end

  # GET /tareas/new
  # GET /tareas/new.xml
  def new
    @unidades = Unidad.find_all_by_id(params[:id])
    @pesos = @unidades[0].asignatura.pesos
    @tarea = Tarea.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @tarea }
    end
  end

  # GET /tareas/1/edit
  def edit
    @tarea = Tarea.find(params[:id])
    @unidades = []
    @unidades << @tarea.unidad
    @pesos = @tarea.unidad.asignatura.pesos
  end

  # POST /tareas
  # POST /tareas.xml
  def create
    @tarea = Tarea.new(params[:tarea])

    respond_to do |format|
      if @tarea.save
        flash[:notice] = 'Tarea was successfully created.'
        format.html { redirect_to(@tarea) }
        format.xml  { render :xml => @tarea, :status => :created, :location => @tarea }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @tarea.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /tareas/1
  # PUT /tareas/1.xml
  def update
    @tarea = Tarea.find(params[:id])

    respond_to do |format|
      if @tarea.update_attributes(params[:tarea])
        flash[:notice] = 'Tarea was successfully updated.'
        format.html { redirect_to(@tarea) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @tarea.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /tareas/1
  # DELETE /tareas/1.xml
  def destroy
    @tarea = Tarea.find(params[:id])
    @tarea.destroy

    respond_to do |format|
      format.html { redirect_to(tareas_url) }
      format.xml  { head :ok }
    end
  end
end
