package br.com.phricardo.schedulingtechnicians.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingUpdateDTO {

    private LocalDateTime date;
}
