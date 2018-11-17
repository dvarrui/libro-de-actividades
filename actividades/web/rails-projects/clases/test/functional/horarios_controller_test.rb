require 'test_helper'

class HorariosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:horarios)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_horario
    assert_difference('Horario.count') do
      post :create, :horario => { }
    end

    assert_redirected_to horario_path(assigns(:horario))
  end

  def test_should_show_horario
    get :show, :id => horarios(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => horarios(:one).id
    assert_response :success
  end

  def test_should_update_horario
    put :update, :id => horarios(:one).id, :horario => { }
    assert_redirected_to horario_path(assigns(:horario))
  end

  def test_should_destroy_horario
    assert_difference('Horario.count', -1) do
      delete :destroy, :id => horarios(:one).id
    end

    assert_redirected_to horarios_path
  end
end
