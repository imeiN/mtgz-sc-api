package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_sms_log")
public class SmsLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 操作人
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    private Date stime;

    /**
     * 必填参数。用户签名
     */
    private String sign;

    /**
     * 必填参数。固定值 pt
     */
    private String type;

    /**
     * 可选参数。扩展码，用户定义扩展码，只能为数字
     */
    private String extno;

    /**
     * 1成功 0失败
     */
    @Column(name = "send_status")
    private Integer sendStatus;

    /**
     * 发送编号
     */
    @Column(name = "send_id")
    private String sendId;

    /**
     * 无效号码数
     */
    @Column(name = "invalid_num")
    private Integer invalidNum;

    /**
     * 成功提交数
     */
    @Column(name = "success_num")
    private Integer successNum;

    /**
     * 黑名单数
     */
    @Column(name = "black_num")
    private Integer blackNum;

    /**
     * 返回消息
     */
    @Column(name = "return_msg")
    private String returnMsg;

    /**
     * 必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    @Column(name = "sms_code")
    private String smsCode;

    @Column(name = "sms_text")
    private String smsText;

    /**
     * 必填参数。手机号码。多个以英文逗号隔开
     */
    private String phone;


    private Long logDate;
}