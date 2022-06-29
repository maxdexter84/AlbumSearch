package ru.maxdexter.albumsearch.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.stringTimeToStringDateTime(inputFormat: String, outputFormat: String): String {
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

    return LocalDateTime.of(LocalDate.now(ZoneOffset.UTC), this.stringToTime(inputFormat))
        .format(outputFormatter)
}

fun String.stringToTime(format: String): LocalTime {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalTime.parse(this, formatter)
}