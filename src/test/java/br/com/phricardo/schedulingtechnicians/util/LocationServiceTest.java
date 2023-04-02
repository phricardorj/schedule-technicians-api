package br.com.phricardo.schedulingtechnicians.util;

import br.com.phricardo.schedulingtechnicians.service.LocationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    @Test
    public void buildLocation_simpleUrl() {
        LocationService locationService = new LocationService();
        String url = "customers";
        String expectedLocation = "/api/v1/customers";
        String actualLocation = locationService.buildLocation(url);
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void buildLocation_urlWithMultipleLevels() {
        LocationService locationService = new LocationService();
        String url = "customers/1234/schedules/5678";
        String expectedLocation = "/api/v1/customers/1234/schedules/5678";
        String actualLocation = locationService.buildLocation(url);
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void buildLocation_withNullUrl_shouldThrowException() {
        LocationService locationService = new LocationService();
        String url = null;
        assertThrows(NullPointerException.class, () -> locationService.buildLocation(url));
    }

}