class HorariosController < ApplicationController
  layout 'default'
  
  # GET /horarios
  # GET /horarios.xml
  def index
    @programaciones = Programacion.find(:all)
    @tab = :inicio
    @nav = :horarios
  end

  # GET /horarios/new
  # GET /horarios/new.xml
  def new
    @programaciones = Programa.find(:all)
    @horario = Horario.new
    @nav = :horarios
    
    @horario.dia_semana=params[:ds].to_i if !params[:ds].nil?
    @horario.num_hora=params[:nh].to_i if !params[:nh].nil?
    
    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @horario }
    end
  end

  # POST /horarios
  # POST /horarios.xml
  def create
    @horario = Horario.new(params[:horario])

    respond_to do |format|
      if @horario.save
        flash[:notice] = 'El Horario se creó correctamente.'
        format.html { redirect_to(@horario) }
        format.xml  { render :xml => @horario, :status => :created, :location => @horario }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @horario.errors, :status => :unprocessable_entity }
      end
    end
  end

  # DELETE /horarios/1
  # DELETE /horarios/1.xml
  def destroy
    @horario = Horario.find(params[:id])
    @horario.destroy

    redir = params[:redir].to_i if !params[:redir].nil?
    model = params[:model].to_i if !params[:model].nil?

    respond_to do |format|
      if (redir.nil?) then
        format.html { redirect_to(horarios_url) }
      else
        format.html { redirect_to(:controller => :aulas, :action => :show, :id => redir) }
      end
      format.xml  { head :ok }
    end
  end
  
  def programacion
    @programacion = Programacion.find(params[:id])
    @tab = :inicio
    @nav = @programacion
    
    @tabla = tabla_horaria_de @programacion
    @titulo=@programacion.codigo
    render :template => 'horarios/'+params[:plantilla]
  end

  def profesor
    @profesor = Profesor.find(params[:profesor_id])
    @curso = Curso.find(params[:curso_id])
    @tab = :inicio
    @nav = []; @nav << @profesor; @nav << @curso
    
    @tabla = tabla_horaria_de @profesor,@curso
    @titulo = @profesor.nombre
    render :template => 'horarios/'+params[:plantilla]
  end

  def aula
    @aula = Aula.find(params[:aula_id])
    @curso = Curso.find(params[:curso_id])
    @tab = :inicio
    @nav = []; @nav << @aula; @nav << @curso
    
    @tabla = tabla_horaria_de @aula,@curso
    @titulo = @aula.codigo
    render :template => 'horarios/'+params[:plantilla]
  end
  
private

  def tabla_horaria_de(modo, curso=modo.curso)
    horas = curso.centro.horas
    reg = curso.horarios
    
    #Crear Array tabla y completar las horas
    tabla = Array.new
    (horas.count-1).times do |i| 
      tabla[i]=Array.new   
      tabla[i][0] = horas[i][0] if horas[i][0]>0
      tabla[i][1] = horas[i][1]

      (2..6).each do |j|
        tabla[i][j]=:descanso if horas[i][2]=='d'
        tabla[i][j]=:libre if horas[i][2]=='h'
      end
    end
    
    aux = Array.new
    (horas.count-1).times do |i|
      aux[horas[i][0]]=i if horas[i][0]>0
    end
    
    reg.each do |i|
      fila = aux[i.num_hora]; col = i.num_dia+1      
      tabla[fila][col]=rellenar_modo_pg(modo,i,tabla[fila][col]) if modo.class==Programacion
      tabla[fila][col]=rellenar_modo_pr(modo,i,tabla[fila][col]) if modo.class==Profesor
      tabla[fila][col]=rellenar_modo_au(modo,i,tabla[fila][col]) if modo.class==Aula
    end
    
    return tabla
  end
  
  def rellenar_modo_pg(pg,ent,sal)
    poner=nil
    
    if ent.programacion==pg then
      poner=ent
    elsif ent.programacion.profesor==pg.profesor then
      poner=:ocupado
    elsif ent.programacion.aula==pg.aula then
      poner=:ocupado
    end
    
    if poner then
      if sal==:libre then
            sal=poner
      else
         sal=:error
      end
    end
    return sal
  end
  
  def rellenar_modo_pr(pr,ent,sal)
    poner=nil
    
    if ent.programacion.profesor==pr then
      poner=ent
    end
    
    if poner then
      if sal==:libre then
            sal=poner
      else
         sal=:error
      end
    end
    return sal
  end
  
  def rellenar_modo_au(au,ent,sal)
    poner=nil
    
    if ent.programacion.aula==au then
      poner=ent
    end
    
    if poner then
      if sal==:libre then
            sal=poner
      else
         sal=:error
      end
    end
    return sal
  end
  
end
