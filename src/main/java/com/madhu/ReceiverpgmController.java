package com.madhu;


import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReceiverpgmController {
	
	static Logger log=Logger.getLogger(ReceiverpgmController.class);
	private String msg;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/receiver")
	public String receiver() throws JSONException
	{

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(" ", restTemplate.exchange("http://localhost:8081/sender", HttpMethod.GET,null,String.class).getBody());
		log.info("Sender message "+jsonObject);
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
	@RequestMapping("/reply/{message}")
	public String sender(@PathVariable String message)
	{
		log.info(message);
		msg=message;
		return message;
	}
	@RequestMapping("/sent")
	public String receiverReplay() 
	{
		log.info(msg);
		return msg;
	}
}

