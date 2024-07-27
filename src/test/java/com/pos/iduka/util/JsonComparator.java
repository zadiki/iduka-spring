package com.pos.iduka.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonComparator {

    public static boolean hasSameStructure(String json1, String json2,ObjectMapper objectMapper) throws Exception {
        JsonNode tree1 = objectMapper.readTree(json1);
        JsonNode tree2 = objectMapper.readTree(json2);

        String structure1 = createStructureWithNullValues(tree1,objectMapper).toString();
        String structure2 = createStructureWithNullValues(tree2,objectMapper).toString();

        try {
            JSONAssert.assertEquals(structure1, structure2, JSONCompareMode.STRICT_ORDER);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public static JsonNode createStructureWithNullValues(JsonNode node,ObjectMapper objectMapper) {
        if (node.isObject()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            node.fields().forEachRemaining(entry ->
                    objectNode.set(entry.getKey(), createStructureWithNullValues(entry.getValue(),objectMapper))
            );
            return objectNode;
        } else if (node.isArray()) {
            return objectMapper.createArrayNode().add(createStructureWithNullValues(node.get(0),objectMapper));
        } else {
            return null;
        }
    }
}
