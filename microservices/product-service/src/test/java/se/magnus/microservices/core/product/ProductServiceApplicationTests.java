package se.magnus.microservices.core.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductServiceApplicationTests {

	@Autowired
	private WebTestClient client;

	@Test
	void getProductById() {
		int productId=1;

		client.get().uri("/product/"+productId)
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.productId").isEqualTo(productId);
	}

	@Test

	void getProductNotFound(){
		int productNotFound=13;
		client.get().uri("/product/"+productNotFound)
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product/"+productNotFound)
				.jsonPath("$.message")
					.isEqualTo("No product found for productId: "+productNotFound);
	}

	@Test
	void getProductInvalidParameterNegativeValue() {

		int productIdInvalid = -1;

		getAndVerifyProduct(productIdInvalid, UNPROCESSABLE_ENTITY)
				.jsonPath("$.path").isEqualTo("/product/" + productIdInvalid)
				.jsonPath("$.message").isEqualTo("Invalid productId: " + productIdInvalid);
	}

	@Test
	void getProductInvalidParameterString() {

		getAndVerifyProduct("/no-integer", BAD_REQUEST)
				.jsonPath("$.path").isEqualTo("/product/no-integer")
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

	private WebTestClient.BodyContentSpec getAndVerifyProduct(int productId, HttpStatus expectedStatus) {
		return getAndVerifyProduct("/" + productId, expectedStatus);
	}

	private WebTestClient.BodyContentSpec getAndVerifyProduct(String productIdPath, HttpStatus expectedStatus) {
		return client.get()
				.uri("/product" + productIdPath)
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(expectedStatus)
				.expectHeader().contentType(APPLICATION_JSON)
				.expectBody();
	}

}
