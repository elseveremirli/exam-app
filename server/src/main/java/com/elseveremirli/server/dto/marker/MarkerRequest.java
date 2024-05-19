package com.elseveremirli.server.dto.marker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkerRequest {
    String name;
    String surname;
    String username;
    String email;
    String password;
}
