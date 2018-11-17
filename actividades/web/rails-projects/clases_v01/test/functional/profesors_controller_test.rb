require File.dirname(__FILE__) + '/../test_helper'
require 'profesors_controller'

# Re-raise errors caught by the controller.
class ProfesorsController; def rescue_action(e) raise e end; end

class ProfesorsControllerTest < Test::Unit::TestCase
  fixtures :profesors

  def setup
    @controller = ProfesorsController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = profesors(:first).id
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

    assert_not_nil assigns(:profesors)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:profesor)
    assert assigns(:profesor).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:profesor)
  end

  def test_create
    num_profesors = Profesor.count

    post :create, :profesor => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_profesors + 1, Profesor.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:profesor)
    assert assigns(:profesor).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Profesor.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Profesor.find(@first_id)
    }
  end
end
