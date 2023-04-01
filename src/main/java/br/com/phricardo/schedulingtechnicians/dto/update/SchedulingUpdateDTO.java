package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Scheduling Update Request")
public class SchedulingUpdateDTO {

    @Schema(description = "Appointment date and time", example = "2023-04-01T10:00:00")
    private LocalDateTime date;
}
