package br.com.phricardo.schedulingtechnicians.util;

import org.springframework.stereotype.Service;

@Service
public class LocationUtil {

    public String buildLocation(String url) {
        return "/api/v1/" + url;
    }
}
