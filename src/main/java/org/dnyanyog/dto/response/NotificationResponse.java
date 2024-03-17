package org.dnyanyog.dto.response;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponse {

  private String status;
  private String code;
  private String message;
  private LocalDateTime timestamp;

  public static NotificationResponse getInstance() {
    return new NotificationResponse();
  }

  public String getStatus() {
    return status;
  }

  public NotificationResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getCode() {
    return code;
  }

  public NotificationResponse setCode(String string) {
    this.code = string;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public NotificationResponse setMessage(String message) {
    this.message = message;
    return this;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public NotificationResponse setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }
}
