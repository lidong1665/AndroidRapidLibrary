package com.lidong.demo.mvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class WeatherData {

    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"18","wind_direction":"东南风","wind_strength":"2级","humidity":"41%","time":"14:32"},"today":{"temperature":"8℃~17℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期三","city":"苏州","date_y":"2016年03月02日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"弱","comfort_index":"","wash_index":"较适宜","travel_index":"较适宜","exercise_index":"较适宜","drying_index":""},"future":[{"temperature":"8℃~17℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期三","date":"20160302"},{"temperature":"11℃~21℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期四","date":"20160303"},{"temperature":"12℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"东南风3-4 级","week":"星期五","date":"20160304"},{"temperature":"10℃~19℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东北风3-4 级","week":"星期六","date":"20160305"},{"temperature":"11℃~20℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期日","date":"20160306"},{"temperature":"10℃~19℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东北风3-4 级","week":"星期一","date":"20160307"},{"temperature":"12℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"东南风3-4 级","week":"星期二","date":"20160308"}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * sk : {"temp":"18","wind_direction":"东南风","wind_strength":"2级","humidity":"41%","time":"14:32"}
     * today : {"temperature":"8℃~17℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期三","city":"苏州","date_y":"2016年03月02日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"弱","comfort_index":"","wash_index":"较适宜","travel_index":"较适宜","exercise_index":"较适宜","drying_index":""}
     * future : [{"temperature":"8℃~17℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期三","date":"20160302"},{"temperature":"11℃~21℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期四","date":"20160303"},{"temperature":"12℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"东南风3-4 级","week":"星期五","date":"20160304"},{"temperature":"10℃~19℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东北风3-4 级","week":"星期六","date":"20160305"},{"temperature":"11℃~20℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风3-4 级","week":"星期日","date":"20160306"},{"temperature":"10℃~19℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东北风3-4 级","week":"星期一","date":"20160307"},{"temperature":"12℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"东南风3-4 级","week":"星期二","date":"20160308"}]
     */

    private ResultEntity result;
    private int error_code;

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public static class ResultEntity {
        /**
         * temp : 18
         * wind_direction : 东南风
         * wind_strength : 2级
         * humidity : 41%
         * time : 14:32
         */

        private SkEntity sk;
        /**
         * temperature : 8℃~17℃
         * weather : 多云
         * weather_id : {"fa":"01","fb":"01"}
         * wind : 东南风3-4 级
         * week : 星期三
         * city : 苏州
         * date_y : 2016年03月02日
         * dressing_index : 较冷
         * dressing_advice : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         * uv_index : 弱
         * comfort_index :
         * wash_index : 较适宜
         * travel_index : 较适宜
         * exercise_index : 较适宜
         * drying_index :
         */

        private TodayEntity today;
        /**
         * temperature : 8℃~17℃
         * weather : 多云
         * weather_id : {"fa":"01","fb":"01"}
         * wind : 东南风3-4 级
         * week : 星期三
         * date : 20160302
         */

        private List<FutureEntity> future;

        public void setSk(SkEntity sk) {
            this.sk = sk;
        }

        public void setToday(TodayEntity today) {
            this.today = today;
        }

        public void setFuture(List<FutureEntity> future) {
            this.future = future;
        }

        public SkEntity getSk() {
            return sk;
        }

        public TodayEntity getToday() {
            return today;
        }

        public List<FutureEntity> getFuture() {
            return future;
        }

        public static class SkEntity {
            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTemp() {
                return temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public String getTime() {
                return time;
            }

            @Override
            public String toString() {
                return "SkEntity{" +
                        "temp='" + temp + '\'' +
                        ", wind_direction='" + wind_direction + '\'' +
                        ", wind_strength='" + wind_strength + '\'' +
                        ", humidity='" + humidity + '\'' +
                        ", time='" + time + '\'' +
                        '}';
            }
        }

        public static class TodayEntity {

            @Override
            public String toString() {
                return "TodayEntity{" +
                        "temperature='" + temperature + '\'' +
                        ", weather='" + weather + '\'' +
                        ", weather_id=" + weather_id +
                        ", wind='" + wind + '\'' +
                        ", week='" + week + '\'' +
                        ", city='" + city + '\'' +
                        ", date_y='" + date_y + '\'' +
                        ", dressing_index='" + dressing_index + '\'' +
                        ", dressing_advice='" + dressing_advice + '\'' +
                        ", uv_index='" + uv_index + '\'' +
                        ", comfort_index='" + comfort_index + '\'' +
                        ", wash_index='" + wash_index + '\'' +
                        ", travel_index='" + travel_index + '\'' +
                        ", exercise_index='" + exercise_index + '\'' +
                        ", drying_index='" + drying_index + '\'' +
                        '}';
            }

            private String temperature;
            private String weather;
            /**
             * fa : 01
             * fb : 01
             */

            private WeatherIdEntity weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public void setWeather_id(WeatherIdEntity weather_id) {
                this.weather_id = weather_id;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public String getTemperature() {
                return temperature;
            }

            public String getWeather() {
                return weather;
            }

            public WeatherIdEntity getWeather_id() {
                return weather_id;
            }

            public String getWind() {
                return wind;
            }

            public String getWeek() {
                return week;
            }

            public String getCity() {
                return city;
            }

            public String getDate_y() {
                return date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public static class WeatherIdEntity {
                private String fa;
                private String fb;

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }

                public String getFa() {
                    return fa;
                }

                public String getFb() {
                    return fb;
                }
            }
        }

        public static class FutureEntity {
            private String temperature;
            private String weather;
            /**
             * fa : 01
             * fb : 01
             */

            private WeatherIdEntity weather_id;
            private String wind;
            private String week;
            private String date;

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public void setWeather_id(WeatherIdEntity weather_id) {
                this.weather_id = weather_id;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public String getWeather() {
                return weather;
            }

            public WeatherIdEntity getWeather_id() {
                return weather_id;
            }

            public String getWind() {
                return wind;
            }

            public String getWeek() {
                return week;
            }

            public String getDate() {
                return date;
            }


            public static class WeatherIdEntity {
                private String fa;
                private String fb;

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }

                public String getFa() {
                    return fa;
                }

                public String getFb() {
                    return fb;
                }

                @Override
                public String toString() {
                    return "WeatherIdEntity{" +
                            "fa='" + fa + '\'' +
                            ", fb='" + fb + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "FutureEntity{" +
                        "temperature='" + temperature + '\'' +
                        ", weather='" + weather + '\'' +
                        ", weather_id=" + weather_id +
                        ", wind='" + wind + '\'' +
                        ", week='" + week + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "sk=" + sk +
                    ", today=" + today +
                    ", future=" + future +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
