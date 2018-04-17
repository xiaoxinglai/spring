package sell.dao.result;

import java.util.List;

/**
 * Created by user12 on 2018/2/28.
 */
public class PageResult<T> {

    private List<T> data;
    private Integer currentPage;
    private Integer totalCount;
    public static  final Integer pageSize=2;

    private Integer code;
    private String msg;
    private Boolean isSuccess;

    public PageResult() {

    }
    public static PageResult Create(Integer code,String msg){
        PageResult result=new PageResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static PageResult Create(List data,Integer currentPage,Integer totalCount){

        PageResult pageResult =new PageResult();
        pageResult.setData(data);
        pageResult.setCurrentPage(currentPage);
        pageResult.setTotalCount(totalCount);
        pageResult.setSuccess(Boolean.TRUE);

        return pageResult;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
