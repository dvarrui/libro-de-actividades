require 'test_helper'

class FaltasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:faltas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_falta
    assert_difference('Falta.count') do
      post :create, :falta => { }
    end

    assert_redirected_to falta_path(assigns(:falta))
  end

  def test_should_show_falta
    get :show, :id => faltas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => faltas(:one).id
    assert_response :success
  end

  def test_should_update_falta
    put :update, :id => faltas(:one).id, :falta => { }
    assert_redirected_to falta_path(assigns(:falta))
  end

  def test_should_destroy_falta
    assert_difference('Falta.count', -1) do
      delete :destroy, :id => faltas(:one).id
    end

    assert_redirected_to faltas_path
  end
end
