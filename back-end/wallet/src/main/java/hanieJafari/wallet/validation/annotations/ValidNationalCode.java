package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.NationalCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NationalCodeValidator.class)
public @interface ValidNationalCode {

    String message() default "Invalid nationalCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
