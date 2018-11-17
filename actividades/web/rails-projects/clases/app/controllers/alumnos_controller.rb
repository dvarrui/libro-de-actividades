class AlumnosController < ApplicationController
  # GET /alumnos
  # GET /alumnos.xml
  def index
    @alumnos = Alumno.find(:all)
    @nav_from = :alumnos
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @alumnos }
    end
  end

  # GET /alumnos/1
  # GET /alumnos/1.xml
  def show
    @alumno = Alumno.find(params[:id])
    @nav_from = @alumno
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @alumno }
    end
  end

  # GET /alumnos/new
  # GET /alumnos/new.xml
  def new
    @municipios = Municipio.find(:all)
    @alumno = Alumno.new
    @nav_from = :alumnos
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @alumno }
    end
  end

  # GET /alumnos/1/edit
  def edit
    @municipios = Municipio.find(:all)
    @alumno = Alumno.find(params[:id])
    @nav_from = @alumno
  end

  # POST /alumnos
  # POST /alumnos.xml
  def create
    @municipios = Municipio.find(:all)
    @alumno = Alumno.new(params[:alumno])

    respond_to do |format|
      if @alumno.save
        flash[:notice] = 'El Alumno se creó correctamente.'
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
    @municipios = Municipio.find(:all)
    @alumno = Alumno.find(params[:id])

    respond_to do |format|
      if @alumno.update_attributes(params[:alumno])
        flash[:notice] = 'El Alumno se modificó correctamente.'
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
    @alumno.destroy

    respond_to do |format|
      format.html { redirect_to(alumnos_url) }
      format.xml  { head :ok }
    end
  end
end
