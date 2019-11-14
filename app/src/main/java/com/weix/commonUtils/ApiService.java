package com.weix.commonUtils;

import com.weix.charts_xcl.ChartData;
import com.weix.treeViews.PourPositionData;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    //    登录验证
//    @GET("rest/main/getUserMessage")
//    Call<UserInfoBean> login();

    //    新增安全检查
    @GET("rest/NewSafetyPatrolController/add")
    Call<String> add(@Query("NewsafetyPatrolVo") String newsafetyPatrolVo);

    //通知人
    @GET
    Call<PourPositionData> getTongzhiren(@Url String url);

    @GET
    Call<ChartData> getZhoubiao(@Url String url);
    //自主检查
//    @GET("app.do?checkUpdate")
//    Call<CheckUpdateBean> checkUpdate();

//    @GET("app.do?ysszxsDownload")
//    Observable<YusheshuizhunxianInfoBean> yusheshuizhunxianInfoDownload(@Query("departId") String departId);

//    //下载基础信息
//    @GET("rest/Sq_jc_HiddenDangerController/getListTree1")
//    Call<BaseLibInfoBean> getBaseInfo(@Query("projectId") String projectId);


//    //检查人
//    @GET("rest/SafetyQualityPatrolNew/rest/main/getUserByDepart")
//    Call<OrganizationActivityData> getJiancharen();

////  上传测点
//    @POST("app.do?celiangshujuxiazhai")
//    Call<ResponseBody> sendCedianCall(@Body List<CedianDuanmianData> cdd);
//    //  上传断面status,封存断面
//    @POST("app.do?fengcun")
//    Call<ResponseBody> sendStatusCall(@Query("duanmianId") String duanmianId);
//    计划巡检新增
    @POST("rest/Sq_SafetyPatrolPlanController/addSafetyPatrolPlan")
    Call<ResponseBody> jihuaAdd(@Body RequestBody requestBody);

//    //围岩处置列表下载
//    @GET("app.do?wybaojing")
//    Observable<BaojingInfo> cedianWarnDo();


//    @Part("ContactInformation")String conInfo,
//
//    //SafetyQualityPatrolNew项目名称容易掉
//    @Multipart
//    @POST("/SafetyQualityPatrolNew/rest/safetyPatrol/pictureUpload")
//    Call<ResponseBody> feedBack(@Query("type") String type, @Part() List<MultipartBody.Part> parts);
//
//    //    http://192.168.10.42:8080/CATDPS/rest/NewSafetyPatrolController/add
//    @POST("/SafetyQualityPatrolNew/rest/NewSafetyPatrolController/add")
//    Call<ResponseBody> upload(@Body YinhuanUploadBean body);
//
////    // 历史数据查询
////    @GET("app.do?lishishujuchaxun")
////    Observable<CeliangDataBean> historyCedian(@Query("cedianid") String cedianid, @Query("cediantype") String cediantype);
//
//
//    //自检下发列表
//    @POST("/SafetyQualityPatrolNew/rest/NewSafetyPatrolController/searchList")
//    Observable<InspectDispatchListData> inspectDispatch(@Body RequestBody requestBody);
//
//    //自检下发列表详情
//    @POST("/SafetyQualityPatrolNew/rest/NewSafetyPatrolController/searchOne")
//    Observable<InspectDispatchDetailData> inspectDispatchDetail(@Body RequestBody requestBody);
//
//    //人员组织机构
//    @GET("/SafetyQualityPatrolNew/rest/main/getUserByDepart")
//    Call<DepartUserTree> departmentUser();
//
//    //自检下发列表
//    @POST("/SafetyQualityPatrolNew/rest/SafetyRectification/appointRectification")
//    Observable<SuccessData> inspectDispatchupload(@Body RequestBody requestBody);
//
//    //获取统计隐患条数
//    @POST("/SafetyQualityPatrolNew/rest/CountController/searchHidden")
//    Call<YinhuanItemData> yinhuanItems(@Body RequestBody requestBody);
//
//
//    //获取审核、未审核条数
//    @POST("/SafetyQualityPatrolNew/rest/CountController/searchRectification")
//    Call<SelfShenHeData> selfItems(@Body RequestBody requestBody);
//    //获取隐患等级拥有的条数
//    @POST("/SafetyQualityPatrolNew/rest/CountController/searchhdGrade")
//    Call<YinhuanLevelData> yinhuanLevelItems(@Body RequestBody requestBody);
//
//    //整改列表
//    @POST("/SafetyQualityPatrolNew/rest/SafetyRectification/list")
//    Observable<InspectRecitifyListData> inspectRecitify(@Body RequestBody requestBody);
//
//    //    http://192.168.10.42:8080/CATDPS/rest/SafetyRectification/details?id=40288aaa6d050b15016d0515ed920013
//    @POST("/SafetyQualityPatrolNew/rest/SafetyRectification/details")
//    Call<InspectProcessDetailData> processDetail(@Body RequestBody requestBody);
//
//    //自检下复核详情
//    @POST("rest/SafetyRectification/details")
//    Observable<InspectCheckDetailData> inspectCheckDetail(@Body RequestBody requestBody);
//
//    //自检下发整改详情
//    @POST("rest/SafetyRectification/details")
//    Observable<InspectRecitifyDetailData> inspectRecitifyDetail(@Body RequestBody requestBody);
//
//    @Multipart
//    @POST("rest/safetyPatrol/pictureUpload")
//    Call<ResponseBody> recitifyOrCheck(@Query("type") String type, @Part() List<MultipartBody.Part> parts);
//
//    //自检下发列表
//    @POST("rest/SafetyRectification/Reply")
//    Observable<SuccessData> Recitifyupload(@Body RequestBody requestBody);
//
////    http://114.55.94.198:8084/SafetyQualityPatrolNew/rest/Sq_SafetyPatrolPlanController/getList1?offset=0&limit=5&sppName=&sppBeginDate=&sppEndDate=&auth=0
//    //计划列表
////    @GET("/CATDPS/rest/Sq_SafetyPatrolPlanController/getList1")
//    @GET("/SafetyQualityPatrolNew/rest/Sq_SafetyPatrolPlanController/getList1")
//    Observable<InspectPlaneListData> planList(@Query("offset") int offset1, @Query("limit") int limit, @Query("sppBeginDate") String sppBeginDate, @Query("sppEndDate") String sppEndDate
//            , @Query("sppName") String sppName, @Query("auth") String auth);
////    Observable<SuccessData> planList(@Body RequestBody requestBody);
//
//    //计划列表详情
//    @GET
//    Observable<JihuaDetailData> JihuaDetail(@Url String url);
//    //消息
//    @POST("rest/Sq_QualityPatrolPlanController/pushmessage")
//    Observable<PushMessageData> messageList(@Body RequestBody requestBody);
//    //消息
//    @POST("rest/igexinPush/delmessage")
//    Observable<PushMessageData> delMesg(@Body RequestBody requestBody);
//
//    //会议签到
//    @POST("rest/sq_SafetyMeetingController/Check")
//    Observable<PushMessageData> qianDao(@Body RequestBody requestBody);

}
