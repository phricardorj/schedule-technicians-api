package br.com.phricardo.schedulingtechnicians.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingResponseDTO {

    private String os;
    private LocalDateTime date;
    private Long customerId;
}
