require 'test_helper'

class PesosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:pesos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_peso
    assert_difference('Peso.count') do
      post :create, :peso => { }
    end

    assert_redirected_to peso_path(assigns(:peso))
  end

  def test_should_show_peso
    get :show, :id => pesos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => pesos(:one).id
    assert_response :success
  end

  def test_should_update_peso
    put :update, :id => pesos(:one).id, :peso => { }
    assert_redirected_to peso_path(assigns(:peso))
  end

  def test_should_destroy_peso
    assert_difference('Peso.count', -1) do
      delete :destroy, :id => pesos(:one).id
    end

    assert_redirected_to pesos_path
  end
end
