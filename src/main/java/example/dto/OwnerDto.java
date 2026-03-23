package example.dto;

import example.model.Owner;
import lombok.Getter;

import java.util.Date;

public class OwnerDto {
    @Getter
    private Long id;
    private String name;
    private Date birthDate;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.birthDate = owner.getBirthDate();
    }
    public OwnerDto(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

}
