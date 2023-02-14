import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.greaterThan;
import static io.restassured.RestAssured.*;

public class OrderCheck {
    public void createdOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0));
    }

    public void checkOrdersList(ValidatableResponse response) {
        response.assertThat();
        response.statusCode(200);
    }
}
