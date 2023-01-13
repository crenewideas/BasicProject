package cn.pxl.hotel.constants;
//{
//  "mappings": {
//    "properties": {
//      "id": {
//        "type": "keyword"
//      },
//      "name":{
//        "type": "text",
//        "analyzer": "ik_max_word",
//        "copy_to": "all"
//      },
//      "address":{
//        "type": "keyword",
//        "index": false
//      },
//      "price":{
//        "type": "integer"
//      },
//      "score":{
//        "type": "integer"
//      },
//      "brand":{
//        "type": "keyword",
//        "copy_to": "all"
//      },
//      "city":{
//        "type": "keyword",
//        "copy_to": "all"
//      },
//      "starName":{
//        "type": "keyword"
//      },
//      "business":{
//        "type": "keyword"
//      },
//      "location":{
//        "type": "geo_point"
//      },
//      "pic":{
//        "type": "keyword",
//        "index": false
//      },
//      "all":{
//        "type": "text",
//        "analyzer": "ik_max_word"
//      }
//    }
//  }
//}
public class HotelConstants {
    public static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"price\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"brand\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"city\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"starName\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"business\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
