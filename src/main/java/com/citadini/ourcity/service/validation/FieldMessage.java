package com.citadini.ourcity.service.validation;

import java.io.Serializable;

public record FieldMessage (
		String fieldName,
		String message) {
}
