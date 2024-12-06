package com.rccode.pdfinvoiceboot.controller;

import com.rccode.pdfinvoiceboot.dto.InvoiceDto;
import com.rccode.pdfinvoiceboot.model.Invoice;
import com.rccode.pdfinvoiceboot.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody InvoiceDto invoice) {
        return invoiceService.create(invoice.getUserId(), invoice.getAmount());
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return invoiceService.findAll();
    }
}
