package com.example.yuntong.network;


import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.ContractToatalBean;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.bean.EditInstrumentBean;
import com.example.yuntong.bean.EditMinorTermBean;
import com.example.yuntong.bean.EditOrderBean;
import com.example.yuntong.bean.HomeBean;
import com.example.yuntong.bean.InstrumentBean;
import com.example.yuntong.bean.InstrumentDetailBean;
import com.example.yuntong.bean.MaterialPutBean;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.bean.MaterialsBean;
import com.example.yuntong.bean.MaterialsTypeBean;
import com.example.yuntong.bean.MinorTermAddBean;
import com.example.yuntong.bean.MinorTermDetailBean;
import com.example.yuntong.bean.OrderAddBean;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.ContractDetailBean;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.LoginEntity;
import com.example.yuntong.bean.MinorTermBean;
import com.example.yuntong.bean.OrderDetailBean;
import com.example.yuntong.bean.OrderListbean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author 杨晓峰
 * @create 2019/4/12
 * @Describe
 */

public interface AllApi {

    /**
     * 登录
     */
    @POST(ApiAddress.userLogin)
    Observable<BaseEntry<LoginEntity>> userLogin(@Body Map<String, String> maps);

    /**
     * 安全修改用户密码
     */
    @POST(ApiAddress.SAGE_PSD)
    Observable<BaseEntry<BaseBean>> modificationPsd(@Body Map<String, String> maps);

    /**
     * 合同列表
     * int pageNumber, int pageSize
     */
    @GET(ApiAddress.CONTRACT_LIST)
    Observable<BaseEntry<ContractListEntity>> getContractList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    /**
     * 合同详情
     */
    @GET
    Observable<BaseEntry<ContractDetailBean>> getContractDetailData(@Url String url);

    /**
     * 合同订单列表
     */
    @GET(ApiAddress.ORDER_LIST)
    Observable<BaseEntry<OrderListbean>> getOrderList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("contractId") String contractId);

    /**
     * 小项列表
     */
    @GET(ApiAddress.MINOR_TERM_LIST)
    Observable<BaseEntry<MinorTermBean>> getMinorTermList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("contractOrderId") String contractOrderId);


    /**
     * 订单详情
     */
    @GET
    Observable<BaseEntry<OrderDetailBean>> getOrderDetailData(@Url String url);

    /**
     * 小项详情
     */
    @GET
    Observable<BaseEntry<MinorTermDetailBean>> getMinorDetailData(@Url String url);

    /**
     * 新增合同
     */
    @POST(ApiAddress.CONTRACT_CREATE)
    Observable<BaseEntry<EditContractBean>> creatContract(@Body Map<String, String> maps);

    /**
     * 新增合同订单
     */
    @POST(ApiAddress.ORDER_CREATE)
    Observable<BaseEntry<OrderAddBean>> creatOrder(@Body Map<String, String> maps);

    /**
     * 创建小项
     */
    @POST(ApiAddress.MINOR_CREATE)
    Observable<BaseEntry<MinorTermAddBean>> creatMinor(@Body Map<String, String> maps);

    /**
     * 新增合同订单财务更新
     */
    @PUT(ApiAddress.UPDATE_FINANCE)
    Observable<BaseEntry<OrderDetailBean>> createUpdateFinance(@Body Map<String, String> maps);

    /**
     * 结束合同
     */
    @GET
    Observable<BaseEntry<EditContractBean>> overContract(@Url String url);

    /**
     * 修改合同
     */
    @PUT(ApiAddress.CONTRACT_CREATE)
    Observable<BaseEntry<EditContractBean>> editContract(@Body Map<String, String> maps);

    /**
     * 结束合同订单
     */
    @GET
    Observable<BaseEntry<EditOrderBean>> overOrder(@Url String url);

    /**
     * 修改合同订单
     */
    @PUT(ApiAddress.ORDER_CREATE)
    Observable<BaseEntry<EditOrderBean>> editOrder(@Body Map<String, String> maps);

    /**
     * 结束小项
     */
    @GET
    Observable<BaseEntry<EditMinorTermBean>> overMinorTerm(@Url String url);

    /**
     * 修改小项
     */
    @PUT(ApiAddress.MINOR_CREATE)
    Observable<BaseEntry<EditMinorTermBean>> editMinorTerm(@Body Map<String, String> maps);

    /**
     * 工具列表
     * int pageNumber, int pageSize
     */
    @GET(ApiAddress.INSTRUMENT_LIST)
    Observable<BaseEntry<InstrumentBean>> getInstrumentList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    /**
     * 新增工具
     */
    @POST(ApiAddress.INSTRUMENT_CREATE)
    Observable<BaseEntry<EditInstrumentBean>> creatInstrument(@Body Map<String, String> maps);

    /**
     * 工具详情
     */
    @GET
    Observable<BaseEntry<InstrumentDetailBean>> getInstrumentDetailData(@Url String url);

    /**
     * 修改工具
     */
    @PUT(ApiAddress.INSTRUMENT_EDIT)
    Observable<BaseEntry<EditInstrumentBean>> editInsutrument(@Body Map<String, String> maps);

    /**
     * 合同小项汇总
     */
    @POST(ApiAddress.CONTRACT_MI_TOTAL)
    Observable<BaseEntry<ContractToatalBean>> contractMiTotal(@Body Map<String, String> maps);

    /**
     * 合同小项汇总
     */
    @POST(ApiAddress.CONTRACT_MI_TOTAL_LIST)
    Observable<BaseEntry<MinorTermBean>> contractMiTotalList(@Body Map<String, String> maps);
    /**
     * 新增材料
     */
    @POST(ApiAddress.CREATE_MATERIALS)
    Observable<BaseEntry<MaterialsAddBean>> createMaterials(@Body Map<String, String> maps);

    /**
     * 材料列表
     * int pageNumber, int pageSize
     */
    @GET(ApiAddress.LIST_MATERIALS)
    Observable<BaseEntry<MaterialsBean>> getMaterialsList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    /**
     * 材料详情
     */
    @GET
    Observable<BaseEntry<MaterialsAddBean>> getMaterialsDetailData(@Url String url);

    /**
     * 新增材料
     */
    @POST(ApiAddress.CREATE_MATERIALS_PUT)
    Observable<BaseEntry<MaterialPutBean>> createMaterialsPut(@Body Map<String, String> maps);
    /**
     * 材料出入库列表
     */
    @GET(ApiAddress.OMATERIALS_PUT_LIST)
    Observable<BaseEntry<MaterialsTypeBean>> getMaPutList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("materialId") String materialId, @Query("type") String type);

    /**
     * 材料出入库详情
     */
    @GET
    Observable<BaseEntry<MaterialPutBean>> getPutDetailData(@Url String url);
    /**
     * 材料详情
     */
    @GET
    Observable<BaseEntry<HomeBean>> getHomeData(@Url String url);

    /**
     * 修改尾款支付状态
     */
    @PUT(ApiAddress.END_PAY_STATUS)
    Observable<BaseEntry<BaseBean>> endPayStatus(@Body Map<String, String> maps);
}
