package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class MessageBody implements Serializable {
    private RequestHeader requestHeader;
    private RequestBody requestBody;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;
}
