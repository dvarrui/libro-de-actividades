class ModulosController < ApplicationController
  layout 'default'
  
  # GET /modulos
  # GET /modulos.xml
  def index
    @modulos = Modulo.find(:all)
    @tab = :recursos
    @nav = :modulos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @modulos }
    end
  end

  # GET /modulos/1
  # GET /modulos/1.xml
  def show
    @modulo = Modulo.find(params[:id])
    @tab = :recursos
    @nav = @modulo
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @modulo }
    end
  end

  # GET /modulos/new
  # GET /modulos/new.xml
  def new
    @ciclos = Ciclo.find(:all)
    @modulo = Modulo.new
    @tab = :recursos
    @nav = :modulos
    
    @modulo.activo = true
    if !params[:id].nil? 
      @ciclos = Ciclo.find_all_by_id(params[:id])
      @nav = @ciclos[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @modulo }
    end
  end

  # GET /modulos/1/edit
  def edit
    @ciclos = Ciclo.find(:all)
    @modulo = Modulo.find(params[:id])
    @tab = :recursos
    @nav = @modulo
  end

  # POST /modulos
  # POST /modulos.xml
  def create
    @modulo = Modulo.new(params[:modulo])

    respond_to do |format|
      if @modulo.save
        flash[:notice] = 'El Modulo se creó correctamente.'
        format.html { redirect_to(@modulo) }
        format.xml  { render :xml => @modulo, :status => :created, :location => @modulo }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @modulo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /modulos/1
  # PUT /modulos/1.xml
  def update
    @modulo = Modulo.find(params[:id])

    respond_to do |format|
      if @modulo.update_attributes(params[:modulo])
        flash[:notice] = 'El Modulo se modificó correctamente.'
        format.html { redirect_to(@modulo) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @modulo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /modulos/1
  # DELETE /modulos/1.xml
  def destroy
    @modulo = Modulo.find(params[:id])
    @modulo.destroy

    respond_to do |format|
      format.html { redirect_to(modulos_url) }
      format.xml  { head :ok }
    end
  end
  
  def list
    @ciclo = Ciclo.find(params[:id])
    @modulos = @ciclo.modulos
    @tab = :recursos
    @nav = @ciclo
  end
end
