require File.dirname(__FILE__) + '/../test_helper'
require 'unidad_controller'

# Re-raise errors caught by the controller.
class UnidadController; def rescue_action(e) raise e end; end

class UnidadControllerTest < Test::Unit::TestCase
  def setup
    @controller = UnidadController.new
    @request    = ActionController::TestRequest.new
    @response   = ActionController::TestResponse.new
  end

  # Replace this with your real tests.
  def test_truth
    assert true
  end
end
