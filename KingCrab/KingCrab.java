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
import info.gridworld.grid.Location;

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
            int row = currentLocation.getRow(), col = currentLocation.getCol();
            Location aLocation = a.getLocation();
            ArrayList<Location> validToMove = getGrid().getEmptyAdjacentLocations(aLocation);
            ArrayList<Location> cantMoveTo = new ArrayList<Location>();
            cantMoveTo.add(currentLocation);
            cantMoveTo.add(new Location(row, col));
            cantMoveTo.add(new Location(row, col));
            int[] dirs =
                { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT, Location.LEFT, Location.RIGHT };
            for (Location loc : getLocationsInDirections(dirs))
            {
                cantMoveTo.add(loc);
            }
            boolean moved = false;
            for (Location l : validToMove) {
                boolean go = true;
                for (Location badL : cantMoveTo) {
                    if (l == badL) {
                        go = false;
                        break;
                    }
                }
                if (go) {
                    a.moveTo(l);
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                a.removeSelfFromGrid();
            }
        }
    }
}
