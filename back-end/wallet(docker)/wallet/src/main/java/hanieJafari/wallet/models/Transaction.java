package hanieJafari.wallet.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_statement_id", nullable = false)
    @JsonBackReference
    private AccountStatement accountStatement;

    @Column(nullable = false)
    private String type; // "DEPOSIT", WITHDRAWAL

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column
    private Boolean failed = false;

    public Boolean getFailed(){return this.failed;}

    public void setFailedOn() {
        this.failed = true;
    }
}
