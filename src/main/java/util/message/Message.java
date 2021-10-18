package util.message;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {
    private MessageHeader header;
    private MessageBody body;

	public Message(boolean b, int i, String method, ArrayList<Object> params) {
		RequestHeader requestHeader = new RequestHeader(b, i, method);
		RequestBody requestBody = new RequestBody(params);

		this.header = new MessageHeader(1, true, 0, 0);
		this.body = new MessageBody(requestHeader, requestBody, null, null);
	}
}
