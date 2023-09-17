package api.countrylayer.com;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/country.feature",
        glue = "api.countrylayer.com.definitions",
        tags = "@Test"
)
public class Runner {
}
