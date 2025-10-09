import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TestMongo {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        String dbName = "FootSimDB";

        try (MongoClient client = MongoClients.create(uri)) {
            MongoDatabase db = client.getDatabase(dbName);
            System.out.println("✅ Successfully connected to MongoDB!");
            System.out.println("📂 Active Database: " + db.getName());

            // --- Add this ---
            MongoCollection<Document> collection = db.getCollection("testCollection");
            Document sample = new Document("message", "Hello, MongoDB!")
                    .append("status", "connected");
            collection.insertOne(sample);
            System.out.println("📄 Inserted a test document into 'testCollection'.");

            System.out.println("🚀 MongoDB connection is up and running on localhost:27017");
        } catch (Exception e) {
            System.out.println("❌ Connection failed! Check if MongoDB service is running.");
            e.printStackTrace();
        }
    }
}
