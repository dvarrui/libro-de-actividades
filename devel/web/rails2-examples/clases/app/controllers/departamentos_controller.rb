class DepartamentosController < ApplicationController
  layout 'default'
  
  # GET /departamentos
  # GET /departamentos.xml
  def index
    @departamentos = Departamento.find(:all)
    @tab = :recursos
    @nav = :departamentos
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @departamentos }
    end
  end

  # GET /departamentos/1
  # GET /departamentos/1.xml
  def show
    @departamento = Departamento.find(params[:id])
    @tab = :recursos
    @nav = @departamento
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @departamento }
    end
  end

  # GET /departamentos/new
  # GET /departamentos/new.xml
  def new
    @centros = Centro.find(:all)
    @departamento = Departamento.new
    @tab = :recursos
    @nav = :departamentos
    
    @departamento.activo=true
    if !params[:id].nil? 
      @centros = Centro.find_all_by_id(params[:id])
      @nav = @centros[0] 
    end

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @departamento }
    end
  end

  # GET /departamentos/1/edit
  def edit
    @centros = Centro.find(:all)
    @departamento = Departamento.find(params[:id])
    @tab = :recursos
    @nav = @departamento
  end

  # POST /departamentos
  # POST /departamentos.xml
  def create
    @departamento = Departamento.new(params[:departamento])

    respond_to do |format|
      if @departamento.save
        flash[:notice] = 'El Departamento se creó correctamente.'
        format.html { redirect_to(@departamento) }
        format.xml  { render :xml => @departamento, :status => :created, :location => @departamento }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @departamento.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /departamentos/1
  # PUT /departamentos/1.xml
  def update
    @departamento = Departamento.find(params[:id])

    respond_to do |format|
      if @departamento.update_attributes(params[:departamento])
        flash[:notice] = 'El Departamento se modificó correctamente.'
        format.html { redirect_to(@departamento) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @departamento.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /departamentos/1
  # DELETE /departamentos/1.xml
  def destroy
    @departamento = Departamento.find(params[:id])
    @departamento.destroy

    respond_to do |format|
      format.html { redirect_to(departamentos_url) }
      format.xml  { head :ok }
    end
  end
  
  def list
    @centro = Centro.find(params[:id])
    @departamentos = @centro.departamentos
    @tab = :recursos
    @nav = @centro
  end

end
