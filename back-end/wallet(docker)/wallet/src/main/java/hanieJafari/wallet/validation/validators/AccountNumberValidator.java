package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.validation.annotations.ValidAccountNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<ValidAccountNumber, String> {

    @Override
    public boolean isValid(String accountNumber, ConstraintValidatorContext context) {
        // Check if the account number is not null, has exactly 13 characters and is numeric
        return accountNumber != null && accountNumber.matches("\\d{13}");
    }
}