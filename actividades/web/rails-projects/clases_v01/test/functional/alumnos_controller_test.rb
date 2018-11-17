require File.dirname(__FILE__) + '/../test_helper'
require 'alumnos_controller'

# Re-raise errors caught by the controller.
class AlumnosController; def rescue_action(e) raise e end; end

class AlumnosControllerTest < Test::Unit::TestCase
  fixtures :alumnos

  def setup
    @controller = AlumnosController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new

    @first_id = alumnos(:first).id
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

    assert_not_nil assigns(:alumnos)
  end

  def test_show
    get :show, :id => @first_id

    assert_response :success
    assert_template 'show'

    assert_not_nil assigns(:alumno)
    assert assigns(:alumno).valid?
  end

  def test_new
    get :new

    assert_response :success
    assert_template 'new'

    assert_not_nil assigns(:alumno)
  end

  def test_create
    num_alumnos = Alumno.count

    post :create, :alumno => {}

    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_equal num_alumnos + 1, Alumno.count
  end

  def test_edit
    get :edit, :id => @first_id

    assert_response :success
    assert_template 'edit'

    assert_not_nil assigns(:alumno)
    assert assigns(:alumno).valid?
  end

  def test_update
    post :update, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'show', :id => @first_id
  end

  def test_destroy
    assert_nothing_raised {
      Alumno.find(@first_id)
    }

    post :destroy, :id => @first_id
    assert_response :redirect
    assert_redirected_to :action => 'list'

    assert_raise(ActiveRecord::RecordNotFound) {
      Alumno.find(@first_id)
    }
  end
end
