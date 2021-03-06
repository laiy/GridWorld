package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import info.gridworld.actor.Rock;


import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    public Location next;
    public Location last;
    public Location origin;
    public boolean isEnd = false;
    public Stack<Location> crossLocation = new Stack<Location>();
    public Integer stepCount = 0;
    boolean goBack = false;
    boolean hasShown = false;//final message has been shown
    boolean firstTime = true;

    int directionCounts[] = new int[4];

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
        for (int i = 0; i < 4; i++) {
            directionCounts[i] = 0;
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
    	if (firstTime) {
    		origin = getLocation();
    		firstTime = false;
    	}
	    boolean willMove = canMove();
        if (isEnd == true) {
        //to show step count when reach the goal        
            if (hasShown == false) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove || goBack) {
        	if (goBack) {
        		goBack = false;
        		next = origin;
        	}
            last = getLocation();
            crossLocation.push(next);
            move();
            //increase step count when move 
            stepCount++;
        } else {
            next = last;
            Location currentLocation = getLocation();
            int direction = currentLocation.getDirectionToward(next);
            switch (direction) {
                case 0:
                    directionCounts[2]--;
                    break;
                case 90:
                    directionCounts[3]--;
                    break;
                case 180:
                    directionCounts[0]--;
                    break;
                case 270:
                    directionCounts[1]--;
                    break;
            }

            stepCount++;
            crossLocation.pop();
            if (crossLocation.size() > 2) {
            	Location temp = crossLocation.pop();
            	last = crossLocation.peek();
            	crossLocation.push(temp);
	        } else {
	        	goBack = true;
	        }
	        move();
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return null;
        ArrayList<Location> valid = new ArrayList<Location>();

        for (int i = 0; i < 4; i++) {
            Location l = loc.getAdjacentLocation(getDirection() + i * Location.RIGHT);
            if (gr.isValid(l)) {
                Actor a = gr.get(l);
                if (a instanceof Rock && a.getColor().getRed() == Color.RED.getRed()) {
                    isEnd = true;
                    break;
                } else if (a == null) {
                    valid.add(l);
                }
            }
        }
        
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        ArrayList<Location> toMove = getValid(getLocation());
        if (toMove.size() == 0 || isEnd == true) {
            return false;
        } else {
            int directionWithMaxSteps = 0, steps;
            Location currentLocation = getLocation();
            boolean validDirection[] = new boolean[4];
            for (Location loc : toMove) {
                int direction = loc.getDirectionToward(currentLocation);

                switch (direction) {
                    case 0:
                        steps = directionCounts[2];
                        validDirection[2] = true;
                        directionWithMaxSteps = steps > directionWithMaxSteps ? steps : directionWithMaxSteps;
                        break;
                    case 90:
                        steps = directionCounts[3];
                        validDirection[3] = true;
                        directionWithMaxSteps = steps > directionWithMaxSteps ? steps : directionWithMaxSteps;
                        break;
                    case 180:
                        steps = directionCounts[0];
                        validDirection[0] = true;
                        directionWithMaxSteps = steps > directionWithMaxSteps ? steps : directionWithMaxSteps;
                        break;
                    case 270:
                        steps = directionCounts[1];
                        validDirection[1] = true;
                        directionWithMaxSteps = steps > directionWithMaxSteps ? steps : directionWithMaxSteps;
                        break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (directionCounts[i] == directionWithMaxSteps && validDirection[i]) {
                    int row = currentLocation.getRow();
                    int col = currentLocation.getCol();
                    switch (i) {
                        case 0:
                            next = new Location(row - 1, col);
                            directionCounts[0]++;
                            break;
                        case 1:
                            next = new Location(row, col + 1);
                            directionCounts[1]++;
                            break;
                        case 2:
                            next = new Location(row + 1, col);
                            directionCounts[2]++;
                            break;
                        case 3:
                            next = new Location(row, col - 1);
                            directionCounts[3]++;
                            break;
                    }
                    break;
                }
            }

            return true;
        }
    }
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        if (gr.isValid(next)) {
        	if (next != origin) {
	            setDirection(getLocation().getDirectionToward(next));
	            moveTo(next);
        	} else {
        		moveTo(origin);
        	}
        } else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
