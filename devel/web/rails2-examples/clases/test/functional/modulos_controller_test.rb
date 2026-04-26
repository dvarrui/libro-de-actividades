require 'test_helper'

class ModulosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:modulos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_modulo
    assert_difference('Modulo.count') do
      post :create, :modulo => { }
    end

    assert_redirected_to modulo_path(assigns(:modulo))
  end

  def test_should_show_modulo
    get :show, :id => modulos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => modulos(:one).id
    assert_response :success
  end

  def test_should_update_modulo
    put :update, :id => modulos(:one).id, :modulo => { }
    assert_redirected_to modulo_path(assigns(:modulo))
  end

  def test_should_destroy_modulo
    assert_difference('Modulo.count', -1) do
      delete :destroy, :id => modulos(:one).id
    end

    assert_redirected_to modulos_path
  end
end
