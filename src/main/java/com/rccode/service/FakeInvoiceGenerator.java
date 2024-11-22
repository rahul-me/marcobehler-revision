package com.rccode.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Profile("-development")
public class FakeInvoiceGenerator {

    private final InvoiceService invoiceService;

    public FakeInvoiceGenerator(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Creating dev invoices...");
        invoiceService.create("someUserId", 50);
        invoiceService.create("someOtherUserId", 100);
    }
}
