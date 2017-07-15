package com.example.demo;

import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoSivyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSivyApplication.class, args);
	}

	@Autowired
	private MongoClient mongoClient;

	@RequestMapping("/sum")
	public int sum(@RequestParam int a, @RequestParam int b) {
		return a + b;
	}

	@RequestMapping
	public Map get() {
		final MongoCollection<Document> collection = mongoClient.getDatabase("enron").getCollection("messages");
		final Document document = collection.find(Document.class).limit(1).iterator().next();
		return document;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public void post(@RequestBody Map<String, Object> map) {
		final MongoCollection<Document> collection = mongoClient.getDatabase("alcohol").getCollection("beer");
		collection.insertOne(new Document(map));
	}
}
