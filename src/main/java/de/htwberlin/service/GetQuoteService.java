package de.htwberlin.service;

import org.springframework.stereotype.Service;

@Service
public class GetQuoteService implements QuotesService {

    private final QuotesService quoteRepository;

    public GetQuoteService(QuotesService quoteRepository) {this.quoteRepository = quoteRepository;}

    @Override
    public String getQuote(int index)
    {
        if (index <= quoteRepository.QUOTES.size())
        return quoteRepository.QUOTES.get(index);
        else throw new IllegalArgumentException();
    }
}
