require File.dirname(__FILE__) + '/../test_helper'
require 'ciclos_controller'

# Re-raise errors caught by the controller.
class CiclosController; def rescue_action(e) raise e end; end

class CiclosControllerTest < Test::Unit::TestCase
  fixtures :ciclos

  def setup
    @controller = CiclosController.new
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

    assert_not_nil assigns(:ciclos)
  end

  def test_show
    get :show, :id => 1

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:ciclo)
    assert assigns(:ciclo).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:ciclo)
  end

  def test_create
    num_ciclos = Ciclo.count

    post :create, :ciclo => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_ciclos + 1, Ciclo.count
  end

  def test_edit
    get :edit, :id => 1

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:ciclo)
    assert assigns(:ciclo).valid?
  end

  def test_update
    post :update, :id => 1
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => 1
  end

  def test_destroy
    assert_not_nil Ciclo.find(1)

    post :destroy, :id => 1
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Ciclo.find(1)
    }
  end
end
