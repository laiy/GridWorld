import static org.junit.Assert.*;
import org.junit.Test;

public class JumperTest {
    public Jumper jumper = new Jumper();
    public ActorWorld world = new ActorWorld();

    @Before
    public void init() {
        world.add(new Location(0, 0), jumper);
    }

    @Test
    public void testcase1() {
    	jumper.moveTo(new Location(0, 0));
        jumper.setDirection(0);
        jumper.act();
        assertEquals(45, jumper.getDirection());
        // assertEquals(new Location(0, 0), jumper.getLocation());
    }
}

