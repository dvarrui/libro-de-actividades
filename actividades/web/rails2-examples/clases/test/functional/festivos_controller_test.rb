require 'test_helper'

class FestivosControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:festivos)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_festivo
    assert_difference('Festivo.count') do
      post :create, :festivo => { }
    end

    assert_redirected_to festivo_path(assigns(:festivo))
  end

  def test_should_show_festivo
    get :show, :id => festivos(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => festivos(:one).id
    assert_response :success
  end

  def test_should_update_festivo
    put :update, :id => festivos(:one).id, :festivo => { }
    assert_redirected_to festivo_path(assigns(:festivo))
  end

  def test_should_destroy_festivo
    assert_difference('Festivo.count', -1) do
      delete :destroy, :id => festivos(:one).id
    end

    assert_redirected_to festivos_path
  end
end
