package org.ingomohr.bsarator.service.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TestBSAratorAdapterResource {

	@Test
	public void testAdaptSimpleEndpoint() {
		// @formatter:off
        given()
          .when().get("/bsarator-service/adapt/simple/hello")
          .then()
             .statusCode(200)
             .body(is("HelLo"));
    	// @formatter:on
	}

}
