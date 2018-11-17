class CiclosController < ApplicationController
  layout 'default'
  
  # GET /ciclos
  # GET /ciclos.xml
  def index
    @ciclos = Ciclo.find(:all)
    @tab = :recursos
    @nav = :ciclos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @ciclos }
    end
  end

  # GET /ciclos/1
  # GET /ciclos/1.xml
  def show
    @ciclo = Ciclo.find(params[:id])
    @tab = :recursos
    @nav = @ciclo
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @ciclo }
    end
  end

  # GET /ciclos/new
  # GET /ciclos/new.xml
  def new
    @centros = Centro.find(:all)
    @ciclo = Ciclo.new
    @tab = :recursos
    @nav = :ciclos
    
    @ciclo.activo=true
    if !params[:id].nil? 
      @centros = Centro.find_all_by_id(params[:id])
      @nav = @centros[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @ciclo }
    end
  end

  # GET /ciclos/1/edit
  def edit
    @centros = Centro.find(:all)
    @ciclo = Ciclo.find(params[:id])
    @tab = :recursos
    @nav = @ciclo
  end

  # POST /ciclos
  # POST /ciclos.xml
  def create
    @ciclo = Ciclo.new(params[:ciclo])
    
    respond_to do |format|
      if @ciclo.save
        flash[:notice] = 'El Ciclo se creó correctamente.'
        format.html { redirect_to(@ciclo) }
        format.xml  { render :xml => @ciclo, :status => :created, :location => @ciclo }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @ciclo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /ciclos/1
  # PUT /ciclos/1.xml
  def update
    @ciclo = Ciclo.find(params[:id])

    respond_to do |format|
      if @ciclo.update_attributes(params[:ciclo])
        flash[:notice] = 'El Ciclo se modificó correctamente.'
        format.html { redirect_to(@ciclo) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @ciclo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /ciclos/1
  # DELETE /ciclos/1.xml
  def destroy
    @ciclo = Ciclo.find(params[:id])
    @ciclo.destroy

    respond_to do |format|
      format.html { redirect_to(ciclos_url) }
      format.xml  { head :ok }
    end
  end
  
  def list
    @centro = Centro.find(params[:id])
    @ciclos = @centro.ciclos
    @tab = :recursos
    @nav = @centro
  end

end
