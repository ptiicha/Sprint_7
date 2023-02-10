import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.*;

public class CourierCheck {

    public void courierCreated(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }

    public void creationSameCourierFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", notNullValue());
    }

    public void loggedIn(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }

    public void notLoggedRequiredFields(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", notNullValue());
    }

    public void notLoggedInvalidField(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", notNullValue());
    }
}
