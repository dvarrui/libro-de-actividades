require 'test_helper'

class AulasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:aulas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_aula
    assert_difference('Aula.count') do
      post :create, :aula => { }
    end

    assert_redirected_to aula_path(assigns(:aula))
  end

  def test_should_show_aula
    get :show, :id => aulas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => aulas(:one).id
    assert_response :success
  end

  def test_should_update_aula
    put :update, :id => aulas(:one).id, :aula => { }
    assert_redirected_to aula_path(assigns(:aula))
  end

  def test_should_destroy_aula
    assert_difference('Aula.count', -1) do
      delete :destroy, :id => aulas(:one).id
    end

    assert_redirected_to aulas_path
  end
end
