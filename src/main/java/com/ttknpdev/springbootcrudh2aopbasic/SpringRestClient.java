package com.ttknpdev.springbootcrudh2aopbasic;

import com.ttknpdev.springbootcrudh2aopbasic.entity.Robot;
import com.ttknpdev.springbootcrudh2aopbasic.log.Logging;
import org.apache.log4j.Level;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  RestTemplate is a class within the Spring framework we will understand how to use RestTemplate for invoking REST APIs of different shapes.
  Once the above spring application is up and running,
  test all Spring rest APIs and logging results with the below Spring rest client
  (Making an HTTP GET Request to Obtain the JSON Response)
  to honestly we can use them(this class) instead Postman! ???
*/
public class SpringRestClient extends Logging {
    // there are request for http protocol
    private static final String GET_READS_ROBOT_ENDPOINT_URL = "http://localhost:8080/api/reads";
    private static final String GET_READ_ROBOT_ENDPOINT_URL = "http://localhost:8080/api/read/{id}";
    private static final String POST_CREATE_ROBOT_ENDPOINT_URL = "http://localhost:8080/api/create2";
    private static final String PUT_UPDATE_ROBOT_ENDPOINT_URL = "http://localhost:8080/api/update/{id}";
    private static final String DELETE_DELETE_ROBOT_ENDPOINT_URL = "http://localhost:8080/api/delete/{id}";
    /**
        RestTemplate is a synchronous client to perform HTTP requests.
        RestTemplate class is used to create applications that consume REST api
        RestTemplate adds the capability of transforming the request and response in JSON or XML to Java objects.
        RestTemplate provides higher-level methods for each of the HTTP methods which make it easy to invoke RESTful services.
        For example, the method getForObject() will perform a GET and return an object.
    */
    private final static RestTemplate restTemplate = new RestTemplate(); // can use @Autowired for creating Object

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();
        //  springRestClient.createRobot();
        // springRestClient.updateRobot();
        springRestClient.deleteRobot();
        //  springRestClient.readRobot();
        // springRestClient.readsRobot();
        // springRestClient.readsRobot2();
        /*
            2566-07-14 17:50:24 [main] DEBUG SpringRestClient:26 - result stores : Robot{id=2, name='RX-55', status='enable', price=950000.0, build='2566-07-14 17:50:23'} // create
            2566-07-14 17:50:24 [main] DEBUG SpringRestClient:34 - result stores : <202 ACCEPTED Accepted,[{"id":1,"name":"RX-66","status":"enable","price":750000.0,"build":"2566-07-14 17:45:03"},{"id":2,"name":"RX-55","status":"enable","price":950000.0,"build":"2566-07-14 17:50:23"}]
            ,[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 14 Jul 2023 10:50:24 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]> // reads
            run after run server
        */
    }

    private void createRobot(){
        Robot robot = new Robot("RX-91","enable",999999D);
        // postForObject() : creates a new resource using HTTP POST method and returns an entity
        // it accesses method in controller through url  (Send the request body in HttpEntity for HTTP POST request)
        Robot result = restTemplate.postForObject(POST_CREATE_ROBOT_ENDPOINT_URL,robot, Robot.class);
        springRestClient.log(Level.DEBUG,"result stores (createRobot) : "+result);
    }

    private void readRobot() {
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "4"); // key id value 1 for taking on path read/{id}
        // method getForObject() will perform a GET and return an object.
        Robot result = restTemplate.getForObject(GET_READ_ROBOT_ENDPOINT_URL, Robot.class,params); // it accesses method that i set through url
        // it shows same response like browser
        springRestClient.log(Level.DEBUG,"result stores (readRobot) : "+result);
    }

    private void updateRobot(){
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "4"); // key id value 1 for path read/{id}
        Robot upgrade = new Robot("RX-01","enable",1000000D);
        // put(): updates a resource for a given URL using the HTTP PUT method.
        restTemplate.put(PUT_UPDATE_ROBOT_ENDPOINT_URL, upgrade,params); // if you don't want response use .put(...)
        springRestClient.log(Level.DEBUG,"updated successfully");
    }

    private void deleteRobot(){
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "1"); // key id value 1 for path read/{id}
        restTemplate.delete(DELETE_DELETE_ROBOT_ENDPOINT_URL,params); //  if you don't want response use .delete(...)
        springRestClient.log(Level.DEBUG,"deleted successfully");
    }

    private void readsRobot(){
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        // Use HttpEntity to wrap the request object.
        HttpEntity < String > entity = new HttpEntity<>("parameters", headers);
        // Perfect method if you wanna use response of your rest-controller
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > result = restTemplate.exchange(GET_READS_ROBOT_ENDPOINT_URL, HttpMethod.GET, entity, String.class);
        springRestClient.log(Level.DEBUG,"result stores (readsRobot) : "+result);
        /*
        <202 ACCEPTED Accepted,
        [{"id":1,"name":"RX-66","status":"enable","price":750000.0,"build":"2566-07-14 17:45:03"},
        {"id":2,"name":"RX-55","status":"enable","price":950000.0,"build":"2566-07-14 17:50:23"},
        {"id":3,"name":"RX-75","status":"enable","price":660000.0,"build":"2566-07-14 18:53:27"},
        {"id":4,"name":"RX-01","status":"enable","price":1000000.0,"build":"2566-07-14 19:01:39"}],
        [Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 14 Jul 2023 15:59:21 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        */
    }

    private void readsRobot2(){
        // In this case , we can use getForEntity() instead exchange() for getting list info that map them to list
        ResponseEntity<List> response = restTemplate.getForEntity(GET_READS_ROBOT_ENDPOINT_URL, List.class);
        List<Robot> robots = response.getBody();
        springRestClient.log(Level.DEBUG,"robots stores (readsRobot2) : "+robots);
    }
}
