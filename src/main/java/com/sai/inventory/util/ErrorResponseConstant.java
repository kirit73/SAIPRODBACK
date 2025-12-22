package com.sai.inventory.util;

public class ErrorResponseConstant {

    public static final int INVALID_DATE_CD = 1001;
    public static final String INVALID_DATE_MSG = "Invalid date format";
    public static final int ID_NOT_FOUND_CD = 1002;
    public static final String ID_NOT_FOUND_MSG = "Id not found";
    public static final int ID_ALREADY_EXIST_CD = 1003;
    public static final String ID_ALREADY_EXIST_MSG = "Id already exist.";
    public static final int INVALID_PARAM_TYPE_CD = 1004;
    public static final String INVALID_PARAM_TYPE = "Invalid Param type";
    public static final int ITEM_CD_EXIST_CD = 1005;
    public static final String ITEM_CD_EXIST = "Item Code already exist";
    public static final int ITEM_CANNOT_EMPTY_CD = 1006;
    public static final String ITEM_CANNOT_EMPTY = "Item list must not be empty";
    public static final int ITEM_CD_NOT_EXIST_CD = 1006;
    public static final String ITEM_CD_NOT_EXIST = "Item Code doest not exist.";
    public static final int ITEM_QNTY_GREAT_CD = 1007;
    public static final String ITEM_QNTY_GREAT_MSG = "Item quantity is greater than available inventory.";

    public static final int DATE_EMPTY_CD = 1008;
    public static final String DATE_EMPTY_MSG = "Date cannot be empty.";
    public static final int DEMAND_NOT_EMPTY_CD = 1009;
    public static final String DEMAND_NOT_EMPTY_MSG = "Demand note can not be blank";
    public static final int QNTY_LESS_THAN_ONE_CD = 1010;
    public static final String QNTY_LESS_THAN_ONE_MSG = "Quantity cannot be less than 1";

    public static final int DAYS_THAN_ONE_CD = 1011;
    public static final String DAYS_THAN_ONE_MSG = "Days cannot be less than 1";

    public static final int ISSUE_DT_NOT_EMPTY_CD = 1012;
    public static final String ISSUE_DT_NOT_EMPTY_MSG = "Issue note can not be blank";
    public static final int INVALID_PROCESS_TYPE = 1013;
    public static final String INVALID_PROCESS_TYPE_MSG = "Invalid process type. Allowed values are IRP, PO, IOP, NIRP";
    public static final String INVALID_PROCESS_TYPE_ISN_MSG = "Invalid process type. Allowed values are IRP, NIRP";
    public static final int GATE_PASS_NOT_EMPTY_CD = 1014;
    public static final String GATE_PASS_NOT_EMPTY_MSG = "Gate Pass can not be blank";

    public static final int INVALID_PROCESS_ID = 1015;
    public static final String INVALID_PROCESS_ID_MSG = "Invalid process id";
    public static final int CURR_PROCESS_STAGE_INVALID = 1016;
    public static final String CURR_PROCESS_STAGE_INVALID_MSG = "Stage of this process is invalid to perform this task. Please fill all required previous forms..";
    public static final int GEN_NAME_NOT_EMPTY_CD = 1017;
    public static final String GEN_NAME_NOT_EMPTY_MSG = "Generate By name can not be blank.";
    public static final int APR_NAME_NOT_EMPTY_CD = 1018;
    public static final String APR_NAME_NOT_EMPTY_MSG = "Approved By name can not be blank.";
    public static final int ISS_NAME_NOT_EMPTY_CD = 1019;
    public static final String ISS_NAME_NOT_EMPTY_MSG = "IssuedBy By name can not be blank.";
    public static final int CURR_PROCESS_STAGE_COMPLETED_CD = 1020;
    public static final String CURR_PROCESS_STAGE_COMPLETED_MSG = "Process is already completed and no changes can be done.";

    public static final int INVALID_PROCESS_STAGE = 1021;
    public static final String INVALID_PROCESS_STAGE_MSG = "Invalid process stage";
    public static final String INVALID_GEN_DATE_MSG = "Invalid generate date format.";
    public static final String INVALID_ISSUE_DATE_MSG = "Invalid issue date format.";
    public static final String INVALID_APPR_DATE_MSG = "Invalid approved date format.";

    public static final int INSP_QNTY_LESS_THAN_ONE_CD = 1022;
    public static final String INSP_QNTY_LESS_THAN_ONE_MSG = "Inspected quantity cannot be less than 1";

    public static final int QNTY_SUM_INVALID_CD = 1023;
    public static final String QNTY_SUM_INVALID_MSG = "Inspected quantity should be equal to sum of accepted quantity and rejected quantity.";
    public static final int INVALID_LOCATOR_CD = 1024;
    public static final String INVALID_LOCATOR_ID_MSG = "Locator Id does not exist in system.";
    public static final int INVALID_UOM_CD = 1025;
    public static final String INVALID_UOM_ID_MSG = "UOM Id does not exist in system.";

    public static final int INVALID_LOCATION_CD = 1026;
    public static final String INVALID_LOCATION_ID_MSG = "Location Id does not exist in system.";
    public static final int INVALID_ITEM_ID_CD = 1027;
    public static final String INVALID_ITEM_ID_MSG = "Item not found for item id. ";
    public static final int ITEM_AND_LOCATOR_INVALID_CD = 1028;
    public static final String ITEM_AND_LOCATOR_INVALID_MSG = "Item code and locator mapping not available";
    public static final int INVALID_TXN_TYPE_CD = 1029;
    public static final String INVALID_TXN_TYPE_MSG = "Invalid Txn Type";
    public static final int INVALID_USER_TYPE_CD = 1030;
    public static final String INVALID_USER_TYPE_MSG = "Invalid User Type";
    public static final int USER_CD_EXIST_CD = 1031;
    public static final String USER_CD_EXIST_MSG = "User code already exist";
    public static final int INVALID_USER_ID = 1031;
    public static final String INVALID_USER_MSG = "Invalid user code";
    public static final int PWD_INVALID = 1032;
    public static final String PWD_INVALID_MSG = "Password less can not be less than 6 characters";
    public static final int LOGIN_PWD_INVALID = 1033;
    public static final String LOGIN_PWD_INVALID_MSG = "Please provide password to login";
    public static final int INACTIVE_USER = 1035;
    public static final String INACTIVE_USER_MSG = "User is not in active stage";
    public static final int INVALID_PROCESS_ORG_ID = 1036;
    public static final String INVALID_PROCESS_ORG_ID_MSG = "Process does not belongs to organization";
    public static final int INVALID_LOCATION_LOCATOR_ORG_CD = 1037;
    public static final String INVALID_LOCATION_LOCATOR_ORG_MSG = "Locator not mapped to location and organization";

    public static final int INVALID_LOCATOR_ORG_CD = 1038;
    public static final String INVALID_LOCATOR_ORG_MSG = "Locator not mapped to organization";

    public static final int INVALID_LOCATION_ORG_CD = 1039;
    public static final String INVALID_LOCATION_ORG_MSG = "Location not mapped to organization";
    public static final int PARAM_ALREADY_EXIST_CD = 1040;
    public static final String PARAM_ALREADY_EXIST_MSG = "Parameter already exist";
    public static final int MANDATORY_FIELDS_CD = 1041;
    public static final String MANDATORY_FIELDS_MSG = "Mandatory fields can not be empty or null";
    public static final int ITEM_NAME_CD_MAX_LENGTH_CD = 1042;
    public static final String ITEM_NAME_CD_MAX_LENGTH_MSG = "Item Name max length is generated more that 3 digits. Please enter item name code manually";
    public static final int DATA_ALREADY_SUBMIT_CD = 1043;
    public static final String DATA_ALREADY_SUBMIT_MSG = "Data already submitted earlier for this process Id";
    public static final int FROM_TO_DATE_INVALID_CD = 1044;
    public static final String FROM_TO_DATE_INVALID_MSG = "From date can not be greater than to date";
    public static final int ORG_ID_IS_MAND_CD = 1045;
    public static final String ORG_ID_IS_MAND_MSG = "Organization id can not be blank for super user";
    public static final int ACTION_NOT_ALWD_HQ_CD = 1046;
    public static final String ACTION_NOT_ALWD_HQ_MSG = "Action is not allowed for HQ user";
    public static final int QTY_GREAT_THAN_AVL_CD = 1047;
    public static final String QTY_GREAT_THAN_AVL_MSG = "Requested quantity is not available";
    public static final int ITEM_NOT_FOUND_ISSUE_NOT_CD = 1048;
    public static final String ITEM_NOT_FOUND_ISSUE_NOT_MSG = "Item not found in issue note : ";
    public static final int MAX_QTY_ISSUE_NOTE_ERROR_CD = 1049;
    public static final String MAX_QTY_ISSUE_NOTE_ERROR_MSG = "Item quantity is greater than issue note available quantity : ";
    public static final int JWT_EXPIRED = 1050;
    public static final String JWT_EXPIRED_MSG = "Token expired. Please re-login to continue";
    public static final int MAX_VAL_NON_NUMERIC_CD = 1051;
    public static final String MAX_VAL_NON_NUMERIC_MSG = "Current max value is non numeric in DB";
    public static final int PARAM_DESC_EMPTY_CD = 1052;
    public static final String PARAM_DESC_EMPTY_MSG = "Param Desc can not be blank";
}
