package com.gorkem.samplegaragescenario.model.concretes;

import com.gorkem.samplegaragescenario.model.abstracts.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Entity {

    private final String uniqueID = UUID.randomUUID().toString();

    private final String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    private String carPlaque;
    private String carColor;
    private int carSize;
    private String reasonForApplication;
    private boolean parkStatus;

    @Override
    public String toString() {
        return "Ticket{" +
                "uniqueID='" + uniqueID + '\'' +
                ", createDate='" + createDate + '\'' +
                ", carPlaque='" + carPlaque + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carSize=" + carSize +
                ", reasonForApplication='" + reasonForApplication + '\'' +
                ", parkStatus=" + parkStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return carSize == ticket.carSize && parkStatus == ticket.parkStatus && uniqueID.equals(ticket.uniqueID) && createDate.equals(ticket.createDate) && carPlaque.equals(ticket.carPlaque) && carColor.equals(ticket.carColor) && reasonForApplication.equals(ticket.reasonForApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueID, createDate, carPlaque, carColor, carSize, reasonForApplication, parkStatus);
    }
}
