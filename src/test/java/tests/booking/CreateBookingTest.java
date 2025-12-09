package tests.booking;

import framework.config.Environment;
import framework.http.ApiClient;
import framework.models.Booking;
import framework.models.BookingDates;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBookingTest {

    @Test
    void createBooking_shouldReturnBookingIdAndCorrectData() {

        ApiClient api = new ApiClient(Environment.getBaseUrl());

        BookingDates dates = new BookingDates(
                "2025-01-01",
                "2025-01-10"
        );

        Booking booking = new Booking(
                "Nestor",
                "Pavon",
                150,
                true,
                dates,
                "Breakfast"
        );

        Response res = api.post("/booking", booking);

        assertEquals(200, res.statusCode(), "Booking creation should return status 200");

        Integer bookingId = res.jsonPath().getInt("bookingid");
        assertNotNull(bookingId, "Response should contain bookingid");
        assertTrue(bookingId > 0, "bookingid should be greater than 0");

        String firstnameResponse = res.jsonPath().getString("booking.firstname");
        String lastnameResponse  = res.jsonPath().getString("booking.lastname");

        assertEquals(booking.getFirstname(), firstnameResponse);
        assertEquals(booking.getLastname(), lastnameResponse);
    }
}
