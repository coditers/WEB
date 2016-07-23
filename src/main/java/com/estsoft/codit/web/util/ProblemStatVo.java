package com.estsoft.codit.web.util;

public class ProblemStatVo {
    String problemName;
    int avg;
    int stdDev;
    int max;
    int min;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public int getStdDev() {
        return stdDev;
    }

    public void setStdDev(int stdDev) {
        this.stdDev = stdDev;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


}
