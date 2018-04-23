package com.my.pack;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;


public class Main {
    public static void main(String[] args) {
        // Create a cluster reference
        CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");

// Connect to the bucket and open it
        Bucket bucket = cluster.openBucket("default");

// Create a JSON document and store it with the ID "helloworld"
        JsonObject content = JsonObject.create().put("hello", "world");
        JsonDocument inserted = bucket.upsert(JsonDocument.create("helloworld", content));

// Read the document and print the "hello" field
        JsonDocument found = bucket.get("helloworld");
        System.out.println("Couchbase is the best database in the " + found.content().getString("hello"));

// Close all buckets and disconnect
        cluster.disconnect();
    }
}
