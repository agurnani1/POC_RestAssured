package training;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class apiTestsSweatBand {
    @Test
    public void createSweatBand(){
        String endpoint="http://localhost:80/api_testing/product/create.php";
        Product swetband = new Product("SweatBand",
                "Sweatband. In 7 colors, made of cotton and silk.",
                30,
                91);
        var response= given().body(swetband).when().post(endpoint).then();
        response.log().body();

    }
    @Test
    public void getSweatBand(){
        String endpoint="http://localhost:80/api_testing/product/search.php";
        var response= given().
                queryParam("name","SweatBand").
                when().get(endpoint).then();
        response.log().body();
    }
    @Test
    public void updateSweatBandPrice(){
        String endpoint="http://localhost:80/api_testing/product/update.php";
        Product swetband = new Product("SweatBand",
                "Sweatband. In 7 colors, made of cotton and silk.",
                100,
                91);
        var response= given().body(swetband).put(endpoint).then();
        response.log().body();
    }
    @Test
    public void deleteSweatBand(){
        String endpoint="http://localhost:80/api_testing/product/delete.php";
        String swetbandBody = """
                {
                "id": "1004"
                }
                """;
        var response= given().body(swetbandBody).when().delete(endpoint).then();
        response.log().body();
    }
}
