class AulasController < ApplicationController
  layout 'default'
  
  # GET /aulas
  # GET /aulas.xml
  def index
    @aulas = Aula.find(:all)
    @tab = :recursos
    @nav = :aulas

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @aulas }
    end
  end

  # GET /aulas/1
  # GET /aulas/1.xmlh
  def show
    @aula = Aula.find(params[:id])
    @tab = :recursos
    @nav = @aula
    
    respond_to do |format|
      format.html # show.html.erb
      format.xml  { render :xml => @aula }
    end
  end

  # GET /aulas/new
  # GET /aulas/new.xml
  def new
    @centros = Centro.find(:all)
    @aula = Aula.new
    @tab = :recursos
    @nav = :aulas
    if !params[:id].nil? 
      @centros = Centro.find_all_by_id(params[:id])
      @nav = @centros[0] 
    end
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @aula }
    end
  end

  # GET /aulas/1/edit
  def edit
    @centros = Centro.find(:all)
    @aula = Aula.find(params[:id])
    @tab = :recursos
    @nav = @aula
  end

  # POST /aulas
  # POST /aulas.xml
  def create
    @aula = Aula.new(params[:aula])

    respond_to do |format|
      if @aula.save
        flash[:notice] = 'El Aula se creó correctamente.'
        format.html { redirect_to(@aula) }
        format.xml  { render :xml => @aula, :status => :created, :location => @aula }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @aula.errors, :status => :unprocessable_entity }
      end
    end
  end

  # PUT /aulas/1
  # PUT /aulas/1.xml
  def update
    @aula = Aula.find(params[:id])

    respond_to do |format|
      if @aula.update_attributes(params[:aula])
        flash[:notice] = 'El Aula se modificó correctamente.'
        format.html { redirect_to(@aula) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @aula.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /aulas/1
  # DELETE /aulas/1.xml
  def destroy
    @aula = Aula.find(params[:id])
    @aula.destroy

    respond_to do |format|
      format.html { redirect_to(aulas_url) }
      format.xml  { head :ok }
    end
  end

  def list
    @centro = Centro.find(params[:id])
    @aulas = @centro.aulas
    @tab = :recursos
    @nav = @centro
  end

:private
  def crear_horario_mostrar(id)
    tabla = Array.new
    
    12.times do |i|
      v=(i+1).to_s 
      tabla[i]=Array.new
      tabla[i][0]=v
      tabla[i][1]=tabla[i][2]=tabla[i][3]=tabla[i][4]=tabla[i][5]=''
    end
    
    num=0
    f=Programa.find_all_by_aula_id(id)
    f.each do |p|
      p.horarios.each do |h| 
         tabla[h.num_hora-1][h.dia_semana] = '<a href="/programas/'+h.programa.id.to_s+'">'+h.programa.modulo.codigo+', '+h.programa.profesor.nombre+'</a>'
         num=num+1
      end
    end
    return tabla,num
  end

  def crear_horario_editar(id)
    tabla = Array.new
    
    12.times do |i|
      v=(i+1).to_s 
      tabla[i]=Array.new
      tabla[i][0]=v
      tabla[i][1]='<a href="/horarios/new?ds=1&nh='+v+'">Crear</a>'
      tabla[i][2]='<a href="/horarios/new?ds=2&nh='+v+'">Crear</a>'
      tabla[i][3]='<a href="/horarios/new?ds=3&nh='+v+'">Crear</a>'
      tabla[i][4]='<a href="/horarios/new?ds=4&nh='+v+'">Crear</a>'
      tabla[i][5]='<a href="/horarios/new?ds=5&nh='+v+'">Crear</a>'
    end
    
    num=0
    f=Programa.find_all_by_aula_id(id)
    f.each do |p|
      p.horarios.each do |h| 
         tabla[h.num_hora-1][h.dia_semana] = '<a href="/horarios/destroy/'+h.id.to_s+'?redir='+id.to_s+'&model=aula">Borrar => '+h.programa.modulo.codigo+', '+h.programa.profesor.nombre+'</a>'
         num=num+1
      end
    end
    return tabla,num
  end


end
