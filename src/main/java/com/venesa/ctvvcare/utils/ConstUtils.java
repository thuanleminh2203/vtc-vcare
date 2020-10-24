package com.venesa.ctvvcare.utils;

import org.springframework.http.HttpMethod;

public class ConstUtils {

	public static final String URL_GATEWAY = "/crm-mobio/loyalty/api/v1/";
	public static final String OK = "OK";
	public static final String ERR = "ERR";
	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	public static final String FORBIDDEN = "FORBIDDEN";

	public static final String HTTP_POST = "POST";
	public static final String HTTP_PUT = "PUT";
	public static final String HTTP_GET = "GET";
	public static final String HTTP_DELETE = "DELETE";

	public static final String ERR_UNAUTHORIZED = "Token is invalid or is expired. Please login again.";
	public static final String ERR_FORBIDDEM = "Access is denied.";

	public static final int SUCCSESS = 0;
	public static final int ERROR = 1;
	public static final String SUCCSESS_MESS = "Thành công";
	public static final String NOT_EXIST_USER = "Tai khoan nay khong ton tai";
	public static final String DUPLICATE_IDENTIFY_CODE = "Identify Code da duoc su dung";
	public static final String LOGGIN = "Tai khoan dang duoc dang nhap";
	public static final String ERR_BUSINESS = "err business";
	public static final String DUPLICATE_REFERENCE_ID = "Ma gioi thieu da ton tai";
	public static final String DUPLICATE_REFERENCE_PHONE = "So dien thoai nay da duoc gioi thieu";
	public static final String NOT_EXIST_BRANCH_ID = "Ma trung tam nay khong ton tai";


	public static final String REGEX_NUMBER_PHONE = "^[0-9]{10}$";
	public static final String REGEX_EMAIL = "^(.+)@(.+)$";
	public static final String REGEX_DATE = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
	public static final String REGEX_IDENTIFY_CODE = "[0-9]{6,}";

	public static final int SEND_MESSAGE_ERROR = 0;
	public static final int SEND_MESSAGE_SUCCESS = 1;
	public static final String SEND_SUCCESS = "Thành công";


	public static final int CALL_API_TO_MOBIO = 2;

	public static final int CALL_API_TO_CRM = 1;
	public static final String GATEWAY_CALL = "gateway";

	enum StatusContract {
		TREATTING(1), DONE(2), CAST_PAYMENT_REQUIRED(3), EXPIRED(4), TRANSFER(5), RETURNED(6), RESERVED(7);

		private int value;

		StatusContract(int value) {
			this.value = value;
		}
	}

	//action for message
	public static final HttpMethod CREATE = HttpMethod.POST;
	public static final HttpMethod UPDATE = HttpMethod.PUT;
	public static final HttpMethod DELETE = HttpMethod.DELETE;
	public static final HttpMethod GET = HttpMethod.GET;
	//type message
	public static final String CONTRACT = "contract";
	public static final String BOOKING = "booking";
	public static final String CUSTOMER = "customer";
	public static final String THERAPY = "therapy";
	public static final String TENANT = "tenant";
	public static final String TRANSACTION = "transaction";
	public static final String TRANSACTION_STATUS = "transaction-status";

	public static final String INTRODUCE_FRIEND = "friend";

	//status transaction
	public static final int OFF_TRANSACTION = 0;
	public static final int ON_TRANSACTION = 1;
	public static final int FINISH_TRANSACTION = 2;

	public static final Short DA_DAT_DUOC_LICH = 5;
	public static final Short CAN_XAC_NHAN = 11;
}
