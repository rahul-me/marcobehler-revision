package com.rccode.pdfinvoiceboot.repository;

import com.rccode.pdfinvoiceboot.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
