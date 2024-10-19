package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.models.User;
import hanieJafari.wallet.validation.annotations.ValidMilitaryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class MilitaryServiceValidator implements ConstraintValidator<ValidMilitaryService, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user.getBirthDate() == null || user.getGender() == null) {
            return true;
        }

        // Calculate the age from birthdate
        int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();

        // If the user is male and older than 18, militaryServiceStatement must not be null
        if ("male".equalsIgnoreCase(user.getGender()) && age > 18) {
            return user.getMilitaryServiceStatement() != null;
        }

        // For other cases
        return true;
    }
}