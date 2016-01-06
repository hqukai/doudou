package com.hqukai.util;

import org.apache.commons.lang.math.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @Description: 1.BigDecimal运算精度为4位 2.由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 *               确的浮点数运算，包括加减乘除和四舍五入。
 */

public class ArithUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;
	// 默认运算精度
	private static final int DEF_RESULT_SCALE = 4;

	// 这个类不能实例化
	private ArithUtil() {
	}

	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {

		return round(b1.add(b2), DEF_RESULT_SCALE);
	}

	public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {

		return round(b1.subtract(b2), DEF_RESULT_SCALE);
	}

	public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {

		return round(b1.multiply(b2), DEF_RESULT_SCALE);
	}

	public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
		return b1.divide(b2, DEF_RESULT_SCALE, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		// return b1.add(b2).doubleValue();
		return round(b1.add(b2).doubleValue(), 2);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.subtract(b2).doubleValue(), 2);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */

	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.multiply(b2).doubleValue(), 2);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue(), 2);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static BigDecimal round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}

		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位格式。
	 * 
	 * @param v
	 *            需要格式化的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 格式化后的结果
	 */
	public static String format(double v, int scale) {
		StringBuffer count = new StringBuffer();
		count.append("0.");
		for (int i = 0; i < scale; i++) {
			count.append("0");
		}
		DecimalFormat format = new DecimalFormat(count.toString());
		return format.format(v);
	}

	// 添加千位符
	public static String thousandCharacterformat(double v, int scale) {
		StringBuffer count = new StringBuffer();
		count.append("#,##0.");
		for (int i = 0; i < scale; i++) {
			count.append("0");
		}
		DecimalFormat format = new DecimalFormat(count.toString());
		format.setRoundingMode(RoundingMode.DOWN);
		return format.format(v);
	}

	public static BigDecimal getRandomBigDecimal() {
		Double ran = ArithUtil.mul(RandomUtils.nextDouble(), 10000);
		BigDecimal res = round(new BigDecimal(ran), 2);
		return res;
	}

	public static void main(String args[]) {
		System.out.println(format(5.10, 3));
		System.out.println(round(getRandomBigDecimal(), 2));
		System.out.println(round(new BigDecimal("1.23"), 0));
	}
}