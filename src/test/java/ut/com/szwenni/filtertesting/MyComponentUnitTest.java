package ut.com.szwenni.filtertesting;

import org.junit.Test;
import com.szwenni.filtertesting.api.RestApiIpComponent;
import com.szwenni.filtertesting.impl.RestApiIpComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        RestApiIpComponent component = new RestApiIpComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}