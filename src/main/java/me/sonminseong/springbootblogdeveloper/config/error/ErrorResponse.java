package me.sonminseong.springbootblogdeveloper.config.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    /**
     * ErrorAttributes를 대체할 에러 메시지용 객체
     * 에러 메시지가 포함된 message 필드와 고유 에러 코드인 code필드를 가지고 있다.
     * ErrorResponse객체를 사용하면 다음 형식의 JSON 응답을 받게 될 것이다.
     * {
     *     "message" : "존재하지 않는 엔티티입니다.",
     *     "code" : "E4"
     * }
     */

    private String message;
    private String code;

    private ErrorResponse(final ErrorCode code){
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public ErrorResponse(final ErrorCode code, final String message){
        this.message = message;
        this.code = code.getCode();
    }

    public static ErrorResponse of(final ErrorCode code){
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final String message){
        return new ErrorResponse(code, message);
    }
}
