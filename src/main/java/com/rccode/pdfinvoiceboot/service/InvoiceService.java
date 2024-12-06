package com.rccode.pdfinvoiceboot.service;

import com.rccode.pdfinvoiceboot.model.Invoice;
import com.rccode.pdfinvoiceboot.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(JdbcTemplate jdbcTemplate, UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }

    @Transactional
    public List<Invoice> findAll() {
        return jdbcTemplate.query("SELECT * FROM INVOICES", (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(rs.getObject("id").toString());
            invoice.setUserId(rs.getString("user_id"));
            invoice.setPdfUrl(rs.getString("pdf_url"));
            invoice.setAmount(rs.getInt("amount"));
            return invoice;
        });
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into invoices (user_id, pdf_url, amount) values (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);  //
            ps.setString(2, generatedPdfUrl);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);
        return invoice;
    }
}

