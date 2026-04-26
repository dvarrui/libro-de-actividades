require File.dirname(__FILE__) + '/../test_helper'
require 'grupos_controller'

# Re-raise errors caught by the controller.
class GruposController; def rescue_action(e) raise e end; end

class GruposControllerTest < Test::Unit::TestCase
  fixtures :grupos

  def setup
    @controller = GruposController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = grupos(:first).id
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

    assert_not_nil assigns(:grupos)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:grupo)
    assert assigns(:grupo).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:grupo)
  end

  def test_create
    num_grupos = Grupo.count

    post :create, :grupo => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_grupos + 1, Grupo.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:grupo)
    assert assigns(:grupo).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Grupo.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Grupo.find(@first_id)
    }
  end
end
