package br.com.phricardo.schedulingtechnicians.util;

import org.springframework.stereotype.Service;

@Service
public class LocationUtil {

    public String buildLocation(String url) {
        if (url == null) throw new NullPointerException("URL cannot be null");
        return "/api/v1/" + url;
    }
}
