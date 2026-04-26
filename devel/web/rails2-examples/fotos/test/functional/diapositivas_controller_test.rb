require File.dirname(__FILE__) + '/../test_helper'
require 'diapositivas_controller'

# Re-raise errors caught by the controller.
class DiapositivasController; def rescue_action(e) raise e end; end

class DiapositivasControllerTest < Test::Unit::TestCase
  fixtures :diapositivas

  def setup
    @controller = DiapositivasController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = diapositivas(:first).id
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

    assert_not_nil assigns(:diapositivas)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:diapositiva)
    assert assigns(:diapositiva).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:diapositiva)
  end

  def test_create
    num_diapositivas = Diapositiva.count

    post :create, :diapositiva => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_diapositivas + 1, Diapositiva.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:diapositiva)
    assert assigns(:diapositiva).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Diapositiva.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Diapositiva.find(@first_id)
    }
  end
end
