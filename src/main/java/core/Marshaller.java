package core;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.NoArgsConstructor;
import util.Message;

import java.io.StringReader;

/**
 * A MARSHALLER converts remote invocations into byte streams. The
 * MARSHALLER provides a generic mechanism that is not specific to any
 * particular remote object type.
 * The REQUESTOR, INVOKER, and REQUEST HANDLERS use the MARSHALLER
 * to retrieve the invocation information contained in the message byte
 * stream.
 */
@NoArgsConstructor
public class Marshaller {
    private static final Gson gson = new Gson();

    /**
     * Convert a message to JSON format
     * @param message the message
     * @return a string in JSON refer to received message
     */
    public String marshal(Message message){
        return gson.toJson(message);
    }

    /**
     * Convert a JSON file in a Message object
     * @param json the JSON file
     * @return A message object based on recived JSON
     */
    public Message unmarshal(String json){
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, Message.class);
    }

}
