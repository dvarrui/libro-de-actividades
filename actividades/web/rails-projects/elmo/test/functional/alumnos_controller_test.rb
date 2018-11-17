require 'test_helper'

class AlumnosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:alumnos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_alumno
    assert_difference('Alumno.count') do
      post :create, :alumno => { }
    end

    assert_redirected_to alumno_path(assigns(:alumno))
  end

  def test_should_show_alumno
    get :show, :id => alumnos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => alumnos(:one).id
    assert_response :success
  end

  def test_should_update_alumno
    put :update, :id => alumnos(:one).id, :alumno => { }
    assert_redirected_to alumno_path(assigns(:alumno))
  end

  def test_should_destroy_alumno
    assert_difference('Alumno.count', -1) do
      delete :destroy, :id => alumnos(:one).id
    end

    assert_redirected_to alumnos_path
  end
end
