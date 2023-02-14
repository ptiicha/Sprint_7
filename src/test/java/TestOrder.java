import static io.restassured.RestAssured.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestOrder {

    private Order defaultOrder;
    private OrderSettings orderSet;
    private OrderCheck check;
    private int orderId;

    @Before
    public void setUp() {
        orderSet = new OrderSettings();
        check = new OrderCheck();
        defaultOrder = OrderGenerator.getDefault();
    }

    @After
    public void cleanUp() {
        orderSet.cancel(orderId);
    }

    @Test
    public void createdOrder() {
        ValidatableResponse createResponse = orderSet.create(defaultOrder);
        check.createdOrder(createResponse);
        orderId = createResponse.extract().path("track");
    }

    @Test
    public void checkOrdersList() {
        ValidatableResponse createResponse = orderSet.create(defaultOrder);
        ValidatableResponse getResponse = orderSet.returnListOfOrders();
        check.checkOrdersList(getResponse);
        orderId = createResponse.extract().path("track");
    }
}