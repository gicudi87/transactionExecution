package com.transaction.spin.utils;

import org.springframework.stereotype.Component;

@Component
public final class Utils {

	public static final Double MIN_AMOUNT = 1.00;
	public static final Double MAX_AMOUNT = 10000.00;
	public static final String TYPE_DEBIT = "DEBIT";
	public static final String CURRENCY = "MXN";
	public static final String STATUS_APPROVED = "APPROVED";
	public static final String STATUS_REJECTED = "REJECTED";
	public static final String STATUS_PROCESS = "PROCESS";
	public static final String STATUS_EXECUTED = "EXECUTED";
	public static final String STATUS_FAILED = "FAILED";
	public static final String URL_PROVIDER = "/provider/v1/execute";
	public static final String MESSAGE_OK = "Transferencia recibida";
	public static final String MESSAGE_FAILED = "Transferencia fallida";
}
