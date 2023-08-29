extends Sprite2D

var speed = 100

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	position.x = position.x + speed * delta
