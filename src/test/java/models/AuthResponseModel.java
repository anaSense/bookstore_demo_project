package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseModel {

    String userId, username, password, token, expires;
    @JsonProperty("created_date")
    String createdDate;
    @JsonProperty("isActive")
    boolean isActive;
}
