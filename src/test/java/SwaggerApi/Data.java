package SwaggerApi;

public class Data {
    public static String data(String name1,String name2,String name3,String status){
       return  "{\n" +
               "  \"id\": 0,\n" +
               "  \"category\": {\n" +
               "    \"id\": 0,\n" +
               "    \"name\": \""+name1+"\"\n" +
               "  },\n" +
               "  \"name\": \""+name2+"\",\n" +
               "  \"photoUrls\": [\n" +
               "    \"string\"\n" +
               "  ],\n" +
               "  \"tags\": [\n" +
               "    {\n" +
               "      \"id\": 0,\n" +
               "      \"name\": \""+name3+"\"\n" +
               "    }\n" +
               "  ],\n" +
               "  \"status\": \""+status+"\"\n" +
               "}";
    }
    public static String store(String status){
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"petId\": 0,\n" +
                "  \"quantity\": 0,\n" +
                "  \"shipDate\": \"2022-04-07T19:15:57.417Z\",\n" +
                "  \"status\": \""+status+"\",\n" +
                "  \"complete\": true\n" +
                "}\n";
    }
    public static String user(String fname){
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"string\",\n" +
                "  \"firstName\": \""+fname+"\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
    }
}
