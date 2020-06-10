package pl.edu.pjwstk.poj.validator;

import org.apache.commons.beanutils.BeanUtils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstField = BeanUtils.getProperty(value, firstFieldName);
            final Object secondField = BeanUtils.getProperty(value, secondFieldName);
            return firstField == null && secondField == null || firstField != null && firstField.equals(secondField);
        } catch (final Exception ignore) {}
        return true;
    }
}
