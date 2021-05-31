package az.savey.ms.savey.model;

import az.savey.ms.savey.exception.StatusCode;
import lombok.Data;

@Data
public class BaseResponse {

    public BaseResponse() {
    }

    public BaseResponse(StatusCode status) {
        this.status = status;
    }

    StatusCode status = StatusCode.SUCCESS;
    Object data;
    String error;
}
