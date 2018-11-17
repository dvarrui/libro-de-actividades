require File.dirname(__FILE__) + '/../test_helper'
require 'fotos_controller'

# Re-raise errors caught by the controller.
class FotosController; def rescue_action(e) raise e end; end

class FotosControllerTest < Test::Unit::TestCase
  fixtures :fotos

  def setup
    @controller = FotosController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = fotos(:first).id
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

    assert_not_nil assigns(:fotos)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:foto)
    assert assigns(:foto).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:foto)
  end

  def test_create
    num_fotos = Foto.count

    post :create, :foto => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_fotos + 1, Foto.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:foto)
    assert assigns(:foto).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Foto.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Foto.find(@first_id)
    }
  end
end
