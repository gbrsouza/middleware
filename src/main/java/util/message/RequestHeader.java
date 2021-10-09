package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class RequestHeader implements Serializable {
    private boolean responseExpected;
    private int requestID;
    private String operation;
}
