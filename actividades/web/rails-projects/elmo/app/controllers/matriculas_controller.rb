class MatriculasController < ApplicationController
	layout 'with3cols'
	
  # GET /matriculas
  # GET /matriculas.xml
  def index
    @matriculas = Matricula.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @matriculas }
    end
  end

  def list
    @grupo = Grupo.find(params[:id])
    @matriculas = @grupo.matriculas
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @matriculas }
    end
  end

  # GET /matriculas/1
  # GET /matriculas/1.xml
  def show
    @matricula = Matricula.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @matricula }
    end
  end

  # GET /matriculas/new
  # GET /matriculas/new.xml
  def new
    @grupos = Grupo.find_all_by_id(params[:id])
    @alumnos = Alumno.find(:all)
    @tutores = Tutor.find(:all)
    @matricula = Matricula.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @matricula }
    end
  end

  # GET /matriculas/1/edit
  def edit
    @matricula = Matricula.find(params[:id])
    @grupos = []
    @grupos << @matricula.grupo
    @alumnos = Alumno.find(:all, :order => :apellidos)
    @tutores = Tutor.find(:all)
  end

  # POST /matriculas
  # POST /matriculas.xml
  def create
    @matricula = Matricula.new(params[:matricula])
	 
    respond_to do |format|
      if @matricula.save
        flash[:notice] = 'Matricula was successfully created.'
        
        cuadrar_faltas_de(@matricula)
        
        format.html { redirect_to(@matricula) }
        format.xml  { render :xml => @matricula, :status => :created, :location => @matricula }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @matricula.errors, :status => :unprocessable_entity }
      end
    end
  end
  
  def matricular_alumno
    e = params[:entrada]
    @alumno = Alumno.find(params[:id].to_i)
    @matricula = Matricula.new()
    @matricula.alumno = @alumno
    @matricula.grupo_id = e[:grupo_id].to_i
    @matricula.tutor_id = e[:tutor_id].to_i
    @matricula.baja = false
	 
    respond_to do |format|
      if @matricula.save
        flash[:notice] = 'Matricula was successfully created.'
        
        cuadrar_faltas_de(@matricula)
        
        format.html { redirect_to(@matricula) }
        format.xml  { render :xml => @matricula, :status => :created, :location => @matricula }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @matricula.errors, :status => :unprocessable_entity }
      end
    end
  end
  

  # PUT /matriculas/1
  # PUT /matriculas/1.xml
  def update
    @matricula = Matricula.find(params[:id])

    respond_to do |format|
      if @matricula.update_attributes(params[:matricula])
        flash[:notice] = 'Matricula was successfully updated.'
        format.html { redirect_to(@matricula) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @matricula.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /matriculas/1
  # DELETE /matriculas/1.xml
  def destroy
    @matricula = Matricula.find(params[:id])
    @matricula.destroy

    respond_to do |format|
      format.html { redirect_to(matriculas_url) }
      format.xml  { head :ok }
    end
  end
  
private

  def cuadrar_faltas_de(matricula)
    grupo = Grupo.find(matricula.grupo_id)
    if (grupo.matriculas.size>0) then
      if (grupo.matriculas[0].faltas.size>0) then
        grupo.matriculas[0].faltas.each do |f1|
          f2=Falta.new
          f2.fecha = f1.fecha
          f2.valor='-'
          f2.matricula=matricula
          f2.save 
        end
      end
    end
  end
  
end
