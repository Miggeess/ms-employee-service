package com.babel.employeeservice.interfaces.error;

import java.time.Instant;

public record ApiError(String message, String path, int status, Instant timestamp){
}
