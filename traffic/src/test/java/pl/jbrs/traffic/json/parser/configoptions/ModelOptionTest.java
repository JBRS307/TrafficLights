package pl.jbrs.traffic.json.parser.configoptions;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.json.parser.configoptions.ModelOption;

import static org.junit.jupiter.api.Assertions.*;
public class ModelOptionTest {
    @Test
    public void toStringTest() {
        ModelOption modelOption;
        String result;

        // given
        modelOption = ModelOption.StateLength;
        // when
        result = modelOption.toString();
        // then
        assertEquals("stateLength", result);

        // given
        modelOption = ModelOption.MinStateLength;
        // when
        result = modelOption.toString();
        // then
        assertEquals("minStateLength", result);

        // given
        modelOption = ModelOption.PriorityMultiplier;
        // when
        result = modelOption.toString();
        // then
        assertEquals("priorityMultiplier", result);

        // given
        modelOption = ModelOption.YellowTime;
        // when
        result = modelOption.toString();
        // then
        assertEquals("yellowTime", result);


    }
  
}