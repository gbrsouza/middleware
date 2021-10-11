package core;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import util.message.Message;

import java.io.*;

/**
 * A MARSHALLER converts remote invocations into byte streams. The
 * MARSHALLER provides a generic mechanism that is not specific to any
 * particular remote object type.
 * The REQUESTOR, INVOKER, and REQUEST HANDLERS use the MARSHALLER
 * to retrieve the invocation information contained in the message byte
 * stream.
 */
@NoArgsConstructor
@Slf4j
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

    /**
     * Convert input stream received from sockets in a Message object
     * @param data the data
     * @return A Message object
     */
    public Message unmarshalFromSocket(InputStream data){
        try {
            var in = new ObjectInputStream(data);
            return (Message) in.readObject();
        } catch (IOException e) {
            log.error("Error to read received data");
            return null;
        } catch (ClassNotFoundException e) {
            log.error("Error for convert received data to Message object");
            return null;
        }
    }

    public byte[] marshalToSocket(Message message){
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(message);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            log.error("Error to serialize message object - returning null message...");
            return null;
        }

    }

}
