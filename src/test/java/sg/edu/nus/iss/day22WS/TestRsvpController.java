package sg.edu.nus.iss.day22WS;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@SpringBootTest
@AutoConfigureMockMvc
class TestRsvpController {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldReturn200() {

		// Build the request
		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvps")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		// Call the controller
		MvcResult result = null;
		try {
			result = mvc.perform(req).andReturn();
		} catch (Exception ex) {
			fail("cannot perform mvc invocation", ex);
			return;
		}

		// Get response
		MockHttpServletResponse resp = result.getResponse();
		try {
			String payload = resp.getContentAsString();
			assertNotNull(payload);
		} catch (Exception ex) {
			fail("cannot retrieve response payload", ex);
			return;
		}
	}

	@Test
	public void ShouldReturnCorrectName() throws UnsupportedEncodingException {
		// Build the request
		RequestBuilder req = MockMvcRequestBuilders.get("/api/rsvp")
				.queryParam("name", "test")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		// Call the controller
		MvcResult result = null;
		try {
			result = mvc.perform(req).andReturn();
		} catch (Exception ex) {
			fail("cannot perform mvc invocation", ex);
			return;
		}

		// Get response
		MockHttpServletResponse resp = result.getResponse();
		
			String payload = resp.getContentAsString();
			InputStream is = new ByteArrayInputStream(payload.getBytes());
			JsonReader reader = Json.createReader(is);
			JsonArray arr = reader.readArray();
			String name = null;
			for(int i=0; i<arr.size(); i++){
				JsonObject obj = arr.getJsonObject(i);
				name = obj.getString("name");
			}

			assertEquals("test", name, "Expect %s RSVP. Got %s".formatted("test", name));
		
	}
}
