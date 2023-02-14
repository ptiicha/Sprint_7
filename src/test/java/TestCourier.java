import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestCourier {
    private int courierId;
    private CourierSettings courierSet;
    private Courier courierNew;
    private CourierCheck check;

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        check = new CourierCheck();
        courierSet = new CourierSettings();
        courierNew = CourierGenerator.getRandom();
        }

    @After
    public void cleanUp() {
        if (courierId > 0) {
            CourierSettings.delete(courierId);
        }
    }

@Test
    public void courierCreated() {
    ValidatableResponse createResponse = courierSet.create(courierNew);
    check.courierCreated(createResponse);
    courierId = courierSet.login(CourierCredentials.from(courierNew)).extract().path("id");
}
    @Test
    public void creationSameCourierFailed () {
        courierNew.setLogin("SameLogin");
        courierSet.create(courierNew);
        Courier secondCourier = CourierGenerator.getRandom();
        secondCourier.setLogin("SameLogin");
        ValidatableResponse createResponse = courierSet.create(secondCourier);
        check.creationSameCourierFailed(createResponse);

        courierId = courierSet.login(CourierCredentials.from(courierNew)).extract().path("id");
    }

    @Test
    public void loggedIn () {
        courierSet.create(courierNew);
        CourierCredentials credentials = CourierCredentials.from(courierNew);
        ValidatableResponse loginResponse = courierSet.login(credentials);
        check.loggedIn(loginResponse);
        courierId = loginResponse.extract().path("id");
    }

    @Test
    public void notLoggedRequiredFields () {
        courierSet.create(courierNew);
        courierId = courierSet.login(CourierCredentials.from(courierNew)).extract().path("id");
        CourierCredentials credentials = CourierCredentials.from(courierNew);
        credentials.setLogin(null);
        ValidatableResponse loginResponse = courierSet.login(credentials);
        check.notLoggedRequiredFields(loginResponse);
    }

    @Test
    public void notLoggedInvalidField () {
        courierSet.create(courierNew);
        courierId = courierSet.login(CourierCredentials.from(courierNew)).extract().path("id");
        CourierCredentials credentials = CourierCredentials.from(courierNew);
        credentials.setLogin("Wrong login");
        ValidatableResponse loginResponse = courierSet.login(credentials);
        check.notLoggedInvalidField(loginResponse);
    }
}
