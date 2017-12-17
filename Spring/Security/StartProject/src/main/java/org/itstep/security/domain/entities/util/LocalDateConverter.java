package org.itstep.security.domain.entities.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return null == localDate ? null: Date.valueOf(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		return null == date ? null : date.toLocalDate(); 
	}

}
