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
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.grid.Grid;



import java.util.ArrayList;


/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private int courage;
    private static final double DARKENING_FACTOR = 0.05;

    public BlusterCritter(int c) {
        courage = c;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        Location loc = getLocation();
        int c = loc.getCol(), r = loc.getRow();
        Location l;
        Grid gr = getGrid();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                l = new Location(r + i, c + j);
                if (gr.isValid(l)) {
                    if (gr.get(l) instanceof Critter) {
                        neighbors.add(getGrid().get(l));
                    }
                }
            }
        }
        return neighbors;

    }

    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        double rate = (n >= courage) ? DARKENING_FACTOR : -1 * DARKENING_FACTOR;
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - rate));
        int green = (int) (c.getGreen() * (1 - rate));
        int blue = (int) (c.getBlue() * (1 - rate));
        blue = (blue > 255) ? 255 : blue;
        red = (red > 255) ? 255 : red;
        green = (green > 255) ? 255 : green;

        setColor(new Color(red, green, blue));
    }
}
