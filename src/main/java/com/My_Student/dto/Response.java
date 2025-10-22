package com.My_Student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Response",
        description = "Schema to hold response information")
public class Response {
    @Schema(
            description = "Response code in the response"
    )
    private String responseCode;

    @Schema(
            description = "Response message in the response"
    )
    private String responseMessage;

    private DataView dataView;

//    private String statusCode;
//    private String statusMessage;
}
