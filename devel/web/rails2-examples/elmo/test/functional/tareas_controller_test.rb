require 'test_helper'

class TareasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:tareas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_tarea
    assert_difference('Tarea.count') do
      post :create, :tarea => { }
    end

    assert_redirected_to tarea_path(assigns(:tarea))
  end

  def test_should_show_tarea
    get :show, :id => tareas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => tareas(:one).id
    assert_response :success
  end

  def test_should_update_tarea
    put :update, :id => tareas(:one).id, :tarea => { }
    assert_redirected_to tarea_path(assigns(:tarea))
  end

  def test_should_destroy_tarea
    assert_difference('Tarea.count', -1) do
      delete :destroy, :id => tareas(:one).id
    end

    assert_redirected_to tareas_path
  end
end
