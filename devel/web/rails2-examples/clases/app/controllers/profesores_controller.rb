class ProfesoresController < ApplicationController
  layout 'default'
  
  # GET /profesores
  # GET /profesores.xml
  def index
    @profesores = Profesor.find(:all)
    @tab = :recursos
    @nav = :profesores

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @profesores }
    end
  end

  # GET /profesores/1
  # GET /profesores/1.xml
  def show
    @profesor = Profesor.find(params[:id])
    @tab = :recursos
    @nav = @profesor

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @profesor }
    end
  end

  # GET /profesores/new
  # GET /profesores/new.xml
  def new
    @departamentos = Departamento.find(:all)
    @profesor = Profesor.new
    @tab = :recursos
    @nav = :profesores

    @profesor.activo = true
    if !params[:id].nil? 
      @departamentos = Departamento.find_all_by_id(params[:id])
      @nav = @departamentos[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @profesor }
    end
  end

  # GET /profesores/1/edit
  def edit
    @departamentos = Departamento.find(:all)
    @profesor = Profesor.find(params[:id])
    @tab = :recursos
    @nav = @profesor
  end

  # POST /profesores
  # POST /profesores.xml
  def create
    @departamentos = Departamento.find(:all)
    @profesor = Profesor.new(params[:profesor])

	@profesor.activo=true
	
    respond_to do |format|
      if @profesor.save
        flash[:notice] = 'El profesor ha sido creado correctamente.'
        format.html { redirect_to(@profesor) }
        format.xml  { render :xml => @profesor, :status => :created, :location => @profesor }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @profesor.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /profesores/1
  # PUT /profesores/1.xml
  def update
    @profesor = Profesor.find(params[:id])

    respond_to do |format|
      if @profesor.update_attributes(params[:profesor])
        flash[:notice] = 'El Profesor ha sido modificado correctamente.'
        format.html { redirect_to(@profesor) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @profesor.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /profesores/1
  # DELETE /profesores/1.xml
  def destroy
    @profesor = Profesor.find(params[:id])
    @profesor.destroy

    respond_to do |format|
      format.html { redirect_to(profesores_url) }
      format.xml  { head :ok }
    end
  end
  
  def list
    @departamento = Departamento.find(params[:id])
    @profesores = @departamento.profesores
    @tab = :recursos
    @nav = @departamento
  end

end
