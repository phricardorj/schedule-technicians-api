package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Scheduling Response")
public class SchedulingResponseDTO {

    @Schema(description = "Service Order", example = "2023033186698")
    private String os;
    @Schema(description = "Appointment date and time", example = "2023-04-01T10:00:00")
    private LocalDateTime date;
    @Schema(description = "Customer Id", example = "123")
    private Long customerId;
}
