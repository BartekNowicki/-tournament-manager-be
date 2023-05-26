package com.app.tmbe.enumConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TournamentTypeConverter implements AttributeConverter<TournamentType, String> {

  @Override
  public String convertToDatabaseColumn(TournamentType type) {
    if (type == null) {
      return null;
    }
    return type.getNaming();
  }

  @Override
  public TournamentType convertToEntityAttribute(String naming) {
    if (naming == null) {
      return null;
    }

    return Stream.of(TournamentType.values())
        .filter(c -> c.getNaming().equals(naming))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
