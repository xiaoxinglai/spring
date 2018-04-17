package sell.dao.result;

/**
 * Created by user12 on 2018/2/3.
 */
public class BizResult<T> {
    private  T date;
    private Boolean isSuccess;
    private Integer currentPage;
    private Integer totalCount;
    private Integer code;
    private String msg;

    /**
     * 返回是布尔值的结果
     * @return
     */
    public static BizResult Create(){
        BizResult bizResult=new BizResult();
        bizResult.setSuccess(Boolean.TRUE);
        return bizResult;
    }

    /**
     * 出现错误的情况
     * @param code
     * @param msg
     * @return
     */
    public static BizResult Create(Integer code,String msg){
        BizResult bizResult=new BizResult();
        bizResult.setSuccess(Boolean.FALSE);
        bizResult.setCode(code);
        bizResult.setMsg(msg);
        return bizResult;
    }

    /**
     * 查找数据的情况  且 无须分页
     * @param date
     * @param <T>
     * @return
     */
    public static<T> BizResult Create(T date){
        BizResult bizResult=new BizResult();
        bizResult.setSuccess(Boolean.TRUE);
        bizResult.setDate(date);
        return bizResult;
    }

    /**
     *查找数据的情况 且有多条数据 需要分页
     * @param date
     * @param currentPage
     * @param totalCount
     * @param <T>
     * @return
     */
    public static<T> BizResult Create(T date,Integer currentPage,Integer totalCount){
        BizResult bizResult=new BizResult();
        bizResult.setSuccess(Boolean.TRUE);
        bizResult.setDate(date);
        bizResult.setCurrentPage(currentPage);
        bizResult.setTotalCount(totalCount);
        return bizResult;
    }


    public BizResult() {
    }

    public void setDate(T date) {
        this.date = date;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
