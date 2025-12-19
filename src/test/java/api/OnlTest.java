package api;

import org.testng.annotations.Test;
import steps.RateStep;
import validators.RateValidator;

public class OnlTest {
    private final RateStep steps = new RateStep();
    private final RateValidator validator = new RateValidator();

    @Test
    public void checkResponse() {
        String response = steps.getResponse();

        validator
                .validateScaleRegex(response)

                .validateKeys()

                .validateHeaders()

                .validateSchema();
    }
}
