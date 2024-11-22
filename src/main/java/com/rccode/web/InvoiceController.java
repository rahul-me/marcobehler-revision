package com.rccode.web;

import com.rccode.dto.InvoiceDto;
import com.rccode.model.Invoice;
import com.rccode.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
