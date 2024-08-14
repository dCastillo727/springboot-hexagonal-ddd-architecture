package com.dcastillo.hexagonalarchitecture.layers.domain.model.user;

import java.util.regex.Pattern;

public record EmailAddress(String value) {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\" +
                    ".[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public EmailAddress {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Email address cannot be null or empty");

        if (!EMAIL_PATTERN.matcher(value).matches()) throw new IllegalArgumentException("Invalid email address");
    }

    public static EmailAddress from(final String value) {
        return new EmailAddress(value);
    }

    private boolean isValid(final String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
