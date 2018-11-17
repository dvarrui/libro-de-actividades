require 'test_helper'

class AsignaturasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:asignaturas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_asignatura
    assert_difference('Asignatura.count') do
      post :create, :asignatura => { }
    end

    assert_redirected_to asignatura_path(assigns(:asignatura))
  end

  def test_should_show_asignatura
    get :show, :id => asignaturas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => asignaturas(:one).id
    assert_response :success
  end

  def test_should_update_asignatura
    put :update, :id => asignaturas(:one).id, :asignatura => { }
    assert_redirected_to asignatura_path(assigns(:asignatura))
  end

  def test_should_destroy_asignatura
    assert_difference('Asignatura.count', -1) do
      delete :destroy, :id => asignaturas(:one).id
    end

    assert_redirected_to asignaturas_path
  end
end
