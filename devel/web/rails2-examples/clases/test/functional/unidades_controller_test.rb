require 'test_helper'

class UnidadesControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:unidades)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_unidad
    assert_difference('Unidad.count') do
      post :create, :unidad => { }
    end

    assert_redirected_to unidad_path(assigns(:unidad))
  end

  def test_should_show_unidad
    get :show, :id => unidades(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => unidades(:one).id
    assert_response :success
  end

  def test_should_update_unidad
    put :update, :id => unidades(:one).id, :unidad => { }
    assert_redirected_to unidad_path(assigns(:unidad))
  end

  def test_should_destroy_unidad
    assert_difference('Unidad.count', -1) do
      delete :destroy, :id => unidades(:one).id
    end

    assert_redirected_to unidades_path
  end
end
