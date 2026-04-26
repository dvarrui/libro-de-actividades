require File.dirname(__FILE__) + '/../test_helper'
require 'categorias_controller'

# Re-raise errors caught by the controller.
class CategoriasController; def rescue_action(e) raise e end; end

class CategoriasControllerTest < Test::Unit::TestCase
  fixtures :categorias

  def setup
    @controller = CategoriasController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = categorias(:first).id
  end

  def test_index
    get :index
    assert_response :success
    assert_template 'list'
  end

  def test_list
    get :list

    assert_response :success
    assert_template 'list'

    assert_not_nil assigns(:categorias)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:categoria)
    assert assigns(:categoria).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:categoria)
  end

  def test_create
    num_categorias = Categoria.count

    post :create, :categoria => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_categorias + 1, Categoria.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:categoria)
    assert assigns(:categoria).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Categoria.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Categoria.find(@first_id)
    }
  end
end
