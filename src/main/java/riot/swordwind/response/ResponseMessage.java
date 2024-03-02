package riot.swordwind.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ResponseMessage {
    OK("Success", HttpStatus.OK),
    NOT_FOUND("Requested resource not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("You are not authorized to access this resource", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus status;

    ResponseMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    // Getter 메서드
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    // HttpStatus에 따라 ErrorMessage를 반환하는 메서드
    public static ResponseMessage getMessageByStatus(HttpStatusCode status) {
        for (ResponseMessage responseMessage : ResponseMessage.values()) {
            if (responseMessage.getStatus() == status) {
                return responseMessage;
            }
        }
        return null; // 적절한 ErrorMessage가 없는 경우, null을 반환하거나 기본 값을 정의할 수 있습니다.
    }
}
