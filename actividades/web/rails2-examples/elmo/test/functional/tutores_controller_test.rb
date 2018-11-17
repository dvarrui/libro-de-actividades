require 'test_helper'

class TutoresControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:tutores)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_tutor
    assert_difference('Tutor.count') do
      post :create, :tutor => { }
    end

    assert_redirected_to tutor_path(assigns(:tutor))
  end

  def test_should_show_tutor
    get :show, :id => tutores(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => tutores(:one).id
    assert_response :success
  end

  def test_should_update_tutor
    put :update, :id => tutores(:one).id, :tutor => { }
    assert_redirected_to tutor_path(assigns(:tutor))
  end

  def test_should_destroy_tutor
    assert_difference('Tutor.count', -1) do
      delete :destroy, :id => tutores(:one).id
    end

    assert_redirected_to tutores_path
  end
end
