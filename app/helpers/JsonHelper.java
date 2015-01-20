package helpers;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 12/24/14.
 */
public class JsonHelper {

    public static List<Integer> arrayNodeToIntegerList(ArrayNode arrayNode) {
        List<Integer> integerList = new ArrayList<Integer>();
        for(int i = 0; i < arrayNode.size(); i++) {
            integerList.add(arrayNode.get(i).asInt());
        }
        return integerList;
    }

}
