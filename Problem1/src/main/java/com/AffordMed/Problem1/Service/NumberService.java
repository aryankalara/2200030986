package com.AffordMed.Problem1.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Service
public class NumberService {

    @Value("${auth.token}")
    private String authToken;

    @Value("${api.prime.url}")
    private String primeApiUrl;

    @Value("${api.fibonacci.url}")
    private String fibonacciApiUrl;

    @Value("${api.even.url}")
    private String evenApiUrl;

    @Value("${api.random.url}")
    private String randomApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private final int WINDOW_SIZE = 10;
    private final Queue<Integer> slidingWindow = new LinkedList<>();
    private final Set<Integer> uniqueNumbers = new HashSet<>();

    public synchronized double getAverage() {
        // Fetch numbers from mock APIs
        int prime = fetchNumberFromApi(primeApiUrl);
        int fibonacci = fetchNumberFromApi(fibonacciApiUrl);
        int even = fetchNumberFromApi(evenApiUrl);
        int random = fetchNumberFromApi(randomApiUrl);

        // Add to sliding window and unique set
        addNumber(prime);
        addNumber(fibonacci);
        addNumber(even);
        addNumber(random);

        // Calculate average of unique numbers
        return uniqueNumbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    private void addNumber(int number) {
        slidingWindow.add(number);
        uniqueNumbers.add(number);

        if (slidingWindow.size() > WINDOW_SIZE) {
            int removed = slidingWindow.poll();

            // Remove from uniqueNumbers only if removed number no longer exists in the window
            if (!slidingWindow.contains(removed)) {
                uniqueNumbers.remove(removed);
            }
        }
    }

    private int fetchNumberFromApi(String url) {
        try {
            // Example: Pass auth token in header or query param if needed, for now, simple getForObject
            return restTemplate.getForObject(url, Integer.class);
        } catch (Exception e) {
            // For demo, fallback to 0 if API fails
            return 0;
        }
    }
}
