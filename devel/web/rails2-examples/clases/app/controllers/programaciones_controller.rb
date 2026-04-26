class ProgramacionesController < ApplicationController
  layout 'default'
  
  # GET /programaciones
  # GET /programaciones.xml
  def index
    @programaciones = Programacion.find(:all)
    @tab = :inicio
    @nav = :programaciones
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @programaciones }
    end
  end

  # GET /programaciones/1
  # GET /programaciones/1.xml
  def show
    @programacion = Programacion.find(params[:id])
    @tab = :inicio
    @nav = @programacion
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @programacion }
    end
  end

  # GET /programaciones/new
  # GET /programaciones/new.xml
  def old_new
    @cursos = Curso.find(:all)
    @modulos = Modulo.find(:all)
    @profesores = Profesor.find(:all)
    @aulas = Aula.find(:all)
    @programa = Programa.new
    @tab = :inicio
    @nav = :programaciones
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @programa }
    end
  end

  # GET /programas/1/edit
  def edit
    @cursos = Curso.find(:all)
    @modulos = Modulo.find(:all)
    @profesores = Profesor.find(:all)
    @aulas = Aula.find(:all)
    @programacion = Programacion.find(params[:id])
    
    @tab = :inicio
    @nav = @programacion
  end

  # POST /programaciones
  # POST /programaciones.xml
  def create
    @programacion = Programacion.new(params[:programacion])

    respond_to do |format|
      if @programacion.save
        flash[:notice] = 'La Programación se creó correctamente.'
        format.html { redirect_to(@programacion) }
        format.xml  { render :xml => @programacion, :status => :created, :location => @programacion }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @programacion.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /programaciones/1
  # PUT /programaciones/1.xml
  def update
    @programacion = Programacion.find(params[:id])

    respond_to do |format|
      if @programacion.update_attributes(params[:programacion])
        flash[:notice] = 'La Programación se modificó correctamente.'
        format.html { redirect_to(@programacion) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @programacion.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /programaciones/1
  # DELETE /programaciones/1.xml
  def destroy
    @programacion = Programacion.find(params[:id])
    @programacion.destroy

    respond_to do |format|
      format.html { redirect_to(programaciones_url) }
      format.xml  { head :ok }
    end
  end
  
  def new
    @modulo = Modulo.new
    @curso = Curso.find(params[:id])
    @ciclos = @curso.centro.ciclos
    @tab = :inicio
    @nav = @curso
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @programacion }
    end
  end
  
  def show_form
    ciclo = Ciclo.find(params[:modulo][:ciclo_id])
    centro = ciclo.centro
    
    @cursos = centro.cursos
    @modulos = ciclo.modulos
    @profesores = centro.profesores
    @aulas = centro.aulas
    @programacion = Programacion.new
    @programacion.modo_evaluar='perfil1'

    render :partial => 'form'
  end

  def list
    @curso = Curso.find(params[:id])
    @programaciones = @curso.programaciones
    @tab = :inicio
    @nav = @curso
  end

  def list_by_profesor
    @profesor = Profesor.find(params[:id])
    @programaciones = @profesor.programaciones
    @tab = :inicio
    @nav = @profesor
  end
end
