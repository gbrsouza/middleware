package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageHeader implements Serializable {
    private int version;
    private boolean byteOrder;
    private int messageType;
    private long messageSize;
}
