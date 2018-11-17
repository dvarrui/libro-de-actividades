require File.dirname(__FILE__) + '/../test_helper'
require 'modulos_controller'

# Re-raise errors caught by the controller.
class ModulosController; def rescue_action(e) raise e end; end

class ModulosControllerTest < Test::Unit::TestCase
  fixtures :modulos

  def setup
    @controller = ModulosController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new
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

    assert_not_nil assigns(:modulos)
  end

  def test_show
    get :show, :id => 1

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:modulo)
    assert assigns(:modulo).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:modulo)
  end

  def test_create
    num_modulos = Modulo.count

    post :create, :modulo => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_modulos + 1, Modulo.count
  end

  def test_edit
    get :edit, :id => 1

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:modulo)
    assert assigns(:modulo).valid?
  end

  def test_update
    post :update, :id => 1
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => 1
  end

  def test_destroy
    assert_not_nil Modulo.find(1)

    post :destroy, :id => 1
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Modulo.find(1)
    }
  end
end
