package training;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    @Test
    public void getCategories(){
        String endpoint="http://localhost:80/api_testing/category/read.php";
        var response=given().auth().basic("root", "root")
                .when().get(endpoint).then();
        response.log().body();
    }
    @Test
    public void getProduct(){
        String endpoint="http://localhost:80/api_testing/product/read_one.php";
        var response1=given().auth().basic("root", "root")
                .queryParam("id", 3)
                .when().get(endpoint)
                .then();
        response1.log().body();
    }
    @Test
    public void createProduct(){
        String endpoint="http://localhost:80/api_testing/product/create.php";
        String body= """
                {
                "name": "Water Bottle",
                "description":"blue water bottle - Holds 1 litre",
                 "price" :12,
                 "category_id" : 17
                 }
                """;

        var response = given().body(body)
                .when().post(endpoint).then();
        response.log().body();

    }
    //update Price of category id =19 which was added above
    @Test
    public void updateProduct(){
        String endpoint="http://localhost:80/api_testing/product/update.php";
        String body= """
                {
                "name": "Water Bottle",
                "description":"blue water bottle - Holds 1 litre",
                 "price" :20,
                 "category_id" : 16
                 }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }
    @Test
    public void getProductUpdated(){
        String endpoint="http://localhost:80/api_testing/product/update.php";
        var response=given().auth().basic("root", "root")
                .queryParam("category_id", 16)
                .when().get(endpoint)
                .then();
        response.log().body();
    }
    @Test
    public void deleteProduct(){
        String endpoint ="http://localhost:80/api_testing/product/delete.php";
        String body= """
                {
                "id":1000
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }
    @Test
    public void createSerializedProduct(){
        String endpoint ="http://localhost:80/api_testing/product/create.php";
        Product product = new Product(
                "New Caps",
                "New Caps. For kids, youth, male and female.",
                100,
                51);
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();


    }
}
