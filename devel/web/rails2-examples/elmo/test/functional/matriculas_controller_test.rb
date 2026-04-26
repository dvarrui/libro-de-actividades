require 'test_helper'

class MatriculasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:matriculas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_matricula
    assert_difference('Matricula.count') do
      post :create, :matricula => { }
    end

    assert_redirected_to matricula_path(assigns(:matricula))
  end

  def test_should_show_matricula
    get :show, :id => matriculas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => matriculas(:one).id
    assert_response :success
  end

  def test_should_update_matricula
    put :update, :id => matriculas(:one).id, :matricula => { }
    assert_redirected_to matricula_path(assigns(:matricula))
  end

  def test_should_destroy_matricula
    assert_difference('Matricula.count', -1) do
      delete :destroy, :id => matriculas(:one).id
    end

    assert_redirected_to matriculas_path
  end
end
