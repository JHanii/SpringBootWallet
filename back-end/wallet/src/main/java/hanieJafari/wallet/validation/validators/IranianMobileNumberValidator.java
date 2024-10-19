package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.validation.annotations.ValidIranianMobileNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IranianMobileNumberValidator implements ConstraintValidator<ValidIranianMobileNumber, String> {

    // Regular expression for valid Iranian mobile numbers
    private static final String IRAN_MOBILE_PATTERN = "^(\\+98|0098|09)\\d{9}$";

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        // Check if the mobile number is not null and matches the pattern
        return mobileNumber != null && mobileNumber.matches(IRAN_MOBILE_PATTERN);
    }
}
