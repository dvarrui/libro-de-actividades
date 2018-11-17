require 'test_helper'

class CursosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:cursos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_curso
    assert_difference('Curso.count') do
      post :create, :curso => { }
    end

    assert_redirected_to curso_path(assigns(:curso))
  end

  def test_should_show_curso
    get :show, :id => cursos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => cursos(:one).id
    assert_response :success
  end

  def test_should_update_curso
    put :update, :id => cursos(:one).id, :curso => { }
    assert_redirected_to curso_path(assigns(:curso))
  end

  def test_should_destroy_curso
    assert_difference('Curso.count', -1) do
      delete :destroy, :id => cursos(:one).id
    end

    assert_redirected_to cursos_path
  end
end
