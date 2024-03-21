package com.nolamarel.mindmingle.tests;

public class Test {
    public String testTitle, testSubhead, testParagraph;
    public int testIv;

    public Test(int testIv, String testTitle, String testSubhead, String testParagraph) {
        this.testIv = testIv;
        this.testTitle = testTitle;
        this.testSubhead = testSubhead;
        this.testParagraph = testParagraph;
    }

    public int getTestIv() {
        return testIv;
    }

    public void setTestIv(int testIv) {
        this.testIv = testIv;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestSubhead() {
        return testSubhead;
    }

    public void setTestSubhead(String testSubhead) {
        this.testSubhead = testSubhead;
    }

    public String getTestParagraph() {
        return testParagraph;
    }

    public void setTestParagraph(String testParagraph) {
        this.testParagraph = testParagraph;
    }
}
