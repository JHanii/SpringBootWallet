package hanieJafari.wallet.models;

import com.fasterxml.jackson.annotation.*;
import hanieJafari.wallet.validation.annotations.ValidAccountNumber;
import hanieJafari.wallet.validation.annotations.ValidBalance;
import hanieJafari.wallet.validation.annotations.ValidShebaNumber;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    @JsonBackReference
    //@JsonIgnore
    private User user;

    @Column(nullable = false, unique = true)
    @ValidAccountNumber
    private String accountNumber;

    @Column(nullable = false)
    @ValidBalance
    private BigDecimal balance;

    @Column(nullable = false)
    private LocalDate accountCreationDate;

    @Column(nullable = false, unique = true)
    @ValidShebaNumber
    private String shebaNumber;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnore
    private AccountStatement accountStatement;

    // Getters and Setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getShebaNumber() {
        return shebaNumber;
    }

    public void setShebaNumber(String shebaNumber) {
        this.shebaNumber = shebaNumber;
    }

    public  AccountStatement getAccountStatement(){return accountStatement;}
    public void setAccountStatement(AccountStatement accountStatement){this.accountStatement = accountStatement;}
}
