package com.estsoft.codit.db.vo;

/**
 * Created by estsoft on 2016-07-19.
 */
public class ResultVo {
    private int applicantId;
    private int test_caseId;
    boolean correctness;
    int usedMemory;
    int runningTime;

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getTest_caseId() {
        return test_caseId;
    }

    public void setTest_caseId(int test_caseId) {
        this.test_caseId = test_caseId;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "applicantId=" + applicantId +
                ", test_caseId=" + test_caseId +
                ", correctness=" + correctness +
                ", usedMemory=" + usedMemory +
                ", runningTime=" + runningTime +
                '}';
    }
}
