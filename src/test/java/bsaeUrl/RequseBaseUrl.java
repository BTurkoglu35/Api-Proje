package bsaeUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RequseBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec=new RequestSpecBuilder().setBaseUri("https://reqres.in/api").build();
    }
}

