package com.jiuyuan.utils;

public class SqlUtil {
	/**
	 * 数据库或代码中经常有这样的格式数据：[aaa,bbb,ccc],当需要向数据查询时，调用此方法可将数据拼接成适合in查询的格式。
	 * @param fieldArr
	 * @return
	 */
	public static String mosaicInStrSql(String[] fieldArr) {
		String return_sql = "";
 
		for (int i = 0; i < fieldArr.length; i++) {
			return_sql += ",'" + fieldArr[i] + "'";
		}
		if (return_sql.length() > 0) {
			return_sql = return_sql.substring(1);
		}

		return return_sql;
	}

	/**
	 * 数据库或代码中经常有这样的格式数据：aaa,bbb或aaa|ccc,当需要向数据查询时，调用此方法可将数据拼接成适合in查询的格式。
	 * 
	 * @param separator
	 *            分隔符
	 * @param arrStr
	 *            数组形式的字符串
	 * @return 'aaa','bbb'
	 */
	public static String mosaicInStrSql(String separator, String arrStr) {
		String return_sql = "";
		arrStr = arrStr.replace(separator, ",");
		String[] pkArr = arrStr.split(",");
		return_sql = mosaicInStrSql(pkArr);

		return return_sql;
	}

}
