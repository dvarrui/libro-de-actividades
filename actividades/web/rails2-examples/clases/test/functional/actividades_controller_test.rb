require 'test_helper'

class ActividadesControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:actividades)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_actividad
    assert_difference('Actividad.count') do
      post :create, :actividad => { }
    end

    assert_redirected_to actividad_path(assigns(:actividad))
  end

  def test_should_show_actividad
    get :show, :id => actividades(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => actividades(:one).id
    assert_response :success
  end

  def test_should_update_actividad
    put :update, :id => actividades(:one).id, :actividad => { }
    assert_redirected_to actividad_path(assigns(:actividad))
  end

  def test_should_destroy_actividad
    assert_difference('Actividad.count', -1) do
      delete :destroy, :id => actividades(:one).id
    end

    assert_redirected_to actividades_path
  end
end
