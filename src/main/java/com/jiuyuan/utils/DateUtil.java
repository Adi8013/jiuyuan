package com.jiuyuan.utils;

/**
 * 日期格式化操作工具类
 * author Adi
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private static TimeZone beijingTZ = TimeZone.getTimeZone("GMT+8");
	private static SimpleDateFormat ymdhmssdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat ymdhmsssdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat yyyygMMgddsdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat yyyymmddsdf = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat yyyymmddhhmmsssdf = new SimpleDateFormat("yyyyMMddHHmmss");

	static {
		ymdhmssdf.setTimeZone(beijingTZ);
		ymdhmsssdf.setTimeZone(beijingTZ);
		yyyygMMgddsdf.setTimeZone(beijingTZ);
		yyyymmddsdf.setTimeZone(beijingTZ);
	}

	/**
	 * @function:根据指定的格式格式化系统当前日期。
	 * @paratype:String，日期格式。
	 * @retinfo:String，格式化的系统当前日期。
	 */
	public static String getDateByPattern(String strPattern) {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		sdf.setTimeZone(beijingTZ);
		return sdf.format(calendar.getTimeInMillis());
	}

	/**
	 * @function:根据指定的格式格式化系统当前日期。
	 * @paratype:SimpleDateFormat，日期格式对象。
	 * @retinfo:String，格式化的系统当前日期。
	 */
	public static String getDateByPattern(SimpleDateFormat sdf) {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		sdf.setTimeZone(beijingTZ);
		return sdf.format(calendar.getTimeInMillis());
	}

	/**
	 * @function:返回格式'yyyy-MM-dd HH:mm:ss'的系统当前日期。
	 * @retinfo:String，格式为'yyyy-MM-dd HH:mm:ss'的系统当前日期。
	 */
	public static String getSystemDate() {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		return ymdhmssdf.format(calendar.getTimeInMillis());
	}

	/**
	 * @function:返回格式'yyyy-MM-dd'的指定日期。
	 * @retinfo:String，格式为'yyyy-MM-dd'的指定日期。
	 */
	public static String getSpecifyDate(Date date) {
		return yyyygMMgddsdf.format(date);
	}

	/**
	 * @function:返回格式'yyyy-MM-dd HH:mm:ss.SSS'的系统当前日期。
	 * @retinfo:String，格式为'yyyy-MM-dd HH:mm:ss.SSS'的系统当前日期。
	 */
	public static String getSystemDateTime() {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		return ymdhmsssdf.format(calendar.getTimeInMillis());
	}

	/**
	 * @function:将格式为'yyyy-MM-dd'的日期转成中文格式'yyyy年MM月dd日'。
	 * @paratype:String，格式为'yyyy-MM-dd'的日期。
	 * @retinfo:String，中文格式的日期。
	 */
	public static String getDateInChinese(String strDate) {
		if (strDate == null || strDate.length() < 10) {
			return "";
		}
		return strDate.substring(0, 4) + "年" + strDate.substring(5, 7) + "月" + strDate.substring(8, 10) + "日";
	}

	/**
	 * @function:返回格式'yyyyMMddHHmmssSSS'的系统当前日期，用于唯一顺序序列号。
	 * @retinfo:String，格式为'yyyyMMddHHmmssSSS'的系统当前日期。
	 */
	public static String getSystemDateTimeSerialNo() {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(calendar.getTimeInMillis());
	}

	/**
	 * @function:返回格式'yyyyMMdd'的系统当前日期
	 * @retinfo:String，格式为'yyyyMMdd'的系统当前日期。
	 */
	public static String getSystemDate2() {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		return yyyymmddsdf.format(calendar.getTime());
	}

	/**
	 * @function:返回格式'yyyy-MM-dd'的系统当前日期。
	 * @retinfo:String，格式为'yyyy-MM-dd'的系统当前日期。
	 */
	public static String getSystemDate3() {
		Calendar calendar = Calendar.getInstance(beijingTZ);
		return yyyygMMgddsdf.format(calendar.getTime());
	}

	/**
	 * @function:将指定日期推前yyyy年MM月dd日。
	 * @paratype:String，格式为'yyyy-MM-dd'的日期。
	 * @paratype:int，年份推前值,负数推前。例如：2010，如果-1，则2011，如果1，则2009。
	 * @paratype:int，月份推前值。
	 * @paratype:int，日期推前值。
	 * @retinfo:String，结果日期。
	 */
	public static String getPointedDate(String strDate, int intYear, int intMonth, int intDay) {
		String strDateRet = "";
		Calendar calDate = getCalendarByString(strDate);
		calDate.set(Calendar.YEAR, calDate.get(Calendar.YEAR) - intYear);
		calDate.set(Calendar.MONTH, calDate.get(Calendar.MONTH) - intMonth);
		calDate.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH) - intDay);
		strDateRet = getSpecifyDate(calDate.getTime());
		return strDateRet;
	}

	/**
	 * @function:将指定日期转化成Calendar类型日期。
	 * @paratype:String，格式为'yyyy-MM-dd'的日期。
	 * @retinfo:Calendar，Calendar类型日期。
	 */
	public static Calendar getCalendarByString(String strDate) {
		Calendar calRet = Calendar.getInstance();
		calRet.set(Calendar.YEAR, Integer.parseInt(strDate.substring(0, 4)));
		calRet.set(Calendar.MONTH, Integer.parseInt(strDate.substring(5, 7)) - 1);
		calRet.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDate.substring(8, 10)));
		return calRet;
	}

	/**
	 * 取得两月份之差，strDate1-strDate2
	 * 
	 * @param strDate1
	 * @param strDate2
	 * @return
	 */
	public static int getMonthDiff(String strDate1, String strDate2) {
		Calendar xmas = getCalendarByString(strDate1);
		Calendar newyears = getCalendarByString(strDate2);
		int m = (xmas.get(xmas.MONTH)) - (newyears.get(newyears.MONTH));
		int y = (xmas.get(xmas.YEAR)) - (newyears.get(newyears.YEAR));
		return y * 12 + m;

	}

	/**
	 * 取得两月份之差，strDate1-strDate2 当strDate1-strDate2不足整月的,把零头去掉,取整
	 * 
	 * @param strDate1
	 * @param strDate2
	 * @return
	 */
	public static int getMonthDiffTo(String strDate1, String strDate2) {
		Calendar xmas = getCalendarByString(strDate1);
		Calendar newyears = getCalendarByString(strDate2);
		int m = (xmas.get(xmas.MONTH)) - (newyears.get(newyears.MONTH));
		int y = (xmas.get(xmas.YEAR)) - (newyears.get(newyears.YEAR));
		// 当strDate1-strDate2不足整月的,把零头去掉,取整数
		String strDate3 = getPointedDate(strDate1, y, m, 0);
		Calendar pointnewyears = getCalendarByString(strDate3);
		if (pointnewyears.get(newyears.MONTH) > newyears.get(newyears.MONTH)) {
			return y * 12 + m;
		} else {
			if (pointnewyears.get(newyears.DATE) < newyears.get(newyears.DATE)) {
				return y * 12 + m - 1;
			} else
				return y * 12 + m;
		}
	}

	/**
	 * 取得两个时间之差，strDate1-strDate2 例如：18：03：24 - 18：01:11
	 * 
	 * @author 郑斌
	 * @param strDate1
	 * @param strDate2
	 * @return 秒数
	 */
	public static int getTimeDiff(String strEndTime, String strBeginTime) {
		String strEH = strEndTime.substring(0, 2);
		String strEM = strEndTime.substring(3, 5);
		String strES = strEndTime.substring(6, 8);

		int endSecond = (Integer.parseInt(strEH) * 60 + Integer.parseInt(strEM)) * 60 + Integer.parseInt(strES);

		String strBH = strBeginTime.substring(0, 2);
		String strBM = strBeginTime.substring(3, 5);
		String strBS = strBeginTime.substring(6, 8);

		int beginSecond = (Integer.parseInt(strBH) * 60 + Integer.parseInt(strBM)) * 60 + Integer.parseInt(strBS);
		return endSecond - beginSecond;

	}

	/**
	 * 取两个日期之差，精确到天数，strDate1-strDate2 例如：2010-08-11 - 2010-12-31，结果：4个月20天
	 * 
	 * @author 郑斌
	 * @param strBeginDate
	 *            开始日期
	 * @param strEndDate
	 *            结束日期
	 * @param intDay
	 *            设置多少天为1个月
	 * @param flg
	 *            true返回天数、false不返回天数
	 * @return 月-日，格式：04-20
	 */
	public static String getDateDiff(String strBeginDate, String strEndDate, int intDay, boolean flg) {
		Calendar calBeginDate = getCalendarByString(strBeginDate);
		Calendar calEndDate = getCalendarByString(strEndDate);

		/** **** 计算月份Begin ***** */
		int y = (calEndDate.get(calEndDate.YEAR)) - (calBeginDate.get(calBeginDate.YEAR));
		int m = (calEndDate.get(calEndDate.MONTH)) - (calBeginDate.get(calBeginDate.MONTH));
		m += y * 12;
		/** **** 计算月份End ***** */

		/** **** 计算天数Begin ***** */
		int d1 = (calBeginDate.get(calBeginDate.DAY_OF_MONTH));
		int d2 = (calEndDate.get(calEndDate.DAY_OF_MONTH));
		int d = 0;

		if (d2 < d1) {
			m -= 1;
			d = 30 - d1 + d2;
		} else {
			d = d2 - d1;
		}
		/** **** 计算天数End ***** */

		// 判断天数是否算1个月
		if (d >= intDay) {
			m += 1;
			d = 0;
		}

		/** **** 组合返回值Begin ***** */
		String strDate = "";
		if (m < 10) {
			strDate = "0" + m;
		} else {
			strDate = m + "";
		}

		if (flg) {
			if (d < 10) {
				strDate += "-0" + d;
			} else {
				strDate += "-" + d;
			}
		}
		/** **** 组合返回值End ***** */
		return strDate;

	}

	/**
	 * 取两个日期天数之差，strDate1-strDate2 例如：2000-01-31 - 2000-02-01，结果：1天
	 * 
	 * @author 郑斌
	 * @param strBeginDate
	 *            开始日期
	 * @param strEndDate
	 *            结束日期
	 * @return 日，格式：1
	 */
	public static int getDateDiff2(String strBeginDate, String strEndDate) {
		if (strBeginDate.equals("")) {
			return 0;
		}
		Calendar calBeginDate = getCalendarByString(strBeginDate);
		Calendar calEndDate = getCalendarByString(strEndDate);

		/** **** 计算月份Begin ***** */
		int y = (calEndDate.get(calEndDate.YEAR)) - (calBeginDate.get(calBeginDate.YEAR));
		int m = (calEndDate.get(calEndDate.MONTH)) - (calBeginDate.get(calBeginDate.MONTH));
		m += y * 12;
		/** **** 计算月份End ***** */

		/** **** 计算天数Begin ***** */
		int d1 = (calBeginDate.get(calBeginDate.DAY_OF_MONTH));
		int d2 = (calEndDate.get(calEndDate.DAY_OF_MONTH));
		int d = 0;

		if (d2 < d1) {
			m -= 1;
			d = 31 - d1 + d2;
		} else {
			d = d2 - d1;
		}
		/** **** 计算天数End ***** */

		d += m * 31;
		return d;

	}

	/**
	 * 根据日期，返回日期中月份部分的天数数量 例如：2010-02-01，返回2月份的最高天数28天
	 * 
	 * @author 郑斌
	 * @param strDate
	 *            日期
	 * @return 天数，28
	 */
	public static int getDays(String strDate) {
		/** **** 获取年、月Begin ***** */
		int y = Integer.parseInt(strDate.substring(0, 4));
		int m = Integer.parseInt(strDate.substring(5, 7));
		/** **** 计算月份End ***** */

		/** **** 计算天数Begin ***** */
		int d = 30;
		if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
			d = 31;
		} else if (m == 2) {
			// 闰年
			if (y % 4 == 0 && y % 100 != 0) {
				// 闰月
				d = 29;
			} else {
				d = 28;
			}
		}
		/** **** 计算天数End ***** */
		return d;
	}

	/**
	 * 根据日期，返回两个日期相隔的月份数和天数
	 * 
	 * @param strBeginDate
	 *            开始时间
	 * @param strEndDate
	 *            结束时间
	 * @param flg
	 * @return
	 */
	public static String getDateDiffNew(String strBeginDate, String strEndDate, int intDay, boolean flg) {
		Calendar calBeginDate = getCalendarByString(strBeginDate);
		Calendar calEndDate = getCalendarByString(strEndDate);
		int y = (calEndDate.get(calEndDate.YEAR)) - (calBeginDate.get(calBeginDate.YEAR));
		int endDay = calEndDate.get(calEndDate.DAY_OF_MONTH);
		int beginDay = calBeginDate.get(calBeginDate.DAY_OF_MONTH);
		int m = 0;
		int d = 0;
		/** **** 计算月份Begin ***** */
		y = (calEndDate.get(calEndDate.YEAR)) - (calBeginDate.get(calBeginDate.YEAR));
		m = (calEndDate.get(calEndDate.MONTH)) - (calBeginDate.get(calBeginDate.MONTH));
		m += y * 12;
		d = 0;
		if (beginDay == 1) {
			if (getDays(strEndDate) == endDay) {
				m = m + 1;
				d = 0;
			} else {
				d = endDay;
			}
		} else {
			if (endDay == beginDay) {
				d = 0;
			} else if (beginDay > endDay) {
				m = m - 1;
				d = beginDay - endDay;
			} else if (endDay > beginDay) {
				d = endDay - beginDay;
			}
		}
		// 判断天数是否算1个月
		if (d >= intDay) {
			m += 1;
			d = 0;
		}

		/** **** 组合返回值Begin ***** */
		String strDate = "";
		if (m < 10) {
			strDate = "0" + m;
		} else {
			strDate = m + "";
		}

		if (flg) {
			if (d < 10) {
				strDate += "-0" + d;
			} else {
				strDate += "-" + d;
			}
		}
		/** **** 组合返回值End ***** */
		return strDate;

	}

	/**
	 * 根据一个给定格式为yyyy-MM-dd的日期，以及指定相应的参数type来表明是获取当前月份还是下一个月份的月份天数最大值
	 * 
	 * @param date
	 *            指定的日期
	 * @param type
	 *            接受以下值："now"、"next" 分别表明获取当前月、下个月. 提示：只接收小写
	 * @return
	 */
	public static int getDayOfMonthByDate(String date, String type) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(format.parse(date));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		int day = 0;
		if (type.equals("now")) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else if (type.equals("next")) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, 1);
		}
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		day = cal.get(Calendar.DAY_OF_MONTH);
		format = null;
		cal = null;
		return day;
	}

	/**
	 * hh 2010-11-15
	 * 根据起始日期和终止日期获取月差和日差。年差换算成月差，返回格式为：000-00-01-00-01（月差-日差1-月天数1-日差2-月天数2）
	 * 出现2的情况是当多余的天数夸在两个月份，不存在日差时对应默认月天数为1。这里的起始日期必须小于结束日期，否则出错。
	 * 
	 * @param strBeginDate
	 *            起始日期
	 * @param strEndDate
	 *            结束日期
	 * @return 月差和日期组成的特殊格式的字符串
	 */
	public static String getDateDiff(String strBeginDate, String strEndDate) {
		String monthDif = "000";
		String dayDif1 = "00";
		String dayMonth1 = "01";
		String dayDif2 = "00";
		String dayMonth2 = "01";
		try {
			int yearDiff = Integer.parseInt(strEndDate.substring(0, 4)) - Integer.parseInt(strBeginDate.substring(0, 4));
			int monthDiff = Integer.parseInt(strEndDate.substring(5, 7)) - Integer.parseInt(strBeginDate.substring(5, 7));
			int dayDiff = Integer.parseInt(strEndDate.substring(8, 10)) - Integer.parseInt(strBeginDate.substring(8, 10));
			int monthCount = yearDiff * 12 + monthDiff;

			int intBeginMonth = Integer.parseInt(strBeginDate.substring(5, 7));// 开始日期月份
			int intEndMonth = Integer.parseInt(strEndDate.substring(5, 7));// 结束日期月份
			int intBeginDay = Integer.parseInt(strBeginDate.substring(8, 10)); // 开始日期天数
			int intEndDay = Integer.parseInt(strEndDate.substring(8, 10)); // 结束日期天数
			// 增加一个特殊情况的判断，当起始日期是1号的时候
			if (intBeginDay == 1) {
				if ((dayDiff + 1) == getDayOfMonthByDate(strEndDate, "now")) {
					monthCount++;
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(format.parse(strBeginDate));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			// 先判断是否大于一个月，小于等于一个月另外处理。
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			if (intBeginDay == getDayOfMonthByDate(strBeginDate, "now")) {// 考虑末尾情况：2001-02-28至2001-03-30
				// 2001-04-30至2001-05-30
				// 2001-05-31至2001-06-29
				cal.set(Calendar.DAY_OF_MONTH, getDayOfMonthByDate(format.format(cal.getTime()), "now") - 1);
			}

			if (cal.getTime().getTime() > format.parse(strEndDate).getTime()) {// 增加一个月后的日期
				// >
				// 结束日期。小于一个月
				if (strBeginDate.substring(5, 7).equals(strEndDate.substring(5, 7))) {
					String dateDiff = "";
					if ((dayDiff + 1) < 10) {
						dateDiff = "000-0" + (dayDiff + 1) + "-" + getDayOfMonthByDate(strBeginDate, "now") + "-00-01";
					} else {
						dateDiff = "000-" + (dayDiff + 1) + "-" + getDayOfMonthByDate(strBeginDate, "now") + "-00-01";
					}
					return dateDiff;
				} else {
					monthDif = "000";
					dayDif1 = "00";
					dayMonth1 = "01";
					dayDif2 = "00";
					dayMonth2 = "01";
					int intdayMonth1 = getDayOfMonthByDate(strBeginDate, "now");
					int intdayMonth2 = getDayOfMonthByDate(strEndDate, "now");
					int intdayDif1 = intdayMonth1 - Integer.parseInt(strBeginDate.substring(8, 10)) + 1;
					int intdayDif2 = intEndDay;
					if (intdayDif1 < 10) {
						dayDif1 = "0" + intdayDif1;
					} else {
						dayDif1 = "" + intdayDif1;
					}
					if (intdayDif2 < 10) {
						dayDif2 = "0" + intdayDif2;
					} else {
						dayDif2 = "" + intdayDif2;
					}
					dayMonth1 = "" + intdayMonth1;
					dayMonth2 = "" + intdayMonth2;
					return monthDif + "-" + dayDif1 + "-" + dayMonth1 + "-" + dayDif2 + "-" + dayMonth2;
				}
			} else if (cal.getTime().getTime() == format.parse(strEndDate).getTime()) {// 刚好等于一个月
				return "001-00-01-00-01";
			}

			// 恢复初始设置，进行大于一个月时的计算。
			cal.setTime(format.parse(strBeginDate));
			cal.add(Calendar.MONTH, monthCount);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			if (intBeginDay == getDayOfMonthByDate(strBeginDate, "now")) {// 考虑末尾情况：2001-02-28至2001-03-30
				// 2001-04-30至2001-05-30
				// 2001-05-31至2001-06-29
				cal.set(Calendar.DAY_OF_MONTH, getDayOfMonthByDate(format.format(cal.getTime()), "now") - 1);
			}
			int comp = format.parse(strEndDate).compareTo(cal.getTime());// 结束时间
			// 比上
			// 增加了几个月的起始时间，大于则为1，等于为0，小于为-1
			if (monthCount < 10) {
				monthDif = "00" + monthCount;
			} else if (monthCount < 100) {
				monthDif = "0" + monthCount;
			} else {
				monthDif = "" + monthCount;
			}
			if (comp == 0) {

			} else if (comp == 1) {// 相差N个月还多出一点
				String changedDate = format.format(cal.getTime());
				if (changedDate.substring(5, 7).equals(strEndDate.substring(5, 7))) {// 多出来的天数在同一个月内
					int intdayDif1 = intEndDay - Integer.parseInt(changedDate.substring(8, 10));
					if (intdayDif1 < 10) {
						dayDif1 = "0" + intdayDif1;
					} else {
						dayDif1 = "" + intdayDif1;
					}
					dayMonth1 = "" + getDayOfMonthByDate(changedDate, "now");
				} else {
					int intdayMonth1 = getDayOfMonthByDate(changedDate, "now");
					int intdayMonth2 = getDayOfMonthByDate(changedDate, "next");
					int intdayDif1 = intdayMonth1 - Integer.parseInt(changedDate.substring(8, 10));
					int intdayDif2 = intEndDay;
					if (intdayDif1 < 10) {
						dayDif1 = "0" + intdayDif1;
					} else {
						dayDif1 = "" + intdayDif1;
					}
					if (intdayDif2 < 10) {
						dayDif2 = "0" + intdayDif2;
					} else {
						dayDif2 = "" + intdayDif2;
					}
					dayMonth1 = "" + intdayMonth1;
					dayMonth2 = "" + intdayMonth2;
				}
			} else if (comp == -1) {// 相差N-1个月多出一点
				monthCount--;
				if (monthCount < 10) {
					monthDif = "00" + monthCount;
				} else if (monthCount < 100) {
					monthDif = "0" + monthCount;
				} else {
					monthDif = "" + monthCount;
				}
				cal.setTime(format.parse(strBeginDate));
				cal.add(Calendar.MONTH, monthCount);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				if (intBeginDay == getDayOfMonthByDate(strBeginDate, "now")) {// 考虑末尾情况：2001-02-28至2001-03-30
					// 2001-04-30至2001-05-30
					// 2001-05-31至2001-06-29
					cal.set(Calendar.DAY_OF_MONTH, getDayOfMonthByDate(format.format(cal.getTime()), "now") - 1);
				}
				String changedDate = format.format(cal.getTime());
				if (changedDate.substring(5, 7).equals(strEndDate.substring(5, 7))) {// 多出来的天数在同一个月内
					int intdayDif1 = intEndDay - Integer.parseInt(changedDate.substring(8, 10));
					if (intdayDif1 < 10) {
						dayDif1 = "0" + intdayDif1;
					} else {
						dayDif1 = "" + intdayDif1;
					}
					dayMonth1 = "" + getDayOfMonthByDate(changedDate, "now");
				} else {
					int intdayMonth1 = getDayOfMonthByDate(changedDate, "now");
					int intdayMonth2 = getDayOfMonthByDate(changedDate, "next");
					int intdayDif1 = intdayMonth1 - Integer.parseInt(changedDate.substring(8, 10));
					int intdayDif2 = intEndDay;
					if (intdayDif1 < 10) {
						dayDif1 = "0" + intdayDif1;
					} else {
						dayDif1 = "" + intdayDif1;
					}
					if (intdayDif2 < 10) {
						dayDif2 = "0" + intdayDif2;
					} else {
						dayDif2 = "" + intdayDif2;
					}
					dayMonth1 = "" + intdayMonth1;
					dayMonth2 = "" + intdayMonth2;
				}
			}
			cal = null;
			format = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return monthDif + "-" + dayDif1 + "-" + dayMonth1 + "-" + dayDif2 + "-" + dayMonth2;
	}

	/**
	 * xzb 20140507 从东莞搬过来的 ,在单位报表上报功能中用到的
	 * 计算strDate2和strDate1这两个日期相隔的月份(注意时间格式为yyyy-MM-dd)
	 * 
	 * @param strDate1
	 *            开始时间
	 * @param strDate2
	 *            后面的时间
	 * @return
	 */
	public static int getMonthInterval(String strDate1, String strDate2) {
		String arrDate1[] = strDate1.split("-");
		String arrDate2[] = strDate2.split("-");
		int yearInterval = Integer.parseInt(arrDate2[0]) - Integer.parseInt(arrDate1[0]);// 年份差
		int monthInterval = Integer.parseInt(arrDate2[1]) - Integer.parseInt(arrDate1[1]);// 月份差
		monthInterval = monthInterval + yearInterval * 12;
		// 如果strDate2的日要比strDate1的大，那么月份要加一，不足一个月的要按一个月算，比如2010-12-13~2010-06-24，相隔月份算是6个月
		if (Integer.parseInt(arrDate2[2]) >= Integer.parseInt(arrDate1[2])) {
			monthInterval = monthInterval + 1;
		}
		return monthInterval;
	}

	/**
	 * 取两个日期月份之差，strDate2-strDate1 例如：2010-02-01 - 2010-02-28，结果：1
	 * 
	 * @param strDate1
	 *            开始日期
	 * @param strDate2
	 *            结束日期
	 * @return 月份差,不是完整月数返回-1
	 */
	// 2014-05-09 从小慧的前台转过来
	public static int getMonthDiffNewSend(String strDate1, String strDate2) {
		int m1 = Integer.parseInt(strDate1.substring(5, 7));
		int m2 = Integer.parseInt(strDate2.substring(5, 7));
		int y1 = Integer.parseInt(strDate1.substring(0, 4));
		int y2 = Integer.parseInt(strDate2.substring(0, 4));
		int d1 = Integer.parseInt(strDate1.substring(8));
		int d2 = Integer.parseInt(strDate2.substring(8));
		int month = (y2 - y1) * 12 + (m2 - m1);
		int day = d2 - d1 + 1;

		// 月份相同时，如果两个日期相差的天数=该月的天数，那么月差+1(2010-02-01~~2010-02-28)
		if ((m2 - m1 == 0) && (day == getDays(strDate1))) {
			month = month + 1;
		}
		// 如果结束时间的日<开始时间的日，并且结束时间的月份不是2、结束时间不是当月的最后一天，那么月差为0
		// 2010-05-16~~2010-06-10 0
		else if ((d2 < d1) && (m2) != 2 && (d2 != getDays(strDate2)) && day != 0) {
			month = month - 1;
		}
		// 月份相差1,如果结束时间的日<开始时间的日，且结束时间的月份为2、结束时间不是当月的最后一天,那么根据天差>=29为一个月，否则不足一个月
		// 2010-01-12 ~~ 2010-02-12 结果0 2010-01-31 ~~ 2010-02-28 结果1
		else if ((m2 - m1 == 1) && (d2 < d1) && (m2 == 2) && (day < 28) && (d2 != getDays(strDate2))) {
			month = month == 0 ? 0 : month - 1;
		} else if ((m2 - m1 == 1) && (d2 < d1) && (m2 == 2) && (day >= 28) && (d2 != getDays(strDate2))) {
			month = month + 1;
		} else if (day == getDays(strDate2)) {
			month = month + 1;
		}
		if ((day == 0 || day == getDays(strDate2)) && (m2) != 2 && (m1) != 2) {

		} else if ((day == 0 || day >= 28) && (m2 == 2 || m1 == 2)) {

		} else {
			month = -1;
		}

		return month;
	}

	/**
	 * 当前日期向前或向后加减一段天数
	 * 
	 * @param dayNum
	 *            加减的天数，正数为加，即日期往后推，负数为减，日期往前推
	 * @return
	 */
	public static String todayPlusOrMinusDay(int dayNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSystem = new Date(System.currentTimeMillis());
		Date afterDate = plusOrMinusDay(dateSystem, dayNum);
		return sdf.format(afterDate);
	}

	/**
	 * 对某个日期加减一段天数
	 * 
	 * @param date
	 *            日期
	 * @param dayNum
	 *            加减的天数，正数为加，即日期往后推，负数为减，日期往前推
	 * @return
	 */
	public static Date plusOrMinusDay(Date date, int dayNum) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DATE, dayNum);
		return cl.getTime();
	}
	
	/**
	 * 根据时间戳以及指定格式返回日期
	 * 
	 * @param timeMillis
	 *            时间戳
	 * @param type
	 *            指定格式类型 1：yyyyMMdd，2：yyyyMMddHHmmss，3：yyyy-MM-dd，4：yyyy-MM-dd
	 *            HH:mm:ss,5：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @return
	 */
	public static String getDateTimeByTimeMillis(long timeMillis, int type) {
		if (type == 1) {
			return yyyymmddsdf.format(timeMillis);
		} else if (type == 2) {
			return yyyymmddhhmmsssdf.format(timeMillis);
		} else if (type == 3) {
			return yyyygMMgddsdf.format(timeMillis);
		} else if (type == 4) {
			return ymdhmssdf.format(timeMillis);
		} else {
			return ymdhmsssdf.format(timeMillis);
		}
	}

	/**
	 * 根据指定格式以及日期返回时间戳
	 * 
	 * @param date
	 *           指定格式的日期数据格式的指定日期
	 * @param type
	 *            指定格式类型 1：yyyyMMdd，2：yyyyMMddHHmmss，3：yyyy-MM-dd，4：yyyy-MM-dd
	 *            HH:mm:ss,5：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @return
	 */
	public static long getTimeMillisByDate(String date, int type) {
		Calendar cal = Calendar.getInstance();
		try {
			if (type == 1) {
				cal.setTime(yyyymmddsdf.parse(date));
			} else if (type == 2) {
				cal.setTime(yyyymmddhhmmsssdf.parse(date));
			} else if (type == 3) {
				cal.setTime(yyyygMMgddsdf.parse(date));
			} else if (type == 4) {
				cal.setTime(ymdhmssdf.parse(date));
			} else {
				cal.setTime(ymdhmsssdf.parse(date));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cal.getTimeInMillis();
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(getDateTimeByTimeMillis(System.currentTimeMillis(),5));
		System.out.println(getTimeMillisByDate("2016-06-14 10:59:12",4));
		System.out.println(getDateTimeByTimeMillis(1470965993317l,5));
	}
}
