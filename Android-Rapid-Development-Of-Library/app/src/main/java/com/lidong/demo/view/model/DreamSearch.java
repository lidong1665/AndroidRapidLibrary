package com.lidong.demo.view.model;

import java.util.List;

/**
 * Created by lidong on 16/7/17.
 */
public class DreamSearch {


    /**
     * reason : successed
     * result : [{"id":"873e943d1bcb40cd4b289e0809803343","title":"黄金 金子","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","list":["梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","另外，如果梦见金银制的杯、盆、工具等，都预示要结婚，或在需要下赌注的事情上，会有好运气。","梦见金衣服、金布，预示会得到荣誉和声望。","梦见自己在寻找黄金，预示会通过自己的努力，改善处境，取得成功。","梦见炼金，或是淘金，采金，则是提醒你要提高警惕，擦亮眼睛，不要以貌取人。","梦见挖出金子，或打开金库，预示你会有意外之财。","梦见藏起金子，提醒你要好好保护自己的利益，必要的话"]},{"id":"237169518a0ff81aec29b80a546aa7ac","title":"黄金","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","list":["梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","另外，如果梦见金银制的杯、盆、工具等，都预示要结婚，或在需要下赌注的事情上，会有好运气。","梦见金衣服、金布，预示会得到荣誉和声望。","梦见自己在寻找黄金，预示会通过自己的努力，改善处境，取得成功。","梦见炼金，或是淘金，采金，则是提醒你要提高警惕，擦亮眼睛，不要以貌取人。","梦见挖出金子，或打开金库，预示你会有意外之财。","梦见藏起金子，提醒你要好好保护自己的利益，必要的话"]},{"id":"315f055cfbae60064e07427321e6a722","title":"捡黄金","des":"梦见捡黄金，你要发大财，但要努力争取。发财也要付出努力，不会自动掉在你的口袋里。","list":["梦见捡黄金，你要发大财，但要努力争取。发财也要付出努力，不会自动掉在你的口袋里。","梦见捡黄金，预示着通过自己的积极努力，能够成功地改变不适宜的条件。"]}]
     * error_code : 0
     */

    private String reason;
    private int error_code;

    public DreamSearch(String reason, int error_code, List<ResultBean> result) {
        this.reason = reason;
        this.error_code = error_code;
        this.result = result;
    }

    public DreamSearch() {
    }

    /**
     * id : 873e943d1bcb40cd4b289e0809803343
     * title : 黄金 金子
     * des : 梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。
     * list : ["梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。","另外，如果梦见金银制的杯、盆、工具等，都预示要结婚，或在需要下赌注的事情上，会有好运气。","梦见金衣服、金布，预示会得到荣誉和声望。","梦见自己在寻找黄金，预示会通过自己的努力，改善处境，取得成功。","梦见炼金，或是淘金，采金，则是提醒你要提高警惕，擦亮眼睛，不要以貌取人。","梦见挖出金子，或打开金库，预示你会有意外之财。","梦见藏起金子，提醒你要好好保护自己的利益，必要的话"]
     */



    private List<ResultBean> result;

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


    @Override
    public String toString() {
        return "DreamSearch{" +
                "reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }

    public static class ResultBean {
        private String id;
        private String title;
        private String des;
        private List<String> list;

        public ResultBean() {
        }

        public ResultBean(String id, String title, String des, List<String> list) {
            this.id = id;
            this.title = title;
            this.des = des;
            this.list = list;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", des='" + des + '\'' +
                    ", list=" + list +
                    '}';
        }
    }
}
