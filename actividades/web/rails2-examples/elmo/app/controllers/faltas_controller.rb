class FaltasController < ApplicationController
	layout 'with1col'
	
  # GET /faltas
  # GET /faltas.xml
  def index
    @faltas = Falta.find(:all)

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @faltas }
    end
  end

  # GET /faltas/1
  # GET /faltas/1.xml
  def show
    @falta = Falta.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @falta }
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

  def error
    @grupo = Grupo.find(params[:id])
  end
  
  # GET /faltas/new
  # GET /faltas/new.xml
  def new
    @falta = Falta.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @falta }
    end
  end

  # GET /faltas/1/edit
  def edit
    @falta = Falta.find(params[:id])
  end

  def edit_grupo
    @grupo = Grupo.find(params[:id])
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
  
  # POST /faltas
  # POST /faltas.xml
  def create
    @falta = Falta.new(params[:falta])

    respond_to do |format|
      if @falta.save
        flash[:notice] = 'Falta was successfully created.'
        format.html { redirect_to(@falta) }
        format.xml  { render :xml => @falta, :status => :created, :location => @falta }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @falta.errors, :status => :unprocessable_entity }
      end
    end
  end

  def create_grupo
    @grupo = Grupo.find(params[:id])
	 year=params[:entrada]['fecha(1i)'].to_i
	 month=params[:entrada]['fecha(2i)'].to_i
	 day=params[:entrada]['fecha(3i)'].to_i
	 
	 if @grupo.matriculas.size==0 then
	     redirect_to(url_for(:action=>'show_grupo', :id=>@grupo))
    end
    	 
	 diamax=0
	 @grupo.matriculas[0].faltas.each do |f|
	 	if f.numdia > diamax then
	 		diamax=f.numdia
	 	end
	 end
	 diamax=diamax+1
	 t = Time.mktime(year,month,day,0,0,0)
	 
	 @grupo.matriculas.each do |m|
	   f = Falta.new
	   f.numdia = diamax
	   f.fecha = t
	   f.valor = 'A'
	   m.faltas << f
	 end

    redirect_to(url_for(:action=>'show_grupo', :id=>@grupo))
  end
  
  # PUT /faltas/1
  # PUT /faltas/1.xml
  def update
    @falta = Falta.find(params[:id])

    respond_to do |format|
      if @falta.update_attributes(params[:falta])
        flash[:notice] = 'Falta was successfully updated.'
        format.html { redirect_to(@falta) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @falta.errors, :status => :unprocessable_entity }
      end
    end
  end

  def update_grupo
    @grupo = Grupo.find(params[:id])
    entrada = params[:entrada]
    entrada.each_key do |key|
      #Localizar las falta por id
      id = key.to_i
      f = Falta.find(id)
      #actualizar su valor según indica params[]
      f.valor = entrada[key].upcase
      f.save
    end
    
    redirect_to(url_for(:action=>'show_grupo', :id=>@grupo))
  end

  # DELETE /faltas/1
  # DELETE /faltas/1.xml
  def destroy
    @falta = Falta.find(params[:id])
    @falta.destroy

    respond_to do |format|
      format.html { redirect_to(faltas_url) }
      format.xml  { head :ok }
    end
  end

  def destroy_grupo
    @grupo = Grupo.find(params[:id])

    diamax=0
	 @grupo.matriculas[0].faltas.each do |f|
	 	if f.numdia > diamax then
	 		diamax=f.numdia
	 	end
	 end
	
	 p diamax
	 @grupo.faltas.each do |f|
	   if f.numdia==diamax then
	   	f.destroy
	   end
	 end

    redirect_to(url_for(:action=>'show_grupo', :id=>@grupo))
  end
end
