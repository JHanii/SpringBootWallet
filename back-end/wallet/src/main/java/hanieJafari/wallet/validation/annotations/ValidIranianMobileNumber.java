package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.IranianMobileNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define the custom annotation for Iranian mobile number validation
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IranianMobileNumberValidator.class)
public @interface ValidIranianMobileNumber {
    String message() default "Invalid Iranian mobile number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
