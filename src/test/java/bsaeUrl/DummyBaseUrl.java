package bsaeUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp() {

        spec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api").build();
    }
}
