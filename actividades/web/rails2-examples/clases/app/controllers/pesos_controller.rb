class PesosController < ApplicationController
  layout 'default'
  
  # GET /pesos
  # GET /pesos.xml
  def index
    @pesos = Peso.find(:all)
    @tab = :inicio
    @nav = :pesos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @pesos }
    end
  end

  # GET /pesos/1
  # GET /pesos/1.xml
  def show
    @peso = Peso.find(params[:id])
    @tab = :inicio
    @nav = @peso
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @peso }
    end
  end

  # GET /pesos/new
  # GET /pesos/new.xml
  def new
    @programaciones = Programacion.find(:all)
    @peso = Peso.new
    @tab = :inicio
    @nav = :pesos
    
    @peso.valor_min=0
    if !params[:id].nil? 
      @programaciones = Programacion.find_all_by_id(params[:id])
      @nav = @programaciones[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @peso }
    end
  end

  # GET /pesos/1/edit
  def edit
    @peso = Peso.find(params[:id])
    @programaciones = Programacion.find_all_by_id(@peso.programacion.id)
    @tab = :inicio
    @nav = @peso
  end

  # POST /pesos
  # POST /pesos.xml
  def create
    @peso = Peso.new(params[:peso])

    respond_to do |format|
      if @peso.save
        flash[:notice] = 'El Peso se creó correctamente.'
        format.html { redirect_to(@peso) }
        format.xml  { render :xml => @peso, :status => :created, :location => @peso }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @peso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /pesos/1
  # PUT /pesos/1.xml
  def update
    @peso = Peso.find(params[:id])

    respond_to do |format|
      if @peso.update_attributes(params[:peso])
        flash[:notice] = 'El Peso se modificó correctamente.'
        format.html { redirect_to(@peso) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @peso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /pesos/1
  # DELETE /pesos/1.xml
  def destroy
    @peso = Peso.find(params[:id])
    @peso.destroy

    respond_to do |format|
      format.html { redirect_to(pesos_url) }
      format.xml  { head :ok }
    end
  end
  
  def list
    @programacion = Programacion.find(params[:id])
    @pesos = @programacion.pesos
    @tab = :inicio
    @nav = @programacion
  end

end
