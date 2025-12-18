package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertTrue;

public class RateValidator {

    public void validateSchema() {
        given()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/rate_schema.json"));
    }

    public void validateHeaders() {
        given()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
                .then()
                .header("Content-Type", containsString("application/json"));
    }

    public void validateKeys() {
        given()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
                .then()
                .body("$", hasKey("amount"))
                .body("$", hasKey("grow"))
                .body("$", hasKey("scale"));
    }

    public void validateScaleRegex(String responseBody) {
        String regex = "amount\"\\s*:\\s*\"\\d+,\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(), "Error message");
    }
}
