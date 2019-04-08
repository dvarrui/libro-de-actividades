extends Area2D

const MOVE_SPEED = 100
export (String) var rol = "player"

func _process(delta):	
	if Input.is_action_pressed("move_up") and position.y > 0:
		position.y -= MOVE_SPEED * delta
	if Input.is_action_pressed("move_down") and position.y < get_viewport_rect().size.y:
		position.y += MOVE_SPEED * delta
	if Input.is_action_pressed("move_left") and position.x > 0:
		position.x -= MOVE_SPEED * delta
	if Input.is_action_pressed("move_right") and position.x < get_viewport_rect().size.x:
		position.x += MOVE_SPEED * delta

func _on_player_area_entered(area):
	if area.rol == "enemy":
		get_tree().quit()
	if area.rol == "goal":
		$label.show()


func _on_player_area_exited(area):
	if area.rol == "goal":
		$label.hide()

func debug():
	#var currentScene = get_tree().get_current_scene().get_filename()
	var currentScene = get_filename()
	print(currentScene) 

# INFO 
#
# change scene to current scene:
# get_tree().change_scene("res://current_scene.tscn")

