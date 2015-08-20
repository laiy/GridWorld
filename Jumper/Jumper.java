/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public Jumper()
    {
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (canMove())
        {
            move();
        }
        else
        {
            turn();
        }
    }

    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location last = next.getAdjacentLocation(getDirection());
        if (gr.isValid(last)) {
            moveTo(last);
        }
        else {
            removeSelfFromGrid();
        }
    }

    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location last = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(last)) {
            return false;
        }
        Actor neighbor = gr.get(last);
        return (neighbor == null);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
