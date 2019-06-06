package com.zisarknar.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
public class SpringRestClientApplication {
	
	static final String URL_BEERS = "https://api.punkapi.com/v2/beers";
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/beers")
	public String getBeers(Model model ) {
		
		try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<Beer[]> response = restTemplate.exchange(URL_BEERS, HttpMethod.GET,entity,Beer[].class);
            model.addAttribute("beers", response.getBody());
            return "index";

        } catch (Exception ex) {
           ex.printStackTrace();
        }
		return "index";
	}
	
	
	@GetMapping("/beers/{id}")
	public String getBeerById(@PathVariable("id") int id, Model model) {
		
		try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<Beer[]> response = restTemplate.exchange(URL_BEERS+"/"+id, HttpMethod.GET,entity,Beer[].class);
            model.addAttribute("beers",response.getBody());
            return "detail";

        } catch (Exception ex) {
           ex.printStackTrace();
        }
		return "detail";
	}
	
	
	@GetMapping("/beers/random")
	public String getBeerRandom() {
		
		try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<String> response = restTemplate.exchange(URL_BEERS+"/random", HttpMethod.GET,entity,String.class);
            return response.getBody();

        } catch (Exception ex) {
           ex.printStackTrace();
        }
		return "error";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientApplication.class, args);
		
	}

}
