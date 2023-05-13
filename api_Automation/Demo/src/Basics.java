import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.ReUsableMethods;
import Files.payload;


public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//validate if Add Api is working fine or not
		//given ,when and then
		
		//given --> all input details
		//when --> submit the api ,resource and http method
		//then --> validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")		
		//.body(payload.AddPlace())
				//content of the file to String-> content of file can convert into Byte --> Byte data to String
				
		.body(new String(Files.readAllBytes(Paths.get("address.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract()
		.response()	.asString()	;
		
		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing json
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		
		// Add place  --> update place with new adress --> get place to validatae if new address is present or not	
		String newAddress ="Summer Walk, Africa";
//		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").

//		body()
//		.when().put("maps/api/place/add/json")
//		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//GET Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1= ReUsableMethods.rawToJson(getPlaceResponse);
		//JsonPath js1= new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");
		 
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
