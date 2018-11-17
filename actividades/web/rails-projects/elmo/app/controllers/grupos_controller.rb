class GruposController < ApplicationController
	layout 'with3cols'
	
  # GET /grupos
  # GET /grupos.xml
  def index
    @grupos = Grupo.find(:all).sort_by {|p| p.asignatura.curso.codigo }

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @grupos }
    end
  end

  def list
    @curso = Curso.find(params[:id])
    @grupos = @curso.grupos.sort_by{|p| p.codigo.downcase}
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @grupos }
    end
  end

  # GET /grupos/1
  # GET /grupos/1.xml
  def show
    @grupo = Grupo.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @grupo }
    end
  end

  # GET /grupos/new
  # GET /grupos/new.xml
  def new
    @grupo = Grupo.new
    
    if !params[:id].nil? 
      @cursos = Curso.find_all_by_id(params[:id])
      @asignaturas = @cursos[0].asignaturas
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @grupo }
    end
  end

  # GET /grupos/1/edit
  def edit
    @grupo = Grupo.find(params[:id])
    @cursos = Curso.find_all_by_id(@grupo.asignatura.curso_id)
    @asignaturas = @cursos[0].asignaturas
  end

  # POST /grupos
  # POST /grupos.xml
  def create
    @grupo = Grupo.new(params[:grupo])
    @grupo.hora_inicio = revisar_los_minutos_de @grupo.hora_inicio
    @grupo.hora_fin = revisar_los_minutos_de @grupo.hora_fin
  
    respond_to do |format|
      if @grupo.save
        flash[:notice] = 'Grupo was successfully created.'
        format.html { redirect_to(@grupo) }
        format.xml  { render :xml => @grupo, :status => :created, :location => @grupo }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @grupo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /grupos/1
  # PUT /grupos/1.xml
  def update
    @grupo = Grupo.find(params[:id])
    
    respond_to do |format|
      if @grupo.update_attributes(params[:grupo])
    		@grupo.hora_inicio = revisar_los_minutos_de @grupo.hora_inicio
    		@grupo.hora_fin = revisar_los_minutos_de @grupo.hora_fin
    		@grupo.save 
        flash[:notice] = 'Grupo was successfully updated.'
        format.html { redirect_to(@grupo) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @grupo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /grupos/1
  # DELETE /grupos/1.xml
  def destroy
    @grupo = Grupo.find(params[:id])
    @grupo.destroy

    respond_to do |format|
      format.html { redirect_to(grupos_url) }
      format.xml  { head :ok }
    end
  end

private

  def revisar_los_minutos_de(hora)
    m = hora.min
    if (m>=30) then
      m=30
    else
      m=0
    end
    
    t = Time.mktime(2000,1,1,hora.hour,m,0)
    
    return t
  end
  
end
