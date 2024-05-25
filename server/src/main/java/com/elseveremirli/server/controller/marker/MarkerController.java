package com.elseveremirli.server.controller.marker;

import com.elseveremirli.server.dto.marker.MarkerLogin;
import com.elseveremirli.server.dto.marker.MarkerRequest;
import com.elseveremirli.server.dto.marker.MarkerResponse;
import com.elseveremirli.server.entities.Marker;
import com.elseveremirli.server.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marker")
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerService markerService;

    @GetMapping("/getMarker")
    public Marker getMarker(@AuthenticationPrincipal Marker marker) {
        return marker;
    }
    @PostMapping("/register")
    public MarkerResponse  register(@RequestBody MarkerRequest markerRequest) {
        return markerService.saveMarker(markerRequest);
    }

    @PostMapping("/login")
    public MarkerResponse login(@RequestBody MarkerLogin markerLogin) {
        return markerService.loginMarker(markerLogin);
    }




}
