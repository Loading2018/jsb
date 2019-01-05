package com.cloud.jsproducerremittance.controller;

import com.cloud.jsproducerremittance.entity.MakeRemittance;
import com.cloud.jsproducerremittance.entity.RemittanceTransaction;
import com.cloud.jsproducerremittance.service.impl.ApplicationInformationServiceImpl;
import com.cloud.jsproducerremittance.service.impl.GeneralRemittanceServiceImpl;
import com.cloud.jsproducerremittance.service.impl.MakeAnAppointmentToTheRemittanceServiceImpl;
import com.cloud.jsproducerremittance.service.impl.ShowRemittanceMessageServiceImpl;
import com.netflix.client.http.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "单笔汇款")
@RestController
public class ToApplyDorRheRemittance {
    @Autowired
    private ApplicationInformationServiceImpl applicationInformationService;
    @Autowired
    private GeneralRemittanceServiceImpl generalRemittanceService;
    @Autowired
    private ShowRemittanceMessageServiceImpl showRemittanceMessageService;
    @Autowired
    private MakeAnAppointmentToTheRemittanceServiceImpl makeAnAppointmentToTheRemittanceService;
    @PostMapping(value = "/addmessage")
    @CrossOrigin/*跨域的注解*/
    public RemittanceTransaction addMessage(@ApiParam(required = true, name = "remittanceTransaction", value = "添加收款人信息")  RemittanceTransaction remittanceTransaction,HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(remittanceTransaction.getRemittancetransactioncardnumber());
        return applicationInformationService.addApplicationInformationService(remittanceTransaction);
    }
    @PostMapping(value = "addgeneralremittance")
    @CrossOrigin/*跨域的注解*/
    public RemittanceTransaction addGeneralRemittance(@ApiParam(required = true, name = "remittanceTransaction", value = "添加收款人信息")  RemittanceTransaction remittanceTransaction){
        return generalRemittanceService.addGeneralRemittancerecord(remittanceTransaction);
    }
    @PostMapping(value = "aaa")
    public String all(@ApiParam(required = true, name = "remittanceTransaction", value = "添加收款人信息")  RemittanceTransaction remittanceTransaction){
        generalRemittanceService.addGeneralRemittance();
        return "";
    }

    @GetMapping("/showGathering")
    public List<RemittanceTransaction> showGathering(@ApiParam(required = true, name = "cardNumber", value = "展示")@RequestParam String cardNumber, HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return showRemittanceMessageService.showGathering(cardNumber);
    }
    @GetMapping("/showRemittance")
    public List<RemittanceTransaction> showRemittance(@ApiParam(required = true, name = "cardNumber", value = "展示")@RequestParam String cardNumber, HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return showRemittanceMessageService.showRemittance(cardNumber);
    }

    @GetMapping("/addBatch")
    public MakeRemittance addBatch(@ApiParam(required = true, name = "makeRemittance", value = "批量汇款")@RequestBody MakeRemittance makeRemittance , HttpServletResponse response, HttpServletRequest request ){
        response.setHeader("Access-Control-Allow-Origin","*");
        return makeAnAppointmentToTheRemittanceService.addMakeAnAppointmentToTheRemittance();
    }

    @PutMapping("/updateState")
    public String updateState(@ApiParam(required = true, name = "remittanceTransaction", value = "更改汇款状态") @RequestBody RemittanceTransaction remittanceTransaction){
        boolean b = generalRemittanceService.updateState(remittanceTransaction);
        if (!b){
            List<String> a = new ArrayList<String>();

            return "修改失败";
        }
        return "修改成功";
    }

}
