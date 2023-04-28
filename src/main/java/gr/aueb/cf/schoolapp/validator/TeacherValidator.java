package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherDTO teacherDTO = (TeacherDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (teacherDTO.getFirstname().length() < 3 || teacherDTO.getFirstname().length() > 60) {
            errors.rejectValue("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (teacherDTO.getLastname().length() < 3 || teacherDTO.getLastname().length() > 50) {
            errors.rejectValue("lastname", "size");
        }
    }
}
