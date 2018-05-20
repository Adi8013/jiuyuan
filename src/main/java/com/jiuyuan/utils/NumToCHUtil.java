package com.jiuyuan.utils;

import java.math.BigDecimal;

public class NumToCHUtil {

	/**
	 * 汉语中数字大写
	 */
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	/**
	 * 汉语中货币单位大写，这样的设计类似于占位符
	 */
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "兆", "拾", "佰", "仟" };
	/**
	 * 特殊字符：整
	 */
	private static final String CN_FULL = "整";
	/**
	 * 特殊字符：负
	 */
	private static final String CN_NEGATIVE = "负";
	/**
	 * 金额的精度，默认值为2
	 */
	private static final int MONEY_PRECISION = 2;
	/**
	 * 特殊字符：零元整
	 */
	private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

	/**
	 * 把输入的金额转换为汉语中人民币的大写
	 * 
	 * @param numberOfMoney
	 *            输入的金额
	 * @return 对应的汉语大写
	 */
	public static String numToCN(BigDecimal numberOfMoney) {
		StringBuffer sb = new StringBuffer();
		//signum()方法返回-1，0，或1，此BigDecimal的值分类为负，零或正值。
		// -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
		// positive.
		int signum = numberOfMoney.signum();
		// 零元整的情况
		if (signum == 0) {
			return CN_ZEOR_FULL;
		}
		// 这里会进行金额的四舍五入
		// movePointRight(int n)方法返回一个BigDecimal，它等效于将该值的小数点向右移动n位
		long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, BigDecimal.ROUND_UP).abs().longValue();
		// 得到小数点后两位值
		long scale = number % 100;
		int numUnit = 0;
		int numIndex = 0;
		boolean getZero = false;
		// 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
		if (!(scale > 0)) {
			numIndex = 2;
			number = number / 100;
			getZero = true;
		}
		if ((scale > 0) && (!(scale % 10 > 0))) {
			numIndex = 1;
			number = number / 10;
			getZero = true;
		}
		int zeroSize = 0;
		while (true) {
			if (number <= 0) {
				break;
			}
			// 每次获取到最后一个数
			numUnit = (int) (number % 10);
			if (numUnit > 0) {
				if ((numIndex == 9) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
				}
				if ((numIndex == 13) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
				}
				sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				getZero = false;
				zeroSize = 0;
			} else {
				++zeroSize;
				if (!(getZero)) {
					sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				}
				if (numIndex == 2) {
					if (number > 0) {
						sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
					}
				} else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				}
				getZero = true;
			}
			// 让number每次都去掉最后一个数
			number = number / 10;
			++numIndex;
		}
		// 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
		if (signum == -1) {
			sb.insert(0, CN_NEGATIVE);
		}
		// 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
		if (!(scale > 0)) {
			sb.append(CN_FULL);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		double money = 330908.11;
		double money2 = 26871.29;
		double money3 = 950.00;
		BigDecimal numberOfMoney = new BigDecimal(Double.toString(money));
		BigDecimal number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(1, BigDecimal.ROUND_DOWN);
		System.out.println(number);
		/*
		String s = NumToCHUtil.numToCN(numberOfMoney);
		System.out.println("你输入的金额为：【" + money + "】   #--# [" + s.toString() + "]");
		System.out.println("你输入的金额为：【" + money2 + "】   #--# [" + NumToCHUtil.numToCN(BigDecimal.valueOf(money2)).toString() + "]");
		System.out.println("你输入的金额为：【" + money3 + "】   #--# [" + NumToCHUtil.numToCN(BigDecimal.valueOf(money3)).toString() + "]");*/
		
		/*BigDecimal num1 = new BigDecimal("123.12345");
		System.out.println("num1***" + num1);
		System.out.println(num1.movePointRight(MONEY_PRECISION).setScale(0, 4));
		BigDecimal num2 = new BigDecimal(123.12345);
		System.out.println("num2***" + num2);
		System.out.println(num2.movePointRight(MONEY_PRECISION));
		BigDecimal num3 = new BigDecimal(Double.toString(123.12345));
		System.out.println("num3***" + num3);
		System.out.println(num3.movePointRight(MONEY_PRECISION));
		BigDecimal num4 = BigDecimal.valueOf(123.12345);
		System.out.println("num4***" + num4);
		System.out.println(num4.movePointRight(MONEY_PRECISION));*/
		/*Scanner sc = new Scanner(System.in);
		System.out.print("请输入第1个数字：");
		double money = sc.nextDouble();
		// BigDecimal numberOfMoney = new BigDecimal(money);
		System.out.print("请输入第2个数字：");
		int divide = sc.nextInt();
		System.out.println("结果：" + (money % divide));*/

	}
}
