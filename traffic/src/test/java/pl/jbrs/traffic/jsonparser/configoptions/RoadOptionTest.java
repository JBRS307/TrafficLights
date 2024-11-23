package pl.jbrs.traffic.jsonparser.configoptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// These enum tests are here to check for typos
// String values shall NOT be copied from class
public class RoadOptionTest {
    @Test
    public void toStringTest() {
        // given, when, then
        assertEquals("crosswalk", RoadOption.Crosswalk.toString());

        // given, when, then
        assertEquals("priority", RoadOption.Priority.toString());

        // given, when, then
        assertEquals("lanes", RoadOption.Lanes.toString());
    }

}