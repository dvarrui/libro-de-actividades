extends Sprite2D

var speed = 200

func _process(delta):
	position.x = position.x + speed * delta
	if position.x < 0 or position.x > 1000:
		speed = speed * (-1)
