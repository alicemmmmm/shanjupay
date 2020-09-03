package com.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.AppService;
import com.shanjupay.merchant.api.dto.AppDTO;
import com.shanjupay.merchant.util.SecurityUtil;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppController
 * @Description TODO
 * @Author LiHongBin
 * @Date 2020\7\31 0031 21:54
 * @Version 1.0
 **/
@RestController
@Api(value = "商户平台-应用管理", tags = "商户平台-应用相关", description = "商户平台-应用相关desc")
public class AppController {

    @Reference
    private AppService appService;

    @ApiOperation("商户创建应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "app", value = "应用信息", required = true,
                    dataType = "AppDTO", paramType = "body")})
    @PostMapping(value = "/my/apps")
    public AppDTO createApp(@RequestBody AppDTO app){
        //得到商户id
        Long merchantId = SecurityUtil.getMerchantId();

        return  appService.createApp(merchantId,app);
    }

    @ApiOperation("查询商户下应用列表")
    @GetMapping(value = "/my/apps")
    public List<AppDTO> queryMyApps(){
        //商户id
        Long merchantId = SecurityUtil.getMerchantId();
        List<AppDTO> appDTOList = appService.queryAppByMerchant(merchantId);
//        System.out.println(merchantId);
//        appDTOList.forEach(appDTO -> System.out.println(appDTO));
        return appDTOList;
    }

    @ApiOperation("根据应用id查询用户信息")
    @ApiImplicitParam(value = "应用id",name = "appId",required = true, dataType = "String",paramType = "path")
    @GetMapping(value = "/my/apps/{appId}")
    public AppDTO getApp(@PathVariable("appId") String appId){

        return appService.getAppById(appId);
    }



}
