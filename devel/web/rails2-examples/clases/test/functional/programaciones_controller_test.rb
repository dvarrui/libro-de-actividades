require 'test_helper'

class ProgramacionesControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:programaciones)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_programacion
    assert_difference('Programacion.count') do
      post :create, :programacion => { }
    end

    assert_redirected_to programacion_path(assigns(:programacion))
  end

  def test_should_show_programacion
    get :show, :id => programaciones(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => programaciones(:one).id
    assert_response :success
  end

  def test_should_update_programacion
    put :update, :id => programaciones(:one).id, :programacion => { }
    assert_redirected_to programacion_path(assigns(:programacion))
  end

  def test_should_destroy_programacion
    assert_difference('Programacion.count', -1) do
      delete :destroy, :id => programaciones(:one).id
    end

    assert_redirected_to programaciones_path
  end
end
