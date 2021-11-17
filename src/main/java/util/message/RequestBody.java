package util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class RequestBody {
    private ArrayList<Object> parameters;

    @Override
    public String toString() {
        String result = "RequestBody{ \n parameters=";
        for (var p : parameters){
            result += p.toString();
            result += " ";
        }
        result+="\n}";
        return result;
    }
}
