package com.ru.vyatsu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam {
    private String subject;
    private Integer score;
    private Integer idEnrollee;
}
