require 'test_helper'

class CiclosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:ciclos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_ciclo
    assert_difference('Ciclo.count') do
      post :create, :ciclo => { }
    end

    assert_redirected_to ciclo_path(assigns(:ciclo))
  end

  def test_should_show_ciclo
    get :show, :id => ciclos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => ciclos(:one).id
    assert_response :success
  end

  def test_should_update_ciclo
    put :update, :id => ciclos(:one).id, :ciclo => { }
    assert_redirected_to ciclo_path(assigns(:ciclo))
  end

  def test_should_destroy_ciclo
    assert_difference('Ciclo.count', -1) do
      delete :destroy, :id => ciclos(:one).id
    end

    assert_redirected_to ciclos_path
  end
end
