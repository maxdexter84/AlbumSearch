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
            return if (isValid(str, DATE_PATTERN) == FieldState.NOT_VALID_FIELD) FieldState.NOT_VALID_FIELD
            else{
                val (day, month, year) = str.split('.')
                val currentDate = Calendar.getInstance()
                val currentYear = currentDate.get(Calendar.YEAR)
                val currentMonth = currentDate.get(Calendar.MONTH)
                val differenceYear = currentYear - year.toInt()
                val differenceMonth = currentMonth - month.toInt()
                return  if (str.length < 10 || year.toInt() > currentYear || month.toInt() > 12 || day.toInt() > 31) FieldState.NOT_VALID_FIELD
                else if (differenceYear > 18) FieldState.VALID_FIELD
                else if (differenceYear == 18 && differenceMonth >= 0) FieldState.VALID_FIELD
                else FieldState.NOTE_AN_ADULT
            }
        }


        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$"
        const val PASSWORD_PATTERN_SYMBOL =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$"
        const val EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
       // const val PHONE_PATTERN = "^((\\+7|7|8)+([0-9]){10})\$"
        const val PHONE_PATTERN = "^([0-9]{10})\$"
      const val DATE_PATTERN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))\$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$"


    }
}