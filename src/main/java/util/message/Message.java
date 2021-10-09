package util.message;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {
    private MessageHeader header;
    private MessageBody body;
}
