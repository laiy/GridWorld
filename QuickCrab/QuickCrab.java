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

import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        Location currentLocation = getLocation();
        int r = currentLocation.getRow(), c = currentLocation.getCol();
        if (getGrid().isValid(new Location(r, c - 3)) && getGrid().isValid(new Location(r, c + 3))) {
            if (getGrid().get(new Location(r, c - 3)) == null && getGrid().get(new Location(r, c + 3)) == null) {
                locs.add(new Location(r, c - 3));
                locs.add(new Location(r, c + 3));
                return locs;
            }
        }
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                locs.add(loc);
            }
        }

        return locs;
    }

}
