package lk.himash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.himash.entity.User;

@RestController
@RequestMapping("/api/publisher")
public class UserController {

	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "himash1";
	
	@GetMapping("/publish/{username}")
	public String publishMessage(@PathVariable String username) {
		template.send(topic, username + " added successfully...!");
		return "Data published";
	}
	
	@GetMapping("/getUser")
	public String publishMessage() {
		User user = new User(0001, "User01", new String[] { "43/f/a", "T.B Jaya Road", "Colombo 04" });
		template.send(topic, user);
		return "Json Data published";
	}
	
}
