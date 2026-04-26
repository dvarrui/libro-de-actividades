class FestivosController < ApplicationController
  layout 'default'
  
  # GET /festivos
  # GET /festivos.xml
  def index
    @festivos = Festivo.find(:all)
    @tab = :inicio
    @nav = :festivos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @festivos }
    end
  end

  # GET /festivos/1
  # GET /festivos/1.xml
  def show
    @festivo = Festivo.find(params[:id])
    @tab = :inicio
    @nav = @festivo

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @festivo }
    end
  end

  # GET /festivos/new
  # GET /festivos/new.xml
  def new
    @cursos = Curso.find(:all)
    @festivo = Festivo.new
    @tab = :inicio
    @nav = :festivos

    a=Time.now.year
    b=Time.now.month
    c=Time.now.day
    @fecha_inicio = Date.new(y=a,m=b,d=c)
    @fecha_fin = Date.new(y=a,m=b,d=(c+1))
    if !params[:id].nil? 
      @cursos = Curso.find_all_by_id(params[:id])
      @nav = @cursos[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @festivo }
    end
  end

  # GET /festivos/1/edit
  def edit
    @cursos = Curso.find(:all)
    @festivo = Festivo.find(params[:id])
    @tab = :inicio
    @nav = @festivo
  end

  # POST /festivos
  # POST /festivos.xml
  def create
    @cursos = Curso.find(:all)
    @festivo = Festivo.new(params[:festivo])

    respond_to do |format|
      if @festivo.save
        flash[:notice] = 'El Festivo se creó correctamente.'
        format.html { redirect_to(@festivo) }
        format.xml  { render :xml => @festivo, :status => :created, :location => @festivo }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @festivo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /festivos/1
  # PUT /festivos/1.xml
  def update
    @cursos = Curso.find(:all)
    @festivo = Festivo.find(params[:id])

    respond_to do |format|
      if @festivo.update_attributes(params[:festivo])
        flash[:notice] = 'El Festivo se grabó correctamente.'
        format.html { redirect_to(@festivo) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @festivo.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /festivos/1
  # DELETE /festivos/1.xml
  def destroy
    @festivo = Festivo.find(params[:id])
    @festivo.destroy

    respond_to do |format|
      format.html { redirect_to(festivos_url) }
      format.xml  { head :ok }
    end
  end

  def list
    @curso = Curso.find(params[:id])
    @festivos = @curso.festivos
    @tab = :inicio
    @nav = @curso
  end
end
