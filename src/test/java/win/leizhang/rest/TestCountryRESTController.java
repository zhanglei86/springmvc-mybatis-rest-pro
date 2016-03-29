package win.leizhang.rest;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import win.leizhang.model.datasource1.TCountry;

public class TestCountryRESTController {
	public static final String REST_SERVICE_URI = "http://localhost:8080/springmvc-mybatis-rest-pro/";

	public static void testRead() {
		System.out.println("Testing GetOne API,using ResponseEntity()-----------");
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<TCountry> requestEntity = new RequestEntity<TCountry>(headers, HttpMethod.GET,
				URI.create(REST_SERVICE_URI + "country/35"));
		ResponseEntity<TCountry> responseEntity = restTemplate.exchange(requestEntity, TCountry.class);
		System.out.println(responseEntity.getBody().getCountryname());
	}

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAll() {
		System.out.println("Testing GetAll API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> map = restTemplate.getForObject(REST_SERVICE_URI + "country/all",
				List.class);
		String json = JSON.toJSONString(map);
		System.out.println(json);
	}

	/* GET */
	private static void getOne() {
		System.out.println("Testing GetOne API----------");
		RestTemplate restTemplate = new RestTemplate();
		TCountry tcountry = restTemplate.getForObject(REST_SERVICE_URI + "country/35", TCountry.class);
		System.out.println(tcountry.getCountryname());
	}

	/* POST */
	private static void createOne() {
		System.out.println("Testing createOne API----------");
		RestTemplate restTemplate = new RestTemplate();
		String requestBody = "{\"countryname\":\"ZL35Country\",\"countrycode\":\"ZL35\"}";
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "country/", requestBody, TCountry.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateOne() {
		System.out.println("Testing updateOne API----------");
		RestTemplate restTemplate = new RestTemplate();

		// MultiValueMap<String, Object> headers = new
		// LinkedMultiValueMap<String, Object>();
		// headers.add("Accept", "application/json");
		// headers.add("Content-Type", "application/json");

		String requestBody = "{\"id\":228,\"countryname\":\"ZL228Country\",\"countrycode\":\"ZL228\"}";
		restTemplate.put(REST_SERVICE_URI + "/country/228", requestBody);
		System.out.println(requestBody);
	}

	/* DELETE */
	private static void deleteOne() {
		System.out.println("Testing deleteOne API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/country/230");
	}

	public static void main(String args[]) {
		testRead();
		listAll();
		getOne();
		//createOne();
		//updateOne();
		//deleteOne();
	}

}
