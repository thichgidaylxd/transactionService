package com.example.transactionService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/webhooks/sepay")
@RequiredArgsConstructor
public class SepayWebhookController {

    private final SepayService sepayService;

    @Value("${sepay.api-key}")
    private String apiKey;

    @PostMapping
    public ResponseEntity<Map<String, Object>> receiveWebhook(
            @RequestHeader("Authorization") String authorization,
            @RequestBody SepayWebhookRequest request) {

        // verify API key
        if (!authorization.equals("Apikey " + apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        sepayService.handleWebhook(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}