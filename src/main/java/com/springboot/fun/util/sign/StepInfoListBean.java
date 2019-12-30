package com.springboot.fun.util.sign;

/**
 * @Author: lvliangjun
 * @Date: 2019/8/5 15:55
 */
public class StepInfoListBean {
    /**
     * step : 0
     * timestamp : 1562342400
     */

    private String step;
    private String timestamp;

    public StepInfoListBean() {
    }

    /**
     * step : 0
     * timestamp : 1562342400
     */


    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StepInfoListBean{" +
                "step='" + step + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
