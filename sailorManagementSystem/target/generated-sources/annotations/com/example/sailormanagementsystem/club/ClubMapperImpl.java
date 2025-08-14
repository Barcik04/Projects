package com.example.sailormanagementsystem.club;

import com.example.sailormanagementsystem.sailor.Sailor;
import com.example.sailormanagementsystem.sailor.SailorDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T22:04:28+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ClubMapperImpl implements ClubMapper {

    @Override
    public ClubDTO toDto(Club club) {
        if ( club == null ) {
            return null;
        }

        String name = null;
        String location = null;
        List<SailorDTO> sailors = null;

        name = club.getName();
        location = club.getLocation();
        sailors = sailorListToSailorDTOList( club.getSailors() );

        ClubDTO clubDTO = new ClubDTO( name, location, sailors );

        return clubDTO;
    }

    @Override
    public List<ClubDTO> toDtoList(List<Club> clubs) {
        if ( clubs == null ) {
            return null;
        }

        List<ClubDTO> list = new ArrayList<ClubDTO>( clubs.size() );
        for ( Club club : clubs ) {
            list.add( toDto( club ) );
        }

        return list;
    }

    protected SailorDTO sailorToSailorDTO(Sailor sailor) {
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

    protected List<SailorDTO> sailorListToSailorDTOList(List<Sailor> list) {
        if ( list == null ) {
            return null;
        }

        List<SailorDTO> list1 = new ArrayList<SailorDTO>( list.size() );
        for ( Sailor sailor : list ) {
            list1.add( sailorToSailorDTO( sailor ) );
        }

        return list1;
    }
}
