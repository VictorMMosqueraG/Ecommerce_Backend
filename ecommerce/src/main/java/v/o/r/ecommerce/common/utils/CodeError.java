package v.o.r.ecommerce.common.utils;

//NOTE: add new type the errors
public enum CodeError {
    MISSING_FILED("Missing Field"),
    ALREADY_EXIST("Already Exist"),
    INTERNAL_SERVER_ERROR("Unexpected Error"),
    ERROR_PARAMS("Error Params"),
    BAD_REQUEST("Bad Request")
    ;
    private final String message;

    CodeError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
