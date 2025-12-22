// package com.sai.inventory.domain.process.request;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import java.util.Date;

// public class StockLedgerRrq {
//     private String fromDate;
//     private String toDate;
//     private String itemCode;

//     @JsonIgnore
//     private Date fromDateDt;
//     @JsonIgnore
//     private Date toDateDt;
//    // @JsonIgnore
//     private Long orgId;

//     public String getFromDate() {
//         return fromDate;
//     }

//     public void setFromDate(String fromDate) {
//         this.fromDate = fromDate;
//     }

//     public String getToDate() {
//         return toDate;
//     }

//     public void setToDate(String toDate) {
//         this.toDate = toDate;
//     }

//     public String getItemCode() {
//         return itemCode;
//     }

//     public void setItemCode(String itemCode) {
//         this.itemCode = itemCode;
//     }

//     public Date getFromDateDt() {
//         return fromDateDt;
//     }

//     public void setFromDateDt(Date fromDateDt) {
//         this.fromDateDt = fromDateDt;
//     }

//     public Date getToDateDt() {
//         return toDateDt;
//     }

//     public void setToDateDt(Date toDateDt) {
//         this.toDateDt = toDateDt;
//     }

//     public void setOrgId(Long orgId) {
//         this.orgId = orgId;
//     }

//     public Long getOrgId() {
//         return orgId;
//     }
// }


package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockLedgerRrq {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"; // Ensure correct format

    private String fromDate;
    private String toDate;
    private String itemCode;

    @JsonIgnore
    private Date fromDateDt;
    @JsonIgnore
    private Date toDateDt;
    
    private Long orgId;

    private static Date parseDate(String dateStr, boolean isStartOfDay) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (isStartOfDay) {
                dateStr += " 00:00:00"; // Set to start of the day
            } else {
                dateStr += " 23:59:59"; // Set to end of the day
            }
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateStr, e);
        }
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
        this.fromDateDt = parseDate(fromDate, true); // Start of the day
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
        this.toDateDt = parseDate(toDate, false); // End of the day
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Date getFromDateDt() {
        return fromDateDt;
    }

    public void setFromDateDt(Date fromDateDt) {
        this.fromDateDt = fromDateDt;
    }

    public Date getToDateDt() {
        return toDateDt;
    }

    public void setToDateDt(Date toDateDt) {
        this.toDateDt = toDateDt;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
