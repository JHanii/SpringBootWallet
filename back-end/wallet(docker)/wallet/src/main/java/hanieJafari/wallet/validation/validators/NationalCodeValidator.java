package hanieJafari.wallet.validation.validators;

import hanieJafari.wallet.validation.annotations.ValidNationalCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NationalCodeValidator implements ConstraintValidator<ValidNationalCode, String> {

    @Override
    public boolean isValid(String nationalNo, ConstraintValidatorContext context) {
        int[] MULT = {29, 27, 23, 19, 17, 29, 27, 23, 19, 17};
        int length = nationalNo.length();
        if (StringUtils.isNumeric(nationalNo) && length == 10) {
            char[] chars = nationalNo.toCharArray();
            int checkDigit = Character.getNumericValue(chars[length - 1]);
            int delta = 0;
            int tensPlusTwo = Character.getNumericValue(chars[length - 2]) + 2;
            for (int i = 0; i < length - 1; i++) {
                delta += ((Character.getNumericValue(chars[i]) + tensPlusTwo) * MULT[i]);
            }
            int remain = delta % 11;
            remain = remain == 10 ? 0 : remain;
            return remain == checkDigit;
        }
        return false;
    }
}
