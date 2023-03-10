import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.greaterThan;
import static io.restassured.RestAssured.*;

public class OrderCheck {
    public void createdOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("track", greaterThan(0));
    }

    public void chooseColor(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("BLACK", is(true));
    }
}
