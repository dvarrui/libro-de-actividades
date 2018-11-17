class EmpresasController < ApplicationController
  layout 'default'
  
  # GET /empresas
  # GET /empresas.xml
  def index
    @empresas = Empresa.find(:all)
    @tab = :recursos
    @nav = :empresas
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @empresas }
    end
  end

  # GET /empresas/1
  # GET /empresas/1.xml
  def show
    @empresa = Empresa.find(params[:id])
    @tab = :recursos
    @nav = @empresa

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @empresa }
    end
  end

  # GET /empresas/new
  # GET /empresas/new.xml
  def new
    @centros = Centro.find(:all)
    @municipios = Municipio.find(:all)
    @empresa = Empresa.new
    @tab = :recursos
    @nav = :empresas

    @empresa.activo=true
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @empresa }
    end
  end

  # GET /empresas/1/edit
  def edit
    @centros = Centro.find(:all)
    @municipios = Municipio.find(:all)
    @empresa = Empresa.find(params[:id])
    @tab = :recursos
    @nav = @empresa
  end

  # POST /empresas
  # POST /empresas.xml
  def create
    @empresa = Empresa.new(params[:empresa])

    respond_to do |format|
      if @empresa.save
        flash[:notice] = 'La Empresa se creó correctamente.'
        format.html { redirect_to(@empresa) }
        format.xml  { render :xml => @empresa, :status => :created, :location => @empresa }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @empresa.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /empresas/1
  # PUT /empresas/1.xml
  def update
    @empresa = Empresa.find(params[:id])

    respond_to do |format|
      if @empresa.update_attributes(params[:empresa])
        flash[:notice] = 'La Empresa se modificó correctamente.'
        format.html { redirect_to(@empresa) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @empresa.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /empresas/1
  # DELETE /empresas/1.xml
  def destroy
    @empresa = Empresa.find(params[:id])
    @empresa.destroy

    respond_to do |format|
      format.html { redirect_to(empresas_url) }
      format.xml  { head :ok }
    end
  end
end
