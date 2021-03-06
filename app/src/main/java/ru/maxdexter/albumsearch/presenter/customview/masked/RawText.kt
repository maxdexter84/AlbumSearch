package ru.maxdexter.albumsearch.presenter.customview.masked


class RawText {
    var text = ""
        private set

    fun subtractFromString(range: Range) {
        var firstPart = ""
        var lastPart = ""
        if (range.start > 0 && range.start <= text.length) {
            firstPart = text.substring(0, range.start)
        }
        if (range.end >= 0 && range.end < text.length) {
            lastPart = text.substring(range.end, text.length)
        }
        text = firstPart + lastPart
    }

    fun addToString(newString: String?, start: Int, maxLength: Int): Int {
        var str = newString
        var firstPart = EMPTY_STRING
        var lastPart = EMPTY_STRING
        if (str == null || str == EMPTY_STRING) {
            return 0
        } else require(start >= 0) { "Start position must be non-negative" }
        require(start <= text.length) { "Start position must be less than the actual text length" }
        var count = str.length
        if (start > 0) {
            firstPart = text.substring(0, start)
        }
        if (start >= 0 && start < text.length) {
            lastPart = text.substring(start, text.length)
        }
        if (text.length + str.length > maxLength) {
            count = maxLength - text.length
            str = str.substring(0, count)
        }
        text = firstPart + str + lastPart
        return count
    }

    fun length(): Int {
        return text.length
    }

    fun charAt(position: Int): Char {
        return text[position]
    }

    companion object{
        const val EMPTY_STRING = ""
    }
}