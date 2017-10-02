package org.itstep;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Message {
    private int id;
    private String text;
    private LocalDateTime time;
}
