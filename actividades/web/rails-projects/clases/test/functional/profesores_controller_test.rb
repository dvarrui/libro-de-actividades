require 'test_helper'

class ProfesoresControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:profesores)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_profesor
    assert_difference('Profesor.count') do
      post :create, :profesor => { }
    end

    assert_redirected_to profesor_path(assigns(:profesor))
  end

  def test_should_show_profesor
    get :show, :id => profesores(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => profesores(:one).id
    assert_response :success
  end

  def test_should_update_profesor
    put :update, :id => profesores(:one).id, :profesor => { }
    assert_redirected_to profesor_path(assigns(:profesor))
  end

  def test_should_destroy_profesor
    assert_difference('Profesor.count', -1) do
      delete :destroy, :id => profesores(:one).id
    end

    assert_redirected_to profesores_path
  end
end
