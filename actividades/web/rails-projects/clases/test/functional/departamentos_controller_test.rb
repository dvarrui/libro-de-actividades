require 'test_helper'

class DepartamentosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:departamentos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_departamento
    assert_difference('Departamento.count') do
      post :create, :departamento => { }
    end

    assert_redirected_to departamento_path(assigns(:departamento))
  end

  def test_should_show_departamento
    get :show, :id => departamentos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => departamentos(:one).id
    assert_response :success
  end

  def test_should_update_departamento
    put :update, :id => departamentos(:one).id, :departamento => { }
    assert_redirected_to departamento_path(assigns(:departamento))
  end

  def test_should_destroy_departamento
    assert_difference('Departamento.count', -1) do
      delete :destroy, :id => departamentos(:one).id
    end

    assert_redirected_to departamentos_path
  end
end
