class UsuariosController < ApplicationController
  def index
    list
    render :action => 'list'
  end

  # GETs should be safe (see http://www.w3.org/2001/tag/doc/whenToUseGet.html)
  verify :method => :post, :only => [ :destroy, :create, :update ],
         :redirect_to => { :action => :list }

  def list
    @usuario_pages, @usuarios = paginate :usuarios, :per_page => 10
  end

  def show
    @usuario = Usuario.find(params[:id])
  end

  def new
    @usuario = Usuario.new
  end

  def create
    @usuario = Usuario.new(params[:usuario])
    if @usuario.save
      flash[:notice] = 'Usuario was successfully created.'
      redirect_to :action => 'list'
    else
      render :action => 'new'
    end
  end

  def edit
    @usuario = Usuario.find(params[:id])
  end

  def update
    @usuario = Usuario.find(params[:id])
    if @usuario.update_attributes(params[:usuario])
      flash[:notice] = 'Usuario was successfully updated.'
      redirect_to :action => 'show', :id => @usuario
    else
      render :action => 'edit'
    end
  end

  def destroy
    Usuario.find(params[:id]).destroy
    redirect_to :action => 'list'
  end

  def login
    @usuario = Usuario.new
  end

  def entrar
    @usuario = Usuario.new(params[:usuario])
    #Autenticar el usuario
    puts @usuario.nombre
    puts @usuario.clave
    session[:usuario]=@usuario
    render :text => 'El usuario '+@usuario.nombre+' con clave '+@usuario.clave
  end
end
