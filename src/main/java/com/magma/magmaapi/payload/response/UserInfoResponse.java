package com.magma.magmaapi.payload.response;

import java.util.List;

public record UserInfoResponse(
    String username,
    String email,
    List<String> roles

) {
}
