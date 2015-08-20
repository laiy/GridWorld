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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            Location currentLocation = getLocation();
            int col = currentLocation.getCol();
            int row = currentLocation.getRow();
            int x = (a.getLocation().getCol() - col == 1) ? 2 : -2;
            if (getGrid().isValid(new Location(row, col + x))) {
                if (getGrid().get(new Location(row, col + x)) == null) {
                    a.moveTo(new Location(row, col + x));
                } else {
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
