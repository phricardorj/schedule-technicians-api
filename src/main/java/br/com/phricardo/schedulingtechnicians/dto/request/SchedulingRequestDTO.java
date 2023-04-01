package br.com.phricardo.schedulingtechnicians.dto.request;

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
@Schema(title = "Scheduling Request")
public class SchedulingRequestDTO {

    @NotNull @Future
    private LocalDateTime date;
    @NotNull
    private Long customerId;
}
