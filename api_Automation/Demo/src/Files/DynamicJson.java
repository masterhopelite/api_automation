package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json").body(payload.AddBook(isbn,aisle))
		.when().post("Library/Addbook.php") //resource
		.then().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	//deleteBook
//	@Test
//	public void deleteBook() {
//		
//	}
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{"ifdjf","4353"},{"fg","4354"},{"ff","4355"}};
	}
	
}
