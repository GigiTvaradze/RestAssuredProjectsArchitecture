package InProgress;

public class payload {
    public static String AddPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }


    public static String UpdateAddress(){
        return "{\n" +
                "  \"place_id\": \"1e5b62a2d8654b408f391d106a6b7360\",\n" +
                "  \"address\": \"44 winter walk, USA\",\n" +
                "  \"key\": \"qaclick123\"\n" +
                "}";
    }
}
