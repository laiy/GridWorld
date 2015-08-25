/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */


import java.util.ArrayList;

import java.util.*;

import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private int size;
    private Object[][] occupantArray;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        size = 16;
        occupantArray = new Object[size][size];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (occupantArray[i][j] != null) {
                    a.add(new Location(i, j));
                }
            }
        }
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        if (loc.getRow() >= size || loc.getCol() >= size)
            return null;

        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");

        int row = loc.getRow();
        int col = loc.getCol();
        int tempSize = size;
        while (row >= tempSize || col >= tempSize) {
            tempSize *= 2;
        }
        if (tempSize != size) {
            Object[][] tempArray = new Object[tempSize][tempSize];
            for (int i = 0; i < size; i++) {
                System.arraycopy(occupantArray[i], 0, tempArray[i], 0, size);
            }
            size = tempSize;
            occupantArray = tempArray;
        }

        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");
         E oldOccupant = get(loc);
         occupantArray[loc.getRow()][loc.getCol()] = null;
         return oldOccupant;
    }

}
