import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import org.junit.BeforeClass;
import org.junit.Test;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Bug;




public class JumperTest {
    public static Jumper jumper = new Jumper();
    public static ActorWorld world = new ActorWorld();

    @BeforeClass
    public static void init() {
        world.add(new Location(0, 0), jumper);
    }

    @Test
    public void testcase1() {
    	jumper.moveTo(new Location(0, 0));
        jumper.setDirection(0);
        jumper.act();
        assertEquals(45, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
    }

    @Test
    public void testcase2() {
        jumper.moveTo(new Location(0, 0));
        jumper.setDirection(90);
        Flower f = new Flower();
        world.add(new Location(0, 2), f);
        jumper.act();
        assertEquals(135, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
        f.removeSelfFromGrid();
        jumper.setDirection(90);
        Rock r = new Rock();
        world.add(new Location(0, 2), r);
        jumper.act();
        assertEquals(135, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
        r.removeSelfFromGrid();
    }

    @Test
    public void testcase3() {
        jumper.moveTo(new Location(0, 0));
        jumper.setDirection(0);
        jumper.act();
        assertEquals(45, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
    }

    @Test
    public void testcase4() {
        jumper.moveTo(new Location(0, 0));
        jumper.setDirection(90);
        Bug b = new Bug();
        world.add(new Location(0, 2), b);
        jumper.act();
        assertEquals(135, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
        b.removeSelfFromGrid();
    }9=,cdefgjmrt`

    @Test
    public void testcase5() {
        jumper.moveTo(new Location(0, 0));
        jumper.setDirection(90);
        Bug j = new Jumper();
        world.add(new Location(0, 2), j);
        jumper.act();
        assertEquals(135, jumper.getDirection());
        assertEquals(new Location(0, 0), jumper.getLocation());
        j.removeSelfFromGrid();
    }
}

