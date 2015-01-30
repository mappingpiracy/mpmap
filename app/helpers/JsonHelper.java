package helpers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 12/24/14.
 */
public class JsonHelper {

    public static List<Integer> arrayNodeToIntegerList(ArrayNode arrayNode) {
        List<Integer> integerList = new ArrayList<Integer>();
        for(JsonNode jsonNode : arrayNode) {
            integerList.add(jsonNode.asInt());
        }
        return integerList;
    }

    public static List<String> arrayNodeToStringList(ArrayNode arrayNode) {
        List<String> stringList = new ArrayList<String>();
        for(JsonNode jsonNode : arrayNode) {
            stringList.add(jsonNode.asText());
        }
        return stringList;
    }

}
