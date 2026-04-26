class NotasController < ApplicationController
	layout 'with2cols'
	
  # GET /notas
  # GET /notas.xml
  def index
    @notas = Nota.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @notas }
    end
  end

  # GET /notas/1
  # GET /notas/1.xml
  def show
    @nota = Nota.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @nota }
    end
  end

  # GET /notas/new
  # GET /notas/new.xml
  def new
    @nota = Nota.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @nota }
    end
  end

  # GET /notas/1/edit
  def edit
    @nota = Nota.find(params[:id])
  end

  # POST /notas
  # POST /notas.xml
  def create
    @nota = Nota.new(params[:nota])

    respond_to do |format|
      if @nota.save
        flash[:notice] = 'Nota was successfully created.'
        format.html { redirect_to(@nota) }
        format.xml  { render :xml => @nota, :status => :created, :location => @nota }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @nota.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /notas/1
  # PUT /notas/1.xml
  def update
    @nota = Nota.find(params[:id])

    respond_to do |format|
      if @nota.update_attributes(params[:nota])
        flash[:notice] = 'Nota was successfully updated.'
        format.html { redirect_to(@nota) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @nota.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /notas/1
  # DELETE /notas/1.xml
  def destroy
    @nota = Nota.find(params[:id])
    @nota.destroy

    respond_to do |format|
      format.html { redirect_to(notas_url) }
      format.xml  { head :ok }
    end
  end
  
  def show_grupo
    @grupo = Grupo.find(params[:id])

    if (@grupo.matriculas.size==0) then
      redirect_to(url_for(:action=>'error',:id=>@grupo))
      return
    end
    @faltas = @grupo.faltas

    @tabla=[]
    @col=[]
    @fila=[]
    
    num_faltas = @grupo.matriculas[0].faltas.size
    num_matriculas = @grupo.matriculas.size
    
    @grupo.matriculas.each do |m|
    	@col << m
    	i=[]
    	m.faltas.each do |f|
 			i << f   	
    	end
    	@tabla << i
    end

    @grupo.matriculas[0].faltas.each do |f|
    	@fila << 'D'+f.numdia.to_s
    end
  end

end
