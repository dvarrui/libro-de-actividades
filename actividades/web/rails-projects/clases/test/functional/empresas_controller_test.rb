require 'test_helper'

class EmpresasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:empresas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_empresa
    assert_difference('Empresa.count') do
      post :create, :empresa => { }
    end

    assert_redirected_to empresa_path(assigns(:empresa))
  end

  def test_should_show_empresa
    get :show, :id => empresas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => empresas(:one).id
    assert_response :success
  end

  def test_should_update_empresa
    put :update, :id => empresas(:one).id, :empresa => { }
    assert_redirected_to empresa_path(assigns(:empresa))
  end

  def test_should_destroy_empresa
    assert_difference('Empresa.count', -1) do
      delete :destroy, :id => empresas(:one).id
    end

    assert_redirected_to empresas_path
  end
end
