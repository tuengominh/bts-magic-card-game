package tech.bts.cardgame;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ReadGamesMongoDBExample {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> productsCol = database.getCollection("products");

        //Document bike = new Document().append("name", "bike").append("price", 100).append("available", true);
        //productsCol.insertOne(bike);

        Document query = new Document().append("name", "TV");
        Document update = new Document().append("$set", new Document("price", 150.0));
        productsCol.updateOne(query, update); //updateMany to change all Documents

        productsCol.updateOne(new Document().append("_id", new ObjectId("5ca1c81764ecd13eaf9f6610")), new Document().append("$set", new Document("price", 300.0)));

        Document condition = new Document("price", new Document("Sgt", 200.0)); //no need append if there is only 1 condition

        MongoCursor<Document> productsCursor = productsCol.find(condition).iterator();
        while(productsCursor.hasNext()){
            Document product = productsCursor.next();
            String name = product.getString("name");
            Double price = product.getDouble("price");
            Boolean available = product.getBoolean("available");
            System.out.println(name + ":" + price + ":" + available);
        }
    }
}
