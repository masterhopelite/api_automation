import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.payload;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if Add Api is working fine or not
		//given ,when and then
		
		//given --> all input details
		//when --> submit the api ,resource and http method
		//then --> validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")		
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract()
		.response()	.asString()	;
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		
		// Add place  --> update place with new adress --> get place to validatae if new address is present or not	
	}

}
