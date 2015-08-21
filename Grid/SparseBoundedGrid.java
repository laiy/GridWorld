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
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;



/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] occupantArray; // the array storing the grid elements
    private int _rows;
    private int _cols;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        _rows = rows;
        _cols = cols;
        occupantArray = new SparseGridNode[rows];
    }

    public int getNumRows()
    {
        return _rows;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return _cols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        while (node != null) {
            if (node.getCol() == col) {
                return (E)node.getOccupant();
            }
            node = node.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        boolean locationNodeExisted = false;
        while (node != null) {
            if (node.getNext().getCol() == col) {
                node.getNext().setOccupant(obj);
                locationNodeExisted = true;
                break;
            }
            node = node.getNext();
        }
        node = occupantArray[row];
        if (!locationNodeExisted) {
            if (node == null) {
                SparseGridNode n = new SparseGridNode(obj, col, null);
                occupantArray[row] = n;
            } else {
                if (col < node.getCol()) {
                    SparseGridNode n = new SparseGridNode(obj, col, node);
                    occupantArray[row] = n;
                } else {
                    SparseGridNode pre = node;
                    node = node.getNext();
                    while (col > node.getCol()) {
                        pre = pre.getNext();
                        node = node.getNext();
                    }
                    SparseGridNode n = new SparseGridNode(obj, col, node);
                    pre.setNext(n);
                }
            }
        }

        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        if (node != null) {
            if (node.getCol() == col) {
                occupantArray[row] = node.getNext();
            } else {
                SparseGridNode pre = node;
                node = node.getNext();
                while (col != node.getCol()) {
                    pre = pre.getNext();
                    node = node.getNext();
                }
                pre.setNext(node.getNext());
            }
        }

        return r;
    }
}
