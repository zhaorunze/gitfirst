package zhaorunze.gittest.entity;

/**
 * Created by zhaorunze on
 * 2018/2/24 15:56
 * E-Mail Address：1159963642@qq.com
 */

public class ResponseBody<T> {
    private String msg;
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
