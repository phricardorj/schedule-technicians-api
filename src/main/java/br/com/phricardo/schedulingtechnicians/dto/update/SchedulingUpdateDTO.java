package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Scheduling Update Request")
public class SchedulingUpdateDTO {

    @NotNull(message = "Appointment date is required")
    @Future(message = "the appointment date must be in the future")
    @Schema(description = "Appointment date and time", example = "2023-04-01T10:00:00")
    private LocalDateTime date;
}
