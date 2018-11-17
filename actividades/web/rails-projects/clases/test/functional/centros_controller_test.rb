require 'test_helper'

class CentrosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:centros)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_centro
    assert_difference('Centro.count') do
      post :create, :centro => { }
    end

    assert_redirected_to centro_path(assigns(:centro))
  end

  def test_should_show_centro
    get :show, :id => centros(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => centros(:one).id
    assert_response :success
  end

  def test_should_update_centro
    put :update, :id => centros(:one).id, :centro => { }
    assert_redirected_to centro_path(assigns(:centro))
  end

  def test_should_destroy_centro
    assert_difference('Centro.count', -1) do
      delete :destroy, :id => centros(:one).id
    end

    assert_redirected_to centros_path
  end
end
