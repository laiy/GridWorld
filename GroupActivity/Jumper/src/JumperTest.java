import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import org.junit.BeforeClass;
import org.junit.Test;


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
}

