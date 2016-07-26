package com.estsoft.codit.db.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by estsoft on 2016-07-22.
 */
public class ApplicantStatVo {

    private int applicantId;
    private String applicantName;
    private List<Integer> problemScoreList;
    private int totalScore;

    public ApplicantStatVo(){
        problemScoreList = new ArrayList<Integer>();
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public List<Integer> getProblemScoreList() {
        return problemScoreList;
    }

    public void setProblemScoreList(List<Integer> problemScoreList) {
        this.problemScoreList = problemScoreList;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "ApplicantStatVo{" +
                "applicantId=" + applicantId +
                ", applicantName='" + applicantName + '\'' +
                ", problemScoreList=" + problemScoreList +
                '}';
    }


    public void addScore(int score){ problemScoreList.add(score);}
    public int calTotalScore(){
        totalScore = 0;
        for(int i : problemScoreList)
            totalScore +=i;
        totalScore /= problemScoreList.size();
        return totalScore;
    }

}
