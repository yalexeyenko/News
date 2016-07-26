package com.epam.yalexeyenko.converter;

import java.time.LocalDate;
import java.sql.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		if (localDate == null) {
			return null;
		} else {
			return Date.valueOf(localDate);
		}
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		if (sqlDate == null) {
			return null;
		} else {
			return sqlDate.toLocalDate();
		}
	}

}
