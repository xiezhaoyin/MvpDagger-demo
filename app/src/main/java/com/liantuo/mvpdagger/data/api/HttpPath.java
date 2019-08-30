package com.liantuo.mvpdagger.data.api;

public class HttpPath {

    /**
     * 登录
     */
    public static final String LOGIN = "login";

    /**
     * 会员支付授权码
     */
    public static final String MEMBER_PAYAUTHCODE = "member/payAuthCode";

    /**
     *  二维码主扫（会员主扫支付、会员支付授权码充值，微信主扫，支付宝主扫）
     */
    public static final String PRECREATE = "precreate";

    /**
     * 聚合支付(被扫)
     */
    public static final String PAY = "pay";

    /**
     * 聚合支付(主扫)
     */
    public static final String JSPAY = "jspay";

    /**
     * 订单查询
     */
    public static final String PAY_QUERY = "pay/query";

    /**
     * 帐单列表
     */
    public static final String BILL = "bill";

    /**
     * 订单退款
     */
    public static final String REFUND = "refund";

    /**
     * 人脸认证
     */
    public static final String AUTH_INFO = "facePayAuth";

    /**
     * 人脸支付
     */
    public static final String FACE_PAY = "facePay";

    /**
     * 会员查询
     */
    public static final String MEMBER_QUERY = "member/get";

    /**
     * 会员注册
     */
    public static final String MEMBER_REGISTER = "member/regist";

    /**
     * 会员修改
     */
    public static final String MEMBER_MODIFY = "member/modify";

    /**
     * 会员卡券列表  /coupon/couponList   /member/couponList
     */
    public static final String MEMBER_COUPONLIST = "coupon/couponList";

    /**
     * 会员卡券详情
     */
    public static final String COUPON_COUPONINFO = "coupon/couponInfo";

    /**
     * 手动核销卡券
     */
    public static final String COUPON_CONSUME = "coupon/consume";

    /**
     * 商户卡券列表
     */
    public static final String MERCHANT_COUPONLIST = "merchant/couponList";

    /**
     * 商户卡券详情
     */
    public static final String MERCHANT_COUPONINFO = "merchant/couponInfo";

    /**
     * 充值营销规则
     */
    public static final String MARKET_RECHARGEPRIZERULE = "market/rechargePrizeRule";

    /**
     * app升级
     */
    public static final String VERSION_UPGRADE = "merchant/versionUpgrade";


}
