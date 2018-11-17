require File.dirname(__FILE__) + '/../test_helper'
require 'pases_controller'

# Re-raise errors caught by the controller.
class PasesController; def rescue_action(e) raise e end; end

class PasesControllerTest < Test::Unit::TestCase
  fixtures :pases

  def setup
    @controller = PasesController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = pases(:first).id
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

    assert_not_nil assigns(:pases)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:pase)
    assert assigns(:pase).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:pase)
  end

  def test_create
    num_pases = Pase.count

    post :create, :pase => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_pases + 1, Pase.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:pase)
    assert assigns(:pase).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Pase.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Pase.find(@first_id)
    }
  end
end
