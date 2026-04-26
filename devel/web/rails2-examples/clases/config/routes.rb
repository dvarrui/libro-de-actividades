ActionController::Routing::Routes.draw do |map|
  map.resources :profesores

  map.resources :programaciones

  map.resources :unidades

  map.resources :actividades

  map.resources :empresas

  map.resources :centros

  map.connect 'horarios/programacion/:id',
              :controller  => 'horarios',
              :action      => 'programacion',
              :plantilla   => 'show'
  map.connect 'horarios/programacion/:id/edit',
              :controller  => 'horarios',
              :action      => 'programacion',
              :plantilla   => 'edit'
  map.connect 'horarios/profesor/:profesor_id/:curso_id',
              :controller  => 'horarios',
              :action      => 'profesor',
              :profesor_id => /\d+/,
              :curso_id    => /\d+/,
              :plantilla   => 'show'
  map.connect 'horarios/profesor/:profesor_id/:curso_id/edit',
              :controller  => 'horarios',
              :action      => 'profesor',
              :profesor_id => /\d+/,
              :curso_id    => /\d+/,
              :plantilla   => 'edit'
  map.connect 'horarios/aula/:aula_id/:curso_id',
              :controller  => 'horarios',
              :action      => 'aula',
              :aula_id     => /\d+/,
              :curso_id    => /\d+/,
              :plantilla   => 'show'
  map.connect 'horarios/aula/:aula_id/:curso_id/edit',
              :controller  => 'horarios',
              :action      => 'aula',
              :profesor_id => /\d+/,
              :curso_id    => /\d+/,
              :plantilla   => 'edit'
  map.resources :horarios

  map.resources :alumnos

  map.resources :pesos

  map.resources :festivos

  map.resources :cursos

  map.resources :departamentos

  map.resources :aulas

  map.resources :modulos

  map.resources :ciclos

  # The priority is based upon order of creation: first created -> highest priority.

  # Sample of regular route:
  #   map.connect 'products/:id', :controller => 'catalog', :action => 'view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   map.purchase 'products/:id/purchase', :controller => 'catalog', :action => 'purchase'
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   map.resources :products

  # Sample resource route with options:
  #   map.resources :products, :member => { :short => :get, :toggle => :post }, :collection => { :sold => :get }

  # Sample resource route with sub-resources:
  #   map.resources :products, :has_many => [ :comments, :sales ], :has_one => :seller
  
  # Sample resource route with more complex sub-resources
  #   map.resources :products do |products|
  #     products.resources :comments
  #     products.resources :sales, :collection => { :recent => :get }
  #   end

  # Sample resource route within a namespace:
  #   map.namespace :admin do |admin|
  #     # Directs /admin/products/* to Admin::ProductsController (app/controllers/admin/products_controller.rb)
  #     admin.resources :products
  #   end

  # You can have the root of your site routed with map.root -- just remember to delete public/index.html.
  # map.root :controller => "welcome"

  # See how all your routes lay out with "rake routes"

  # Install the default routes as the lowest priority.
  map.connect ':controller/:action/:id'
  map.connect ':controller/:action/:id.:format'
  map.connect '', :controller => 'menus'
end
