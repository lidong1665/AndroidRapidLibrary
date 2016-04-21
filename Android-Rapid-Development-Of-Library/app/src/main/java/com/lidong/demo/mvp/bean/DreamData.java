package com.lidong.demo.mvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public class DreamData {


    /**
     * reason : successed
     * result : [{"id":"89","name":"其它动物","fid":"3"},{"id":"85","name":"哺乳类","fid":"3"},{"id":"86","name":"昆虫类","fid":"3"},{"id":"87","name":"冷血类","fid":"3"},{"id":"88","name":"鸟禽类","fid":"3"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * id : 89
     * name : 其它动物
     * fid : 3
     */

    private List<ResultBean> result;

    public DreamData() {
    }

    public DreamData(List<ResultBean> result, String reason, int error_code) {
        this.result = result;
        this.reason = reason;
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public DreamData(String reason, int error_code, List<ResultBean> result) {
        this.reason = reason;
        this.error_code = error_code;
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String name;
        private String fid;

        public ResultBean() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", fid='" + fid + '\'' +
                    '}';
        }
    }
}
