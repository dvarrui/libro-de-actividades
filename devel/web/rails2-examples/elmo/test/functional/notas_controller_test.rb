require 'test_helper'

class NotasControllerTest < ActionController::TestCase
  def test_should_get_index
    get :index
    assert_response :success
    assert_not_nil assigns(:notas)
  end

  def test_should_get_new
    get :new
    assert_response :success
  end

  def test_should_create_nota
    assert_difference('Nota.count') do
      post :create, :nota => { }
    end

    assert_redirected_to nota_path(assigns(:nota))
  end

  def test_should_show_nota
    get :show, :id => notas(:one).id
    assert_response :success
  end

  def test_should_get_edit
    get :edit, :id => notas(:one).id
    assert_response :success
  end

  def test_should_update_nota
    put :update, :id => notas(:one).id, :nota => { }
    assert_redirected_to nota_path(assigns(:nota))
  end

  def test_should_destroy_nota
    assert_difference('Nota.count', -1) do
      delete :destroy, :id => notas(:one).id
    end

    assert_redirected_to notas_path
  end
end
