package hanieJafari.wallet.validation.annotations;

import hanieJafari.wallet.validation.validators.BalanceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BalanceValidator.class)
public @interface ValidBalance {
    String message() default "Balance must be at least 10,000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
