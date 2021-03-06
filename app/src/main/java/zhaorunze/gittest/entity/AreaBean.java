package zhaorunze.gittest.entity;

import java.io.Serializable;

/**
 * Created by zhaorunze on
 * 2018/2/27 15:07
 * E-Mail Address：1159963642@qq.com
 */

public class AreaBean implements Serializable{
    private Integer areaId;
    private String areaName;
    private Integer priority;
    private Long createTime;
    private Long lastEditTime;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof AreaBean) {
            AreaBean other = (AreaBean) obj;
            return this.areaId.equals(other.areaId);
        }
        return false;
    }
}
