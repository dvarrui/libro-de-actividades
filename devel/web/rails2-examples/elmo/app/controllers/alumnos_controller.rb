class AlumnosController < ApplicationController
  layout 'default'
	
  # GET /alumnos
  # GET /alumnos.xml
  def index
    redirect_to url_for(:action=>:listar)
  end
  
  def listar
    begin
      n=params[:filtro][:nombre]
      a=params[:filtro][:apellidos]
      e=params[:filtro][:numexp].to_i
    rescue => exc
      n=''; a=''; e=0
    end
    
    conditions = ["(nombre LIKE ? and apellidos LIKE ?) or numexp=?", "%#{n}%", "%#{a}%", e]
    @alumnos = Alumno.find(:all, :order=>'nombre', :conditions=>conditions)
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @alumnos }
    end

    params[:filtro]={}
    params[:filtro][:nombre]=n
    params[:filtro][:apellidos]=a
    params[:filtro][:numexp]=e.to_s
    
    #@total = Alumno.count(:conditions => conditions)
    #@alumnos_pages, @alumnos = paginate :alumnos, :order => 'nombre', 
    #         :conditions => conditions, 
    #         :per_page => alumnos_per_page
  end
  
  # GET /alumnos/1
  # GET /alumnos/1.xml
  def show
    @alumno = Alumno.find(params[:id])
    p = Parametro.find_by_variable("curso_id") 
    curso_id = p.valor.to_i 
    i = Curso.find(curso_id)
    @grupos = i.grupos.sort_by{|p| p.codigo.downcase} 
    @tutores = Tutor.find_all_by_baja(false).sort_by{|p| p.nombre.downcase}
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @alumno }
    end
  end

  # GET /alumnos/new
  # GET /alumnos/new.xml
  def new
    @alumno = Alumno.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @alumno }
    end
  end

  # GET /alumnos/1/edit
  def edit
    @alumno = Alumno.find(params[:id])
  end

  # POST /alumnos
  # POST /alumnos.xml
  def create
    @alumno = Alumno.new(params[:alumno])

    respond_to do |format|
      if @alumno.save
        flash[:notice] = 'Alumno was successfully created.'
        format.html { redirect_to(@alumno) }
        format.xml  { render :xml => @alumno, :status => :created, :location => @alumno }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @alumno.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /alumnos/1
  # PUT /alumnos/1.xml
  def update
    @alumno = Alumno.find(params[:id])

    respond_to do |format|
      if @alumno.update_attributes(params[:alumno])
        flash[:notice] = 'Alumno was successfully updated.'
        format.html { redirect_to(@alumno) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @alumno.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /alumnos/1
  # DELETE /alumnos/1.xml
  def destroy
    @alumno = Alumno.find(params[:id])
    
    if (@alumno.matriculas.size==0)
      @alumno.destroy

      respond_to do |format|
        format.html { redirect_to(alumnos_url) }
        format.xml  { head :ok }
      end
    else
      redirect_to(:action=>:error, :id=>@alumno)
    end   
  end
  
  def error
   @alumno = Alumno.find(params[:id])
  end
  
end
