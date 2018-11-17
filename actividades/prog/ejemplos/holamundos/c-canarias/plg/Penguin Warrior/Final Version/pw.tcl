# Penguin Warrior game script (Tcl).
# This version of the script implements two states:
# attack and evade. In the attack state, the opponent
# homes in on the player and fires its weapons. After it
# gets within a certain proximity of the player, it switches
# to the evade state, in which it aims at a random point in the
# world.

# The name of our current state, attack or evade.
set state attack

# Coordinates to aim towards. In the attack state these will
# be set to the player's position. In the evade state these
# will be set to random values.
set target_x 0
set target_y 0

proc playComputer { } {
    global computer_x computer_y computer_angle computer_accel
    global player_x player_y player_angle player_accel
    global target_x target_y state

    # Global constants. These are initially set by InitScripting().
    global world_width world_height
    global player_forward_thrust player_reverse_thrust

    if {[string equal $state attack]} {
	#
	# Code for the attack state
	#

	# In attack mode, our target is the player.
	set target_x $player_x
	set target_y $player_y

	# If we're too close to the player, switch to evade.
	set distance [getDistanceToTarget]
	if {$distance < 30} {
	    set state evade

	    # Set an invalid target so the evade state will
	    # come up with a new one.
	    set target_x -1

	    return
	}

	# If we're far away, speed up. If we're close, lay off the throttle.
	if {$distance > 100} {
	    set computer_accel $player_forward_thrust
	} elseif {$distance > 50} {
	    set computer_accel [expr {$player_forward_thrust/3}]
	} else {
	    set computer_accel 0
	}

	# If we're close enough to the player, fire away!
	if {$distance < 200} {
	    fireWeapon
	}

    } else {
	#
	# Code for the evade state
	#

	# Have we hit our target yet? (within a reasonable tolerance)
	if {abs($target_x - $computer_x) < 10 && abs($target_y - $computer_y) < 10} {
	    puts "Going back into ATTACK mode."
	    set state attack
	    return
	}

	# Do we need to find a new target?
	if {$target_x < 0} {
	    # Select a random point in the world as our target.
	    set target_x [expr {int(rand()*$world_width)}]
	    set target_y [expr {int(rand()*$world_height)}]

	    puts "Selected new EVADE target."
	}

	set computer_accel $player_forward_thrust

    }

    #
    # State-independent code
    #

    # Figure out the quickest way to aim at our destination.
    set target_angle [getAngleToTarget]
    set arc [expr {$target_angle - $computer_angle}]
    if {$arc < 0} {
	set arc [expr {$arc + 360}]
    }

    if {$arc < 180} {
	set computer_angle [expr {$computer_angle + 3}]
    } else {
	set computer_angle [expr {$computer_angle - 3}]
    }

}

# Returns the distance (in pixels) between the target coordinate and the opponent.
proc getDistanceToTarget { } {
    global computer_x computer_y target_x target_y

    set xdiff [expr {$computer_x - $target_x}]
    set ydiff [expr {$computer_y - $target_y}]

    return [expr {sqrt($xdiff * $xdiff + $ydiff * $ydiff)}]
}

# Returns the angle (in degrees) to the target coordinate from
# the opponent. Uses basic trig (arctangent).
proc getAngleToTarget { } {
    global computer_x computer_y target_x target_y

    set x [expr {$target_x - $computer_x}]
    set y [expr {$target_y - $computer_y}]

    set theta [expr {atan2(-$y,$x)}]
    if {$theta < 0} {
	set theta [expr {2*3.141592654 + $theta}]
    }
    
    return [expr {$theta * 180/3.141592654}]
}

