# Penguin Warrior game script (Tcl).
# A first attempt.

proc playComputer { } {
    global computer_x computer_y computer_angle computer_accel
    global player_x player_y player_angle player_accel

    # Global constants. These are initially set by InitScripting().
    global world_width world_height
    global player_forward_thrust player_reverse_thrust

    # Find our distance from the player.
    set distance [getDistanceToPlayer]

    # If we're close enough to the player, fire away!
    if {$distance < 200} {
	fireWeapon
    }

    # Figure out the quickest way to aim at the player.
    set target_angle [getAngleToPlayer]
    set arc [expr {$target_angle - $computer_angle}]
    if {$arc < 0} {
	set arc [expr {$arc + 360}]
    }

    # Turn 15 degrees at a time, same as the player.
    if {$arc < 180} {
	set computer_angle [expr {$computer_angle + 15}]
    } else {
	set computer_angle [expr {$computer_angle - 15}]
    }

    # Apply a reasonable amount of thrust.
    set computer_accel 5

    # That's it! Exit from Tcl_Eval and go back to the C-based engine.
}

# Returns the distance (in pixels) between the player and the opponent.
# This is just the Pythagorean formula.
proc getDistanceToPlayer { } {
    global computer_x computer_y player_x player_y

    set xdiff [expr {$computer_x - $player_x}]
    set ydiff [expr {$computer_y - $player_y}]

    return [expr {sqrt($xdiff * $xdiff + $ydiff * $ydiff)}]
}

# Returns the angle (in degrees) to the player from
# the opponent. Uses basic trig (arctangent).
proc getAngleToPlayer { } {
    global computer_x computer_y player_x player_y

    set x [expr {$player_x - $computer_x}]
    set y [expr {$player_y - $computer_y}]

    set theta [expr {atan2(-$y,$x)}]
    if {$theta < 0} {
	set theta [expr {2*3.141592654 + $theta}]
    }
    
    return [expr {$theta * 180/3.141592654}]
}

