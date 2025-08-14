package com.example.sailormanagementsystem.sailor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T21:58:56+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class SailorMapperImpl implements SailorMapper {

    @Override
    public SailorDTO toDto(Sailor sailor) {
        if ( sailor == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        LocalDate dateOfBirth = null;

        firstName = sailor.getFirstName();
        lastName = sailor.getLastName();
        dateOfBirth = sailor.getDateOfBirth();

        SailorDTO sailorDTO = new SailorDTO( firstName, lastName, dateOfBirth );

        return sailorDTO;
    }

    @Override
    public List<SailorDTO> toDtoList(List<Sailor> sailors) {
        if ( sailors == null ) {
            return null;
        }

        List<SailorDTO> list = new ArrayList<SailorDTO>( sailors.size() );
        for ( Sailor sailor : sailors ) {
            list.add( toDto( sailor ) );
        }

        return list;
    }
}
