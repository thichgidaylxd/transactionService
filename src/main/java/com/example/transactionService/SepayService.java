package com.example.transactionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SepayService {

    private final FundTransactionRepository repository;

    public void handleWebhook(SepayWebhookRequest request) {

        // tránh duplicate khi SePay retry
        if (repository.findBySepayId(request.getId()).isPresent()) {
            return;
        }

        FundTransaction transaction = new FundTransaction();

        transaction.setSepayId(request.getId());
        transaction.setGateway(request.getGateway());
        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setCode(request.getCode());
        transaction.setContent(request.getContent());
        transaction.setTransferType(request.getTransferType());
        transaction.setTransferAmount(request.getTransferAmount());
        transaction.setAccumulated(request.getAccumulated());
        transaction.setSubAccount(request.getSubAccount());
        transaction.setReferenceCode(request.getReferenceCode());
        transaction.setDescription(request.getDescription());

        transaction.setTransactionDate(
                LocalDateTime.parse(
                        request.getTransactionDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );

        repository.save(transaction);
    }
}