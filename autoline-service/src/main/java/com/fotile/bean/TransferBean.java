package com.fotile.bean;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

public class TransferBean implements Serializable{
    private String recid;
    private String serialNumber;
    private String CheckResult;
    private String WorkCenterName;
    private List<String> defectCodes;
    private String productionLine;

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCheckResult() {
        return CheckResult;
    }

    public void setCheckResult(String checkResult) {
        CheckResult = checkResult;
    }

    public String getWorkCenterName() {
        return WorkCenterName;
    }

    public void setWorkCenterName(String workCenterName) {
        WorkCenterName = workCenterName;
    }

    public List<String> getDefectCodes() {
        return defectCodes;
    }

    public void setDefectCodes(List<String> defectCodes) {
        this.defectCodes = defectCodes;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }
}
