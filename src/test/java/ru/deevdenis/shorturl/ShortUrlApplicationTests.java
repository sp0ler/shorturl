package ru.deevdenis.shorturl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.deevdenis.shorturl.Controller.MainController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShortUrlApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static final long TIME_EXPIRED = 10000000L;
	private static final List<ShortUrlTest> shortUrlTestList = new ArrayList<>();

	public ObjectMapper objectMapper() { return new ObjectMapper(); }

	@Order(1)
	@Test
	void testSaveController() throws Exception {
		String jsonStringTest = "{\"text\":\"test\", \"role\":\"public\"}";

		MvcResult mvcResult = mockMvc.perform(
			post(MainController.SAVE, TIME_EXPIRED)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStringTest)
		).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		ShortUrlTest shortUrl = objectMapper().readValue(content, ShortUrlTest.class);
		shortUrlTestList.add(shortUrl);

		assertEquals(200, status);
	}

	@Order(2)
	@Test
	void testFetchController() throws Exception {
		ShortUrlTest addedShortUrl = shortUrlTestList.get(0);
		String id = addedShortUrl.getId();

		MvcResult mvcResult = mockMvc.perform(
			get(MainController.FETCH, id)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
		).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		ShortUrlTest newShortUrl = objectMapper().readValue(content, ShortUrlTest.class);

		assertEquals(200, status);
		assertEquals(addedShortUrl, newShortUrl, "It equal!");
	}

	@Order(3)
	@Test
	void testDeleteController() throws Exception {
		ShortUrlTest addedShortUrl = shortUrlTestList.get(0);
		String id = addedShortUrl.getId();

		MvcResult mvcResult = mockMvc.perform(
				delete(MainController.DELETE, id)
		).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(410, status);
	}
}
