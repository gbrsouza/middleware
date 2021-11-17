package communication.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private String httpCode;
    private String httpMessage;
    private String content;
}
