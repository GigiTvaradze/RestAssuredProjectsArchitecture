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

    public static String CoursePrice() {
        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    public  static String addBook(String aisle, String isbn){
        String payload = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";
        return payload;
    }

    public static String jiraCreateIssueData(String summaryText,String contentDescriptionText){
        String jiraCreateIssueData = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\": {\n" +
                "          \"key\": \"SCRUM\"\n" +
                "       },\n" +
                "       \"summary\": \""+summaryText+"\",\n" +
                "       \"description\": {\n" +
                "          \"type\": \"doc\",\n" +
                "          \"version\": 1,\n" +
                "          \"content\": [\n" +
                "              {\n" +
                "                  \"type\": \"paragraph\",\n" +
                "                  \"content\": [\n" +
                "                      {\n" +
                "                          \"type\": \"text\",\n" +
                "                          \"text\": \""+contentDescriptionText+"\"\n" +
                "                      }\n" +
                "                  ]\n" +
                "              }\n" +
                "          ]\n" +
                "       },\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}";
        return jiraCreateIssueData;
    }
}
