package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.ShebaNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define the custom annotation
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShebaNumberValidator.class)
public @interface ValidShebaNumber {
    String message() default "Sheba number must start with 'IR' and have 24 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}