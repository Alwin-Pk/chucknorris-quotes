package de.htwberlin.web.api;

import de.htwberlin.service.GetQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteRestController
{
    private final GetQuoteService service;

    public QuoteRestController(GetQuoteService service) {this.service = service;}

    @GetMapping (path = "/api/v1/quotes/{index}")
    public ResponseEntity<QuoteResponse> fetchQuote(@PathVariable (name = "index") int index)
    {
            var quote = new QuoteResponse(service.getQuote(index));
            if (quote.getQuote() == null || quote.getQuote().isEmpty() || quote.getQuote().equals("null"))
            {
                return ResponseEntity.notFound().build();
            }
            else return ResponseEntity.ok(quote);
    }
}
