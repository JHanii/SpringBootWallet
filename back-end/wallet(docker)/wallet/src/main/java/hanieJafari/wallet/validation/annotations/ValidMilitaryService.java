package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.MilitaryServiceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MilitaryServiceValidator.class)
public @interface ValidMilitaryService {
    String message() default "Military service statement is required for males over 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
