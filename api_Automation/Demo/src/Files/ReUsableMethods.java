package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath rawToJson(String Response) {
		JsonPath js1= new JsonPath(Response);
		return js1;
	} 
}
