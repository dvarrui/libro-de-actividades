class CursosController < ApplicationController
  layout 'default'
  
  # GET /cursos
  # GET /cursos.xml
  def index
    @cursos = Curso.find(:all)
    @tab = :inicio
    @nav = :cursos

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @cursos }
    end
  end

  # GET /cursos/1
  # GET /cursos/1.xml
  def show
    @curso = Curso.find(params[:id])
    @tab = :inicio
    @nav = @curso
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @curso }
    end
  end

  # GET /cursos/new
  # GET /cursos/new.xml
  def new
    @centros = Centro.find(:all)
    @curso = Curso.new
    @tab = :inicio
    @nav = :cursos
    
    a=Time.now.year
    @curso.inicio_clases = Date.new(y=a,m=9,d=15)
    @curso.fin_eval1 = Date.new(y=a,m=12,d=15)
    @curso.fin_eval2 = Date.new(y=(a+1),m=3,d=15)
    @curso.fin_eval3 = Date.new(y=(a+1),m=6,d=15)
    if !params[:id].nil? 
      @centros = Centro.find_all_by_id(params[:id])
      @nav = @centros[0] 
    end

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @curso }
    end
  end

  # GET /cursos/1/edit
  def edit
    @centros = Centro.find(:all)
    @curso = Curso.find(params[:id])
    @tab = :inicio
    @nav = @curso
  end

  # POST /cursos
  # POST /cursos.xml
  def create
    @curso = Curso.new(params[:curso])

    respond_to do |format|
      if @curso.save
        flash[:notice] = 'El Curso se creó correctamente.'
        format.html { redirect_to(@curso) }
        format.xml  { render :xml => @curso, :status => :created, :location => @curso }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @curso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /cursos/1
  # PUT /cursos/1.xml
  def update
    @curso = Curso.find(params[:id])

    respond_to do |format|
      if @curso.update_attributes(params[:curso])
        flash[:notice] = 'El Curso se guardó correctamente.'
        format.html { redirect_to(@curso) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @curso.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /cursos/1
  # DELETE /cursos/1.xml
  def destroy
    @curso = Curso.find(params[:id])
    @curso.destroy

    respond_to do |format|
      format.html { redirect_to(cursos_url) }
      format.xml  { head :ok }
    end
  end

  def list
    @centro = Centro.find(params[:id])
    @cursos = @centro.cursos
    @tab = :inicio
    @nav = @centro
  end
end
