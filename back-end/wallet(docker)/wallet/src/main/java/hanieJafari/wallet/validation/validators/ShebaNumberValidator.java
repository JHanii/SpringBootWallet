package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.validation.annotations.ValidShebaNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ShebaNumberValidator implements ConstraintValidator<ValidShebaNumber, String> {

    // Regular expression to match the format
    private static final String SHEBA_PATTERN = "^IR\\d{24}$";

    @Override
    public boolean isValid(String shebaNumber, ConstraintValidatorContext context) {
        // Check if the Sheba number is not null and matches the pattern
        return shebaNumber != null && shebaNumber.matches(SHEBA_PATTERN);
    }
}