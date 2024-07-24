package InProgress.Section19ExcelIntegration;

import org.testng.annotations.Test;

import java.util.HashMap;

public class CreateJsonFromHashMap {


    @Test
            public void createJsonFromHashMap() {
        HashMap<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("First Name","Gigi");
        jsonAsMap.put("Second Name","Tvaradze");

        System.out.println(jsonAsMap);
    }
}
