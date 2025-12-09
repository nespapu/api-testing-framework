package tests.booking;

import framework.auth.AuthService;
import framework.config.Environment;
import framework.http.ApiClient;
import framework.models.Booking;
import framework.models.BookingDates;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteBookingTest {

    private int createBooking(ApiClient api) {
        BookingDates dates = new BookingDates("2025-03-01", "2025-03-10");
        Booking booking = new Booking("Nestor", "DeleteTest", 200, true, dates, "Lunch");

        Response res = api.post("/booking", booking);
        Integer bookingId = res.jsonPath().getInt("bookingid");

        assertNotNull(bookingId, "Booking creation should contain bookingid");
        return bookingId;
    }

    @Test
    void deleteBooking_shouldRemoveBookingSuccessfully() {

        ApiClient api = new ApiClient(Environment.getBaseUrl());
        AuthService authService = new AuthService(api);

        String token = authService.login();
        assertNotNull(token);

        int bookingId = createBooking(api);
        assertTrue(bookingId > 0);

        Response deleteRes = api.delete("/booking/" + bookingId, token);

        int status = deleteRes.statusCode();
        assertTrue(status >= 200 && status < 300,
                "DELETE should return a 2xx status code, but returned " + status);

        Response getRes = api.get("/booking/" + bookingId);

        assertEquals(404, getRes.statusCode(),
                "After deletion, GET /booking/{id} is expected to return 404");
    }
}
