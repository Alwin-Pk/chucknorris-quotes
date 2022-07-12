package de.htwberlin;

import de.htwberlin.service.GetQuoteService;
import de.htwberlin.web.api.QuoteResponse;
import de.htwberlin.web.api.QuoteRestController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuoteRestController.class)
class ChucknorrisQuotesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GetQuoteService service;

	@Test
	@DisplayName("Should reach the repo and retrieve quote")
	public void testGetRequest() throws Exception
	{
		int index = 1;
		var quotes = List.of(
				new QuoteResponse("Chuck Norris trägt keine Uhr. Er entscheidet wie spät es ist!"),
				new QuoteResponse("Chuck ist ein Norris"));

		doReturn(quotes.get(index).getQuote()).when(service).getQuote(index);

		this.mockMvc.perform(get("/api/v1/quotes/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName ("Should throw 404 error if quote is not found")
	public void test404Error() throws Exception
	{
		doReturn(null).when(service).getQuote(900);

		this.mockMvc.perform(get("/api/v1/quotes/900"))
				.andExpect(status().isNotFound());
	}
}
