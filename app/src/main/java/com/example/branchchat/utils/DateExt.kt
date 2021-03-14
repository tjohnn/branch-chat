package com.example.branchchat.utils

import java.util.Date
import java.util.Locale

fun Date.toReadableDateTime(): String {
    return String.format(
        Locale.ENGLISH,
        "%1\$td %1\$tb %1\$tY %1\$tH:%1\$tM",
        this
    )
}