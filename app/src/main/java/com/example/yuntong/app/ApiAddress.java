package com.example.yuntong.app;

/**
 * @author 杨晓峰
 * @create 2019/4/17
 * @Describe 所有接口地址集
 */
public class ApiAddress {
    //生产环境
    public final static String api = "http://api.yt.liby.ltd:18080";
    public static String contractId;//合同Id
    public static String orderId;//订单ID
    public static String minnorId;//小项ID
    public static String instrumentId;//工具Id
    public static String materialId;//材料Id
    public static String material_Put_Id;//材料入库Id
    public static String material_Out_Id;//材料出库Id
    //登录
    public final static String userLogin = "/user/login";
    //安全修改用户密码
    public final static String SAGE_PSD = "/user/safe_modify_password";
    //合同列表
    public final static String CONTRACT_LIST = "/contract/list/adapter/order_by_create_time_desc";
    //合同详情
    public final static String CONTRACT_OVER = "/contract/end?contractId=";
    //结束合同
    public final static String CONTRACT_DETAIL = "/contract/";
    //结束订单
    public final static String OVER_ORDER = "/contract_order/end?contractOrderId=";
    //合同订单列表
    public final static String ORDER_LIST = "/contract_order/list/adapter/order_by_create_time_desc";
    //订单详情
    public final static String ORDER_DETAIL = "/contract_order/";
    //新增合同
    public final static String CONTRACT_CREATE = "/contract";
    //新增合同订单
    public final static String ORDER_CREATE = "/contract_order";
    //新增合同订单中的财务更新
    public final static String UPDATE_FINANCE = "/contract_order/update_finance_item";
    //小项列表
    public final static String MINOR_TERM_LIST = "/small_item/list/adapter/order_by_create_time_desc";
    //小项详情
    public final static String MINOR_DETAIL = "/small_item/";
    //创建小项
    public final static String MINOR_CREATE = "/small_item";
    //结束小项
    public final static String OVER_MINOR_TERM = "/small_item/end?smallItemId=";
    //工具列表
    public final static String INSTRUMENT_LIST= "/instrument/list/adapter/order_by_create_time_desc";
    //新增工具
    public final static String INSTRUMENT_CREATE = "/instrument";
    //工具详情
    public final static String INSTRUMENT_DETAIL = "/instrument/";
    //修改工具
    public final static String INSTRUMENT_EDIT = "/instrument";

    //合同小项汇总
    public final static String CONTRACT_MI_TOTAL = "/small_item/summary_by_time_range";

    //合同小项汇总列表
    public final static String CONTRACT_MI_TOTAL_LIST = "/small_item/summary_by_time_range_detail";
    //新增材料
    public final static String CREATE_MATERIALS = "/material";
    //材料列表
    public final static String LIST_MATERIALS = "/material/list/adapter/order_by_create_time_desc";
    //材料详情
    public final static String MATERIALS_DETAIL = "/material/";
    //新增材料入库
    public final static String CREATE_MATERIALS_PUT = "/material_log";
    //材料出入库列表
    public final static String OMATERIALS_PUT_LIST = "/material_log/list/adapter/order_by_create_time_desc";
    //材料出入库详情
    public final static String PUT_DETAIL = "/material_log/";
    //首页详情
    public final static String HOME_DATA = "/index";
    //修改尾款支付状态
    public final static String END_PAY_STATUS = "/small_item/total_paid";

}
