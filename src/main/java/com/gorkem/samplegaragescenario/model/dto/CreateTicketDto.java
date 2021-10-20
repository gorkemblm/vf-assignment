package com.gorkem.samplegaragescenario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketDto {

    private String reasonForApplication;
    private String carPlaque;
    private String carColor;
    private String carType;
}
