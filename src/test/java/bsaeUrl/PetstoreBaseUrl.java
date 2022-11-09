package bsaeUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetstoreBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
    }
}
