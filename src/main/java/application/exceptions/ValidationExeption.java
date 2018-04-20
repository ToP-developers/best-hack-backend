package application.exceptions;

import java.util.Map;

public class ValidationExeption extends RuntimeException {
    private Map<String, Boolean> validation;

    public ValidationExeption(Map<String, Boolean> message) {
        this.validation = message;
    }

    public Map<String, Boolean> getValidation() {
        return validation;
    }
}
