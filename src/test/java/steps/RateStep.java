package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RateStep {
    public String getResponse() {
        return given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
                .then()
                .log().all()
                .statusCode(200)
                .body("amount", equalTo("2,9477"))
                .body("grow", equalTo(1))
                .body("scale", equalTo(1))
                .extract()
                .body().asString();
    }
}
