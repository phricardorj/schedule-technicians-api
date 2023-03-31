package br.com.phricardo.schedulingtechnicians.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDTO {

    @NotNull @Future
    private LocalDateTime date;
    @NotNull
    private Long customerId;
}
