package com.gorkem.samplegaragescenario.model.concretes;

import com.gorkem.samplegaragescenario.model.abstracts.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Entity {

    private String color;
    private String plaque;
    private String type;
    private int size;

    @Override
    public String toString() {
        return "Vehicle{" +
                "color='" + color + '\'' +
                ", plaque='" + plaque + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return size == vehicle.size && color.equals(vehicle.color) && plaque.equals(vehicle.plaque) && type.equals(vehicle.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, plaque, type, size);
    }
}
