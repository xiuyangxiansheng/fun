package com.springboot.fun.util.sign;

import java.util.List;

/**
 * @Author: lvliangjun
 * @Date: 2019/8/5 15:57
 */
public class StepInfo {
    private WatermarkBean watermark;
    private List<StepInfoListBean> stepInfoList;

    public StepInfo() {
    }

    public List<StepInfoListBean> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(List<StepInfoListBean> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }

    public WatermarkBean getWatermark() {
        return watermark;
    }

    public void setWatermark(WatermarkBean watermark) {
        this.watermark = watermark;
    }

    @Override
    public String toString() {
        return "StepInfo{" +
                "watermark=" + watermark +
                ", stepInfoList=" + stepInfoList +
                '}';
    }
}
