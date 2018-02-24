package zhaorunze.gittest.entity;

import java.util.List;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:11
 * E-Mail Address：1159963642@qq.com
 */

public class GuideBean {

    /**
     * start_url : http://wslive-1253942066.pictj.myqcloud.com/start/20180126/15169375319647faca477c70d414c7adb171a8d9c4e43.jpg
     * guide_url : ["http://wslive-1253942066.pictj.myqcloud.com/guide/20180126/1516936868447426eaae1413d297dee4279ff6b3fec09.jpg","http://wslive-1253942066.pictj.myqcloud.com/guide/20180126/1516936873142728ea1ecd1600565565d116f7ef185c0.jpg","http://wslive-1253942066.pictj.myqcloud.com/guide/20180202/15175426901163d59909e0fdffabdbb82f845237c404f.jpg","http://wslive-1253942066.pictj.myqcloud.com/guide/20180126/1516936863613a977a0d270d0dd714485792b2fbe6326.jpg"]
     * ad_url : {"type":"1","target_url":"http://wslive-1253942066.pictj.myqcloud.com/official_accounts/20180131/1517364893705df805b9323bb1a9ffc79967966264c30.jpg"}
     */

    private String start_url;
    private AdUrlBean ad_url;
    private List<String> guide_url;

    public String getStart_url() {
        return start_url;
    }

    public void setStart_url(String start_url) {
        this.start_url = start_url;
    }

    public AdUrlBean getAd_url() {
        return ad_url;
    }

    public void setAd_url(AdUrlBean ad_url) {
        this.ad_url = ad_url;
    }

    public List<String> getGuide_url() {
        return guide_url;
    }

    public void setGuide_url(List<String> guide_url) {
        this.guide_url = guide_url;
    }

    public class AdUrlBean {
        /**
         * type : 1
         * target_url : http://wslive-1253942066.pictj.myqcloud.com/official_accounts/20180131/1517364893705df805b9323bb1a9ffc79967966264c30.jpg
         */

        private String type;
        private String target_url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }
    }
}
