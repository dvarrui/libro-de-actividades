require 'test_helper'

class ParametrosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:parametros)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_parametro
    assert_difference('Parametro.count') do
      post :create, :parametro => { }
    end

    assert_redirected_to parametro_path(assigns(:parametro))
  end

  def test_should_show_parametro
    get :show, :id => parametros(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => parametros(:one).id
    assert_response :success
  end

  def test_should_update_parametro
    put :update, :id => parametros(:one).id, :parametro => { }
    assert_redirected_to parametro_path(assigns(:parametro))
  end

  def test_should_destroy_parametro
    assert_difference('Parametro.count', -1) do
      delete :destroy, :id => parametros(:one).id
    end

    assert_redirected_to parametros_path
  end
end
