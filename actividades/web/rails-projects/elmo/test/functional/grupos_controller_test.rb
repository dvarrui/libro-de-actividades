require 'test_helper'

class GruposControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:grupos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_grupo
    assert_difference('Grupo.count') do
      post :create, :grupo => { }
    end

    assert_redirected_to grupo_path(assigns(:grupo))
  end

  def test_should_show_grupo
    get :show, :id => grupos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => grupos(:one).id
    assert_response :success
  end

  def test_should_update_grupo
    put :update, :id => grupos(:one).id, :grupo => { }
    assert_redirected_to grupo_path(assigns(:grupo))
  end

  def test_should_destroy_grupo
    assert_difference('Grupo.count', -1) do
      delete :destroy, :id => grupos(:one).id
    end

    assert_redirected_to grupos_path
  end
end
