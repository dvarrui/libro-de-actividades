class CentrosController < ApplicationController
  layout 'default'
  
  # GET /centros
  # GET /centros.xml
  def index
    @centros = Centro.find(:all)
    @nav = :centros
    @tab = :recursos
    
    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @centros }
    end
  end

  # GET /centros/1
  # GET /centros/1.xml
  def show
    @centro = Centro.find(params[:id])
    @nav = @centro
    @tab = :recursos

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @centro }
    end
  end

  # GET /centros/new
  # GET /centros/new.xml
  def new
    @municipios = Municipio.find(:all)
    @centro = Centro.new
    @nav = :centros
    @tab = :recursos

    @centro.horario='h1=08:00, h2=09:00, h3=10:00, d3=11:00, h4=11:30, h5=12:30, h6=13:30'
    @centro.activo=true

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @centro }
    end
  end

  # GET /centros/1/edit
  def edit
    @municipios = Municipio.find(:all)
    @centro = Centro.find(params[:id])
    @nav = @centro
    @tab = :recursos
  end

  # POST /centros
  # POST /centros.xml
  def create
    @centro = Centro.new(params[:centro])

    respond_to do |format|
      if @centro.save
        flash[:notice] = 'El Centro se creó correctamente.'
        format.html { redirect_to(@centro) }
        format.xml  { render :xml => @centro, :status => :created, :location => @centro }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @centro.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /centros/1
  # PUT /centros/1.xml
  def update
    @centro = Centro.find(params[:id])

    respond_to do |format|
      if @centro.update_attributes(params[:centro])
        flash[:notice] = 'El Centro se modificó correctamente.'
        format.html { redirect_to(@centro) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @centro.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /centros/1
  # DELETE /centros/1.xml
  def destroy
    @centro = Centro.find(params[:id])
    @centro.destroy

    respond_to do |format|
      format.html { redirect_to(centros_url) }
      format.xml  { head :ok }
    end
  end
end
