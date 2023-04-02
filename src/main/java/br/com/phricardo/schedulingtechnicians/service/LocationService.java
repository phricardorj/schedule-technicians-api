package br.com.phricardo.schedulingtechnicians.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class LocationService {

    public String buildLocation(String url) {
        if (url == null) throw new NullPointerException("URL cannot be null");
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/" + url)
                .toUriString();
    }
}
