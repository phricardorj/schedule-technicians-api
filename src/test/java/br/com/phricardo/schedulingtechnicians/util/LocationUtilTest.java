package br.com.phricardo.schedulingtechnicians.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationUtilTest {

    @Test
    public void buildLocation_simpleUrl() {
        LocationUtil locationUtil = new LocationUtil();
        String url = "customers";
        String expectedLocation = "/api/v1/customers";
        String actualLocation = locationUtil.buildLocation(url);
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void buildLocation_urlWithMultipleLevels() {
        LocationUtil locationUtil = new LocationUtil();
        String url = "customers/1234/schedules/5678";
        String expectedLocation = "/api/v1/customers/1234/schedules/5678";
        String actualLocation = locationUtil.buildLocation(url);
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void buildLocation_withNullUrl_shouldThrowException() {
        LocationUtil locationUtil = new LocationUtil();
        String url = null;
        assertThrows(NullPointerException.class, () -> locationUtil.buildLocation(url));
    }

}