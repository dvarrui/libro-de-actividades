require File.dirname(__FILE__) + '/../test_helper'
require 'departamentos_controller'

# Re-raise errors caught by the controller.
class DepartamentosController; def rescue_action(e) raise e end; end

class DepartamentosControllerTest < Test::Unit::TestCase
  fixtures :departamentos

  def setup
    @controller = DepartamentosController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = departamentos(:first).id
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

    assert_not_nil assigns(:departamentos)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:departamento)
    assert assigns(:departamento).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:departamento)
  end

  def test_create
    num_departamentos = Departamento.count

    post :create, :departamento => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_departamentos + 1, Departamento.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:departamento)
    assert assigns(:departamento).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Departamento.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Departamento.find(@first_id)
    }
  end
end
