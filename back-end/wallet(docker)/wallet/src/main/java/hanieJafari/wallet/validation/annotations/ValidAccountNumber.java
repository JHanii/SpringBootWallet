package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.AccountNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation definition
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountNumberValidator.class)
public @interface ValidAccountNumber {
    String message() default "Account number must be exactly 13 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}