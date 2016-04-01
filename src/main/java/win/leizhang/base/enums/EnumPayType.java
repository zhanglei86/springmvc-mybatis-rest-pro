package win.leizhang.base.enums;

/**
 * 支付类型
 *
 */
public enum EnumPayType {
	WECHATPAY(0), // 微信支付
	ALIPAY(1), // 支付宝支付
	REMAININGPAY(2), // 余额支付
	CASHVOUCHERPAY(3), // 现金券支付
	MIXPAY(4);// 混合支付

	private Integer value;

	private EnumPayType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static EnumPayType setValue(String str) {
		return valueOf(str);
	}

}
