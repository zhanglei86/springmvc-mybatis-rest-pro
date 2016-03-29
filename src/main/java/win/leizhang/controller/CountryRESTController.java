package win.leizhang.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import win.leizhang.model.datasource1.TCountry;
import win.leizhang.service.datasource1.CountryService;

@RequestMapping("country")
@RestController
public class CountryRESTController {

	private static final Logger logger = Logger.getLogger(CountryRESTController.class);

	@Autowired
	private CountryService countryService;

	// select all
	// produces = "application/json;charset=UTF-8"
	@RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	// @ResponseBody
	public ResponseEntity<List<TCountry>> selectAll() {
		List<TCountry> tcountryList = countryService.selectAll();
		if (tcountryList.isEmpty()) {
			logger.info("Fetching All with is not found!");
			return new ResponseEntity<List<TCountry>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TCountry>>(tcountryList, HttpStatus.OK);
	}

	// select one
	@RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<TCountry> selectOne(@PathVariable("id") int id) {
		TCountry tCountry = countryService.selectByKey(id);
		if (tCountry == null) {
			logger.info("Fetching One with id:" + id + " is not found!");
			return new ResponseEntity<TCountry>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TCountry>(tCountry, HttpStatus.OK);
	}

	// create
	@RequestMapping(value = "", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<Void> createOne(@RequestBody TCountry tcountry, UriComponentsBuilder ucBuilder) {
		logger.info("Creating one: " + tcountry.getCountryname());

		if (countryService.isExist(tcountry.getCountryname()) == true) {
			logger.info("one with name" + tcountry.getCountryname() + "already exist!");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		int i = countryService.save(tcountry);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/country/" + "{id}").buildAndExpand(tcountry.getId()).toUri());
		return new ResponseEntity(i, headers, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "{id}", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<TCountry> updateOne(@PathVariable("id") int id, @RequestBody TCountry tcountry) {
		logger.info("Fetching&Updating One with id:" + id);
		TCountry currentObj = countryService.selectByKey(id);
		if (currentObj == null) {
			logger.info("One with id:" + id + " is not found!");
			return new ResponseEntity<TCountry>(HttpStatus.NOT_FOUND);
		}
		currentObj.setCountryname(tcountry.getCountryname());
		currentObj.setCountrycode(tcountry.getCountrycode());
		int i = countryService.updateAll(currentObj);
		return new ResponseEntity(i, HttpStatus.OK);
	}

	// delete
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<TCountry> deleteOne(@PathVariable("id") int id) {
		logger.info("Fetching&Deleting One with id:" + id);
		TCountry currentObj = countryService.selectByKey(id);
		if (currentObj == null) {
			logger.info("One with id:" + id + " is not found!");
			return new ResponseEntity<TCountry>(HttpStatus.NOT_FOUND);
		}
		int i = countryService.delete(id);
		return new ResponseEntity(i, HttpStatus.OK);
	}

	// isExist
	@RequestMapping(value = "isExist/{name}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity selectOther(@PathVariable("name") String name) {
		boolean bl = countryService.isExist(name);
		return new ResponseEntity(bl, HttpStatus.OK);
	}

}
