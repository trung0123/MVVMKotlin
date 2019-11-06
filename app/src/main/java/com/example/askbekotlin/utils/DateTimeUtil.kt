package com.example.askbekotlin.utils

import com.example.askbekotlin.data.model.RangeDate
import java.text.DateFormat
import java.text.MessageFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtil {
    var PATTERN_API = "yyyy/MM/dd HH:mm"
    var PATTERN_YYYY_MM_dd = "yyyy/MM/dd"
    var PATTERN_HH_mm = "HH:mm"
    var PATTERN_SEARCH = "yyyy/MM/dd"
    var PATTERN_JP = "yyyy年MM月dd日"
    var PATTERN_GTM = "yyyy-MM-dd'T'HH:mm"
    var PATTERN_YYYY_MM_dd_HH_mm_ss_Z = "yyyy/MM/dd HH:mm:ssZ"
    var PATTERN_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSSz"
    var PATTERN_mm_ss_JP = "mm分ss秒"
    var PATTERN_MM_DD_JP = "MM月dd日"

    fun parseDate(time: String, pattern: String): Date? {
        try {
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
            return Date()
        }

    }

    fun parseDateTimeZone(time: String, pattern: String): Date? {
        try {
            val simpleDateFormat = SimpleDateFormat(pattern)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
            return simpleDateFormat.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
            return Date()
        }

    }

    fun formatDate(date: Date?, pattern: String): String {
        try {
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun formatDateTimeZone(date: Date, pattern: String): String {
        try {
            val simpleDateFormat = SimpleDateFormat(pattern)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
            return simpleDateFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun dateNextLesson(start: String, end: String): String {
        val startDate = parseDate(start, PATTERN_API)
        val endDate = parseDate(end, PATTERN_API)
        return fromToDate(startDate, endDate)
    }

    fun dateNextLessonGMT(start: String, end: String): String {
        val startDate = parseDate(start, PATTERN_YYYY_MM_dd_HH_mm_ss_Z)
        val endDate = parseDate(end, PATTERN_YYYY_MM_dd_HH_mm_ss_Z)
        return fromToDate(startDate, endDate)
    }

    private fun fromToDate(startDate: Date?, endDate: Date?): String {
        val calStart = Calendar.getInstance()
        calStart.time = startDate!!
        val calEnd = Calendar.getInstance()
        calEnd.time = endDate!!

        return if (isSameDay(calStart, calEnd)) {
            MessageFormat.format(
                "{0,number,####}年{1,number,00}月{2,number,00}日  " +
                        "{3,number,00}:{4,number,00}" +
                        " ~ " +
                        "{5,number,00}:{6,number,00}",
                calStart.get(Calendar.YEAR),
                calStart.get(Calendar.MONTH) + 1,
                calStart.get(Calendar.DAY_OF_MONTH),
                calStart.get(Calendar.HOUR_OF_DAY),
                calStart.get(Calendar.MINUTE),
                calEnd.get(Calendar.HOUR_OF_DAY),
                calEnd.get(Calendar.MINUTE)
            )
        } else {
            MessageFormat.format(
                ("{0,number,####}年{1,number,00}月{2,number,00}日  " +
                        "{3,number,00}:{4,number,00}" +
                        " ~ " +
                        "{5,number,####}年{6,number,00}月{7,number,00}日  " +
                        "{8,number,00}:{9,number,00}"),
                calStart.get(Calendar.YEAR),
                calStart.get(Calendar.MONTH) + 1,
                calStart.get(Calendar.DAY_OF_MONTH),
                calStart.get(Calendar.HOUR_OF_DAY),
                calStart.get(Calendar.MINUTE),

                calEnd.get(Calendar.YEAR),
                calEnd.get(Calendar.MONTH) + 1,
                calEnd.get(Calendar.DAY_OF_MONTH),
                calEnd.get(Calendar.HOUR_OF_DAY),
                calEnd.get(Calendar.MINUTE)
            )
        }
    }

    fun fromToDateLesson(startDate: Date, endDate: Date, chatUnit: Int): String {
        val calStart = Calendar.getInstance()
        calStart.time = startDate
        val calEnd = Calendar.getInstance()
        calEnd.time = endDate

        return if (isSameDay(calStart, calEnd)) {
            MessageFormat.format(
                ("{0,number,####}/{1,number,00}/{2,number,00}  " +
                        "{3,number,00}:{4,number,00}" +
                        " ~ " +
                        "{5,number,00}:{6,number,00} " +
                        "({7}分)"),
                calStart.get(Calendar.YEAR),
                calStart.get(Calendar.MONTH) + 1,
                calStart.get(Calendar.DAY_OF_MONTH),
                calStart.get(Calendar.HOUR_OF_DAY),
                calStart.get(Calendar.MINUTE),
                calEnd.get(Calendar.HOUR_OF_DAY),
                calEnd.get(Calendar.MINUTE),
                chatUnit
            )
        } else {
            MessageFormat.format(
                ("{0,number,####}/{1,number,00}/{2,number,00}  " +
                        "{3,number,00}:{4,number,00}" +
                        " ~ " +
                        "{5,number,####}/{6,number,00}/{7,number,00} " +
                        "{8,number,00}:{9,number,00} " +
                        "({10}分)"),
                calStart.get(Calendar.YEAR),
                calStart.get(Calendar.MONTH) + 1,
                calStart.get(Calendar.DAY_OF_MONTH),
                calStart.get(Calendar.HOUR_OF_DAY),
                calStart.get(Calendar.MINUTE),

                calEnd.get(Calendar.YEAR),
                calEnd.get(Calendar.MONTH) + 1,
                calEnd.get(Calendar.DAY_OF_MONTH),
                calEnd.get(Calendar.HOUR_OF_DAY),
                calEnd.get(Calendar.MINUTE),
                chatUnit
            )
        }
    }

    fun getTimeAgo(dateStr: String): String? {
        val date = DateTimeUtil.parseDateTimeZone(dateStr, PATTERN_API)
        val time = date!!.time


        val now = getCurrentDate().time

        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time

        val c = getCalendarUTC()
        c.timeInMillis = diff

        if (c.get(Calendar.YEAR) >= 1971) {
            return (c.get(Calendar.YEAR)).toString() + "年以上前"
        }

        if (c.get(Calendar.MONTH) >= 1) {
            return (c.get(Calendar.MONTH)).toString() + "ヶ月前"
        }

        if ((c.get(Calendar.DAY_OF_MONTH) - 1) / 7 >= 1) {
            return ((c.get(Calendar.DAY_OF_MONTH) - 1) / 7).toString() + "週間前"
        }

        if (c.get(Calendar.DAY_OF_MONTH) - 1 >= 1) {
            return (c.get(Calendar.DAY_OF_MONTH) - 1).toString() + "日前"
        }

        if (c.get(Calendar.HOUR_OF_DAY) >= 1) {
            return (c.get(Calendar.HOUR_OF_DAY)).toString() + "時間前"
        }

        return if (c.get(Calendar.MINUTE) >= 1) {
            (c.get(Calendar.MINUTE)).toString() + "分前"
        } else "たった今"

    }

    fun getDiffTime(dateStr: String): Long {
        val date = DateTimeUtil.parseDateTimeZone(dateStr, PATTERN_API)
        val time = date!!.time

        val now = getCurrentTimeInMillis()
        return if (time <= 0) {
            0
        } else time - now
    }

    fun getDiffTime(time: Long): Long {
        val now = getCurrentTimeInMillis()
        return if (time <= 0) {
            0
        } else time - now
    }

    fun getTimeLessonStart(dateStr: String): String {
        val diff = getDiffTime(dateStr)

        val c = getCalendarUTC()

        c.timeInMillis = diff

        if (c.get(Calendar.DAY_OF_YEAR) - 1 >= 1) {
            return (c.get(Calendar.DAY_OF_YEAR) - 1).toString() + "日後"
        }
        if (c.get(Calendar.HOUR_OF_DAY) >= 1) {
            return (c.get(Calendar.HOUR_OF_DAY)).toString() + "時間" + ":" + c.get(Calendar.MINUTE) + "分"
        }
        return if (c.get(Calendar.MINUTE) >= 1) {
            (c.get(Calendar.MINUTE)).toString() + "分" + ":" + c.get(Calendar.SECOND) + "秒"
        } else (c.get(Calendar.SECOND)).toString() + "秒"

    }

    fun getTimeLessonStart(diff: Long): String {
        val c = getCalendarUTC()

        c.timeInMillis = diff

        if (c.get(Calendar.DAY_OF_YEAR) - 1 >= 1) {
            return (c.get(Calendar.DAY_OF_YEAR) - 1).toString() + "日後"
        }
        if (c.get(Calendar.HOUR_OF_DAY) >= 1) {
            return (c.get(Calendar.HOUR_OF_DAY)).toString() + "時間" + c.get(Calendar.MINUTE) + "分"
        }
        return if (c.get(Calendar.MINUTE) >= 1) {
            (c.get(Calendar.MINUTE)).toString() + "分" + c.get(Calendar.SECOND) + "秒"
        } else (c.get(Calendar.SECOND)).toString() + "秒"

    }

    fun getCalendarUTC(): Calendar {
        val timeZone = TimeZone.getTimeZone("UTC")
        return Calendar.getInstance(timeZone)
    }

    fun getCurrentTimeInMillis(): Long {
        val cal = GregorianCalendar()
        return cal.timeInMillis
    }

    fun getCurrentTime(): Long {
        return getCurrentTimeInMillis() / 1000
    }

    fun getCurrentTimeString(pattern: String): String {
        val currentTime = Calendar.getInstance().time
        try {
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.format(currentTime)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun getUnixTime(date: Date): Long {
        return date.time / 1000

    }

    fun parseTimeStamp(time: Long?, pattern: String): String {
        val date = Date(time!! * 1000)
        return formatDate(date, pattern)
    }

    fun parseMinutesToHHmm(minutes: Int): String {
        val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
        val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
        return MessageFormat.format("{0,number,00}:{1,number,00}", hours, remainMinutes)
    }

    fun isSameDate(date1: Date, date2: Date): Boolean {
        val cal1 = getCurrentCalendar()
        cal1.time = date1
        val cal2 = getCurrentCalendar()
        cal2.time = date2
        return isSameDay(cal1, cal2)
    }

    fun isDateBefore(date1: Date, date2: Date): Boolean {
        return date1.before(date2)
    }

    fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    fun isPastTime(dateStr: String): Boolean {
        val diff = getDiffTime(dateStr)
        return if (diff < 0) true else false
    }

    fun getCurrentDate(): Date {
        return getCurrentCalendar().time
    }

    fun getCurrentCalendar(): Calendar {
        return Calendar.getInstance(TimeZone.getTimeZone("Japan"))
    }

    fun getCalendar(date: Date): Calendar {
        val cal = getCurrentCalendar()
        cal.time = date
        return cal
    }

    fun getTextDateRange(rangeDate: RangeDate): String {
        return rangeDate.day + " " + rangeDate.startHour + " ~ " + rangeDate.endHour
    }

    fun getTextDateRangeJP(rangeDate: RangeDate): String {
        try {
            val date = DateFormat.getDateInstance().parse(rangeDate.day)
            val dateJP = formatDate(date, PATTERN_MM_DD_JP)
            return dateJP + " " + rangeDate.startHour + "~" + rangeDate.endHour
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }

    fun getYear(date: Date): Int {
        val cal = getCalendar(date)
        return cal.get(Calendar.YEAR)
    }

    fun getAge(date: Date): Int {
        var age: Int
        val now = getCurrentCalendar()
        val dob = getCalendar(date)
        require(!dob.after(now)) { "Can't be born in the future" }
        val year1 = now.get(Calendar.YEAR)
        val year2 = dob.get(Calendar.YEAR)
        age = year1 - year2
        val month1 = now.get(Calendar.MONTH)
        val month2 = dob.get(Calendar.MONTH)
        if (month2 > month1) {
            age--
        } else if (month1 == month2) {
            val day1 = now.get(Calendar.DAY_OF_MONTH)
            val day2 = dob.get(Calendar.DAY_OF_MONTH)
            if (day2 > day1) {
                age--
            }
        }
        return age
    }
}