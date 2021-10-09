package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ResponseBody implements Serializable {
    private Object operationResult;
}
