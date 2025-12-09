package tests.booking;

import framework.auth.AuthService;
import framework.config.Environment;
import framework.http.ApiClient;
import framework.models.Booking;
import framework.models.BookingDates;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateBookingTest {

    private int createBooking(ApiClient api) {
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        Booking booking = new Booking("Nestor", "Pavon", 150, true, dates, "Breakfast");

        Response res = api.post("/booking", booking);
        return res.jsonPath().getInt("bookingid");
    }

    @Test
    void updateBooking_shouldModifyFieldsCorrectly() {

        ApiClient api = new ApiClient(Environment.getBaseUrl());
        AuthService authService = new AuthService(api);

        String token = authService.login();
        assertNotNull(token);

        int bookingId = createBooking(api);
        assertTrue(bookingId > 0);

        BookingDates newDates = new BookingDates("2025-02-01", "2025-02-15");

        Booking updatedBooking = new Booking(
                "NestorUpdated",
                "PavonUpdated",
                999,
                false,
                newDates,
                "Dinner"
        );

        Response res = api.put("/booking/" + bookingId, updatedBooking, token);

        assertEquals(200, res.statusCode());
        
        assertEquals("NestorUpdated", res.jsonPath().getString("firstname"));
        assertEquals("PavonUpdated", res.jsonPath().getString("lastname"));
        assertEquals(999, res.jsonPath().getInt("totalprice"));
        assertFalse(res.jsonPath().getBoolean("depositpaid"));
        assertEquals("Dinner", res.jsonPath().getString("additionalneeds"));
    }
}
