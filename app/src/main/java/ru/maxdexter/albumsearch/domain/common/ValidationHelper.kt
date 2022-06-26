package ru.maxdexter.albumsearch.domain.common

import java.util.regex.Pattern

class ValidationHelper {
    companion object {
        fun isValid(str: String, expression: String): Boolean {
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(str)
            return matcher.matches()
        }

        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$"
        const val PASSWORD_PATTERN_SYMBOL =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$"
        const val EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    }
}