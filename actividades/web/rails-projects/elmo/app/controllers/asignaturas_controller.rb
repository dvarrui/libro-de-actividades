class AsignaturasController < ApplicationController
	layout 'with3cols'
	
  # GET /asignaturas
  # GET /asignaturas.xml
  def index
    @asignaturas = Asignatura.find(:all, :order=>:codigo)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @asignaturas }
    end
  end

  def list
    @curso = Curso.find(params[:id])
    @asignaturas = @curso.asignaturas.sort_by{|p| p.codigo.downcase}
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @asignaturas }
    end
  end

  # GET /asignaturas/1
  # GET /asignaturas/1.xml
  def show
    @asignatura = Asignatura.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @asignatura }
    end
  end

  # GET /asignaturas/new
  # GET /asignaturas/new.xml
  def new
    @asignatura = Asignatura.new
    @cursos = Curso.find(:all)
    
    if !params[:id].nil? 
      @cursos = Curso.find_all_by_id(params[:id])
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @asignatura }
    end
  end

  # GET /asignaturas/1/edit
  def edit
    @asignatura = Asignatura.find(params[:id])
    @cursos = Curso.find(:all)
  end

  # POST /asignaturas
  # POST /asignaturas.xml
  def create
    @asignatura = Asignatura.new(params[:asignatura])

    respond_to do |format|
      if @asignatura.save
        flash[:notice] = 'Asignatura was successfully created.'
        format.html { redirect_to(@asignatura) }
        format.xml  { render :xml => @asignatura, :status => :created, :location => @asignatura }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @asignatura.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /asignaturas/1
  # PUT /asignaturas/1.xml
  def update
    @asignatura = Asignatura.find(params[:id])

    respond_to do |format|
      if @asignatura.update_attributes(params[:asignatura])
        flash[:notice] = 'Asignatura was successfully updated.'
        format.html { redirect_to(@asignatura) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @asignatura.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /asignaturas/1
  # DELETE /asignaturas/1.xml
  def destroy
    @asignatura = Asignatura.find(params[:id])
    @asignatura.destroy

    respond_to do |format|
      format.html { redirect_to(asignaturas_url) }
      format.xml  { head :ok }
    end
  end
end
