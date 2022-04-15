package isi.tn.elastic.controllers;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import isi.tn.elastic.models.LogData;
import isi.tn.elastic.services.LogDataService;

@RestController
@RequestMapping("/v1/logdata")
public class LogDataController {
	 @Autowired
	    private LogDataService logDataService;

	    @GetMapping
	    public List<LogData> searchLogDataByHost(@RequestParam("host") String host)
	    {
	        List<LogData> logDataList = logDataService.getAllLogDataForHost(host);

	        return logDataList;
	    }

	    @GetMapping("/search")
	    public List<LogData> searchLogDataByTerm(@RequestParam("term") String term)
	    {
	        return logDataService.findBySearchTerm(term);
	    }

	    @PostMapping
	    public LogData addLogData(@RequestBody LogData logData)
	    {

	        return logDataService.createLogDataIndex(logData);
	    }
	    
	    

	    @PostMapping("/createInBulk")
	    public  List<LogData> addLogDataInBulk(@RequestBody List<LogData> logDataList)
	    {
	        return (List<LogData>) logDataService.createLogDataIndices(logDataList);
	    }
	    
	    @GetMapping("/getHostCamunda")
	    public void getHost(@RequestParam("host") String host) throws ParseException
	    {
	    	String uri = "http://localhost:8080/engine-rest/user/"+ host +"/profile" ; 
	    	RestTemplate restTemplate = new RestTemplate(); 
	    	String result = restTemplate.getForObject(uri,String.class);
	    	
	        SimpleDateFormat sdf = 
	                new SimpleDateFormat("yyyy-MM-dd");
	            Date d = sdf.parse("2017-04-04"); 
	            java.sql.Date sqlStartDate = new java.sql.Date(d.getTime());

	    	LogData logData = new LogData("123",host,sqlStartDate,"yoyo",1.0,"ok");
	    	
	    	logDataService.createLogDataIndex(logData);
	    	System.out.println(result); 
	    	
	    }
}
