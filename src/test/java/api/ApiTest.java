package api;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static enums.Currency.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class ApiTest {
    @DataProvider(name = "currencies")
    public Object[][] currencyProvider() {
        return new Object[][]{
                {RUB.getCode()},
                {USD.getCode()},
                {EUR.getCode()}
        };
    }

    @Test(dataProvider = "currencies")
    public void checkOnlinerRates(String currency) {
        String responseBody = given()
                .log().all()
                .when()
                .get("https://kurs.onliner.by/sdapi/kurs/api/bestrate?currency=" + currency + "&type=nbrb")
                .then()
                .log().all()
                .statusCode(200)
                //.body("amount", equalTo("2,9477"))
                //.body("grow", equalTo(1))
                //.body("scale", equalTo(1))
                .extract()
                .body().asString();

        System.out.println(responseBody.toUpperCase());

        String regex = "amount\"\\s*:\\s*\"\\d+,\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(), "Error message");
    }
}
