package hanieJafari.wallet.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hanieJafari.wallet.service.AccountService;
import hanieJafari.wallet.validation.annotations.ValidIranianMobileNumber;
import hanieJafari.wallet.validation.annotations.ValidMilitaryService;
import hanieJafari.wallet.validation.annotations.ValidNationalCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "app_user")
@ValidMilitaryService
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        @ValidNationalCode
        private String nationalID;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        @JsonManagedReference
        private List<Account> accounts;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false)
        @ValidIranianMobileNumber
        private String mobilePhoneNumber;

        @Column(nullable = false)
        private LocalDate birthDate;

        @Column(nullable = false)
        private String gender;

        @Column(nullable = false)
        private Boolean militaryServiceStatement;

        @Column(unique = true, nullable = false)
        @Email
        private String email;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        // Getters and Setters
        public String getNationalID() {
                return nationalID;
        }

        public void setNationalID(String nationalID) {
                this.nationalID = nationalID;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getMobliePhoneNumber() {
                return mobilePhoneNumber;
        }

        public void setMobliePhoneNumber(String mobilePhoneNumber) {
                this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public LocalDate getBirthDate() {
                return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public Boolean getMilitaryServiceStatement() {
                return militaryServiceStatement;
        }

        public void setMilitaryServiceStatement(Boolean militaryServiceStatement) {
                this.militaryServiceStatement = militaryServiceStatement;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void addAccounts(Account account){this.accounts.add(account);}

}
