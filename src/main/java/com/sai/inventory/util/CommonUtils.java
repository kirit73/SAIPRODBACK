package com.sai.inventory.util;

import com.sai.inventory.entity.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {
    public static Integer getInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(String val, String format) {
        Date finalDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setLenient(true);
            return simpleDateFormat.parse(val);
        } catch (Exception e) {
        }
        return null;
    }

    public static Long getLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isBlank(String v) {
        if (v == null || v.isBlank() || v.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String v) {
        return !isBlank(v);
    }

    public static boolean isBlank(Long v) {
        if (v == null) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(Long v) {
        return !isBlank(v);
    }

    public static String getDate(Date val, String format) {
        Date finalDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(val);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getDate(Date val) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ApplicationConstant.DATE_FORMAT);
            simpleDateFormat.setLenient(true);
            return simpleDateFormat.format(val);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getAuthKey(String auth) {
        return auth.substring(7);
    }

    public static Date changeDateToDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date changeDateToDayENd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 99);
        return calendar.getTime();
    }

    public static String getGetFNSCategory(Date createDate) {
        if (createDate == null || addMonths(-12).after(createDate)) {
            return "No Moving";
        } else if (addMonths(-1).before(createDate)) {
            return "Fast Moving";
        } else {
            return "Slow Moving";
        }
    }

    public static Date addMonths(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static String formatDate(Date createDt) {
        if (createDt != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return simpleDateFormat.format(createDt);
        }

        return null;
    }

    public static GatePassDtls getCorrectGPD(boolean rejectProcess, List<GatePassDtls> allByProcessId) {
        GatePassDtls dtls = null;
        if (allByProcessId == null || allByProcessId.isEmpty()) {
            dtls = null;
        }else if (allByProcessId.size() == 1) {
            dtls = allByProcessId.get(0);
        } else if (rejectProcess) {
            dtls = allByProcessId.stream().sorted((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate())).collect(Collectors.toList()).get(0);
        } else {
            dtls = allByProcessId.stream().sorted(Comparator.comparing(BaseEntity::getCreateDate)).collect(Collectors.toList()).get(0);
        }
        return dtls;
    }

    public static String get3Digit(Object o){
        return String.format("%03d", o);
    }

    public static InspectionReportDtls getCorrectIRD(boolean rejectProcess, List<InspectionReportDtls> allByProcessId) {
        InspectionReportDtls dtls = null;
        if (allByProcessId == null || allByProcessId.isEmpty()) {
            dtls = null;
        }else if (allByProcessId.size() == 1) {
            dtls = allByProcessId.get(0);
        } else if (rejectProcess) {
            dtls = allByProcessId.stream().sorted((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate())).collect(Collectors.toList()).get(0);
        } else {
            dtls = allByProcessId.stream().sorted(Comparator.comparing(BaseEntity::getCreateDate)).collect(Collectors.toList()).get(0);
        }
        return dtls;
    }

    public static InspectionReportDtlsNew getCorrectIRDN(boolean rejectProcess, List<InspectionReportDtlsNew> allByProcessId) {
        InspectionReportDtlsNew dtls = null;
        if (allByProcessId == null || allByProcessId.isEmpty()) {
            dtls = null;
        }else if (allByProcessId.size() == 1) {
            dtls = allByProcessId.get(0);
        } else if (rejectProcess) {
            dtls = allByProcessId.stream().sorted((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate())).collect(Collectors.toList()).get(0);
        } else {
            dtls = allByProcessId.stream().sorted(Comparator.comparing(BaseEntity::getCreateDate)).collect(Collectors.toList()).get(0);
        }
        return dtls;
    }

    public static AcceptRejectDtls getCorrectACT(boolean rejectProcess, List<AcceptRejectDtls> allByProcessId) {
        AcceptRejectDtls dtls = null;
        if (allByProcessId == null || allByProcessId.isEmpty()) {
            dtls = null;
        }else if (allByProcessId.size() == 1) {
            dtls = allByProcessId.get(0);
        } else if (rejectProcess) {
            dtls = allByProcessId.stream().sorted((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate())).collect(Collectors.toList()).get(0);
        } else {
            dtls = allByProcessId.stream().sorted(Comparator.comparing(BaseEntity::getCreateDate)).collect(Collectors.toList()).get(0);
        }
        return dtls;
    }
}
