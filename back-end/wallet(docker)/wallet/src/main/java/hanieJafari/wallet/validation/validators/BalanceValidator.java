package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.validation.annotations.ValidBalance;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BalanceValidator implements ConstraintValidator<ValidBalance, BigDecimal> {

    private static final BigDecimal MIN_BALANCE = new BigDecimal("10000");

    @Override
    public boolean isValid(BigDecimal balance, ConstraintValidatorContext context) {
        // Check if the balance is null
        if (balance == null) {
            return false;
        }
        // Check if the balance is at least 10,000
        return balance.compareTo(MIN_BALANCE) >= 0;
    }
}