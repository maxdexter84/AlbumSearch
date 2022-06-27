package ru.maxdexter.albumsearch.domain.common

import ru.maxdexter.albumsearch.utils.FieldState
import java.util.*
import java.util.regex.Pattern

class ValidationHelper {
    companion object {
        fun isValid(str: String, expression: String): FieldState {
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(str)
            return if (matcher.matches()) FieldState.VALID_FIELD else FieldState.NOT_VALID_FIELD
        }

        fun passwordTheSame(pass1: String, pass2: String): FieldState {
            return if (pass1 == pass2) FieldState.VALID_FIELD else FieldState.NOT_THE_SAME_PASSWORD
        }

        fun birthdayValid(str: String): FieldState {
            val (day, month, year) = str.split('.')
            val currentDate = Calendar.getInstance()
            val currentYear = currentDate.get(Calendar.YEAR)
            val currentMonth = currentDate.get(Calendar.MONTH)
            val differenceYear = currentYear - year.toInt()
            val differenceMonth = currentMonth - month.toInt()
            return if (differenceYear > 18) FieldState.VALID_FIELD
            else if (differenceYear == 18 && differenceMonth >= 0) FieldState.VALID_FIELD
            else FieldState.NOTE_AN_ADULT
        }

        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$"
        const val PASSWORD_PATTERN_SYMBOL =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$"
        const val EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    }
}