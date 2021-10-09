package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ResponseHeader implements Serializable {
    private int requestID;
    private int responseStatus;
}
