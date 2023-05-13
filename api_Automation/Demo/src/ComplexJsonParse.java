import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	
	public static void main(String[] args) {
	
		JsonPath js=new JsonPath(payload.CoursePrice());
		
		//print no of courses returned by api
		int count = js.getInt("courses.size()");  // size will give the count
		//System.out.println(count);
		
		//Print Purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		//System.out.println(totalAmount);
		
		//Print title of first index
		String titleFirstCourse  = js.get("courses[0].title");
		//System.out.println(titleFirstCourse);
		
		//print all titles with respective prices
		for(int i=0;i<count;i++) {
			String Title = js.get("courses["+i+"].title");
			//System.out.println(Title);
			
			int Price = js.getInt("courses["+i+"].price");
			//System.out.println(Price);
			//System.out.println(Title+"-->"+Price);
			
			//js.get("courses[i].price");
		}
		//no. of copies sold by Rpa
		for(int i=0;i<count;i++) {
			String Title = js.get("courses["+i+"].title");
			if(Title.equalsIgnoreCase("RPA")) {
				int copies = js.get("courses["+i+"].copies");
				//System.out.println(copies);
				break;
			}
		}
		int sum =0;
		for(int i=0;i<count;i++) {
			//int sum =0;
				int copies = js.get("courses["+i+"].copies");
			 sum =  sum + copies;
				//System.out.println(sum);
				break;
			
		}
		System.out.println(sum);
		 
	}
}
