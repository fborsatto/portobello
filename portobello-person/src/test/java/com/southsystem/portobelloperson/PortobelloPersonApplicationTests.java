package com.southsystem.portobelloperson;

import com.southsystem.portobelloperson.model.Person;
import com.southsystem.portobelloperson.model.enumeration.PersonTypeEnum;
import com.southsystem.portobelloperson.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortobelloPersonApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	PersonRepository personRepository;

	@Test
	public void testCreatePerson() {
		Person person = new Person(1L, "Flavio Borsatto", PersonTypeEnum.PF, "1234567890");

		webTestClient.post().uri("/api/person")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(person), Person.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.name").isEqualTo("Flavio Borsatto");
	}

	@Test
	public void testGetAllPersons() {
		webTestClient.get().uri("/api/person")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Person.class);
	}

	@Test
	public void testGetSinglePerson() {
		Person person = personRepository.save(new Person(1L, "Flavio Borsatto", PersonTypeEnum.PF, "1234567890")).block();

		webTestClient.get()
				.uri("/api/person/{id}", Collections.singletonMap("id", person.getPersonId()))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(response ->
						Assertions.assertThat(response.getResponseBody()).isNotNull());
	}

}
