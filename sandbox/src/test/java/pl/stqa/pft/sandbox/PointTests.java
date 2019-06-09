package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point (1,2);
        Point p2 = new Point (4,4);
        Assert.assertEquals(p1.distance(p2), 3.605551275463989);

    }
}
