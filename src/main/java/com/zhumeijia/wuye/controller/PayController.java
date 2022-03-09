package com.zhumeijia.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.zhumeijia.wuye.bean.Procurement;
import com.zhumeijia.wuye.bean.ResBody;
import com.zhumeijia.wuye.bean.User_Payment;
import com.zhumeijia.wuye.service.ProcurementService;
import com.zhumeijia.wuye.service.User_PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class PayController {

    @Autowired
    User_PaymentService service;
    @Autowired
    ProcurementService pservice;
//    应用id
    @Value("${alipay_test.APP_ID}")
    private String APP_ID;
//    应用私钥
    @Value("${alipay_test.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY;

//    编码
    @Value("${alipay_test.CHARSET}")
    private String CHARSET;

//  支付宝公钥
    @Value("${alipay_test.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;

//  接口路径
    @Value("${alipay_test.GATEWAY_URL}")
    private String GATEWAY_URL;

//    格式
    @Value("${alipay_test.FORMAT}")
    private String FORMAT;


    //签名方式
    @Value("${alipay_test.SIGN_TYPE}")
    private String SIGN_TYPE;

    // 异步请求，需要使用域名
    @Value("${alipay_test.NOTIFY_URL}")
    private  String NOTIFY_URL;

    // 同步请求，不需要域名
    @Value("${alipay_test.RETURN_URL}")
    private  String RETURN_URL;

    @RequestMapping("/api/goodsqueren")
    @ResponseBody
    public ResBody goodsqueren(@RequestParam("id") int id, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest, HttpSession httpSession) throws IOException, ServletException {


        ResBody resBody = new ResBody();
        Procurement procurement = pservice.getProcurementById(id);
        System.out.println(procurement);

        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        //付款金额，必填
        //订单名称，必填
        //商品描述，可空
        // 可填，延时时间，如果填了在此时间段内没有付款则无效，这里的时间支付宝界面上减2分钟
        long etime1 = System.currentTimeMillis() + 3 * 60 * 1000;//延时函数，单位毫秒，这里是延时了3分钟
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String time_expire = df.format(new Date(etime1));
        String num = df1.format(new Date());
        request.setBizContent("{\"out_trade_no\":\"" + num + "\","
                + "\"total_amount\":\"" + procurement.getTotalmoney() + "\","
                + "\"subject\":\"" +"交"+procurement.getGname() + "\","
                + "\"body\":\"" + procurement.getGname() + "\","
                + "\"time_expire\":\"" + time_expire + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request,"GET").getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resBody.setCode(500);
        resBody.setMsg(form);
        httpSession.setAttribute("name","procurement");
        httpSession.setAttribute("id",id);
        return resBody;

    }
    @RequestMapping(value = "/api/refund", method = RequestMethod.GET)
    @ResponseBody
    public ResBody refund(@RequestParam("out_trade_no") String out_trade_no,@RequestParam("refund_amount") String refund_amount)
    {
        ResBody resBody = new ResBody();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        Map map = new HashMap();
        map.put("refund_amount",refund_amount);
        map.put("out_trade_no",out_trade_no);
        request.setBizContent(JSON.toJSONString(map));
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            if(response.getMsg().equals("Success"))
            {
                resBody.setCode(200);
                resBody.setMsg("添加成功");
                return resBody;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resBody.setCode(500);
        resBody.setMsg("失败");
        return resBody;


    }
    @RequestMapping("/api/userjiaofei")
    @ResponseBody
    public ResBody alipay_ajax_buy(@RequestParam("id") int id, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest, HttpSession httpSession) throws IOException, ServletException {


        ResBody resBody = new ResBody();


        User_Payment user_payment = service.getPaymentById(id);
        System.out.println(user_payment);

        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        //付款金额，必填
        //订单名称，必填
        //商品描述，可空
        // 可填，延时时间，如果填了在此时间段内没有付款则无效，这里的时间支付宝界面上减2分钟
        long etime1 = System.currentTimeMillis() + 3 * 60 * 1000;//延时函数，单位毫秒，这里是延时了3分钟
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String time_expire = df.format(new Date(etime1));
        String num = df1.format(new Date());
        request.setBizContent("{\"out_trade_no\":\"" + num + "\","
                + "\"total_amount\":\"" + user_payment.getValue() + "\","
                + "\"subject\":\"" +"采购"+user_payment.getPayment().getName() + "\","
                + "\"body\":\"" + user_payment.getPayment().getName() + "\","
                + "\"time_expire\":\"" + time_expire + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request,"GET").getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        resBody.setCode(500);
        resBody.setMsg(form);
        httpSession.setAttribute("name","user_payment");
        httpSession.setAttribute("id",id);
        return resBody;

    }

    @RequestMapping(value = "/api/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
            throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些

        boolean signVerified = AlipaySignature.rsaCheckV1(params,ALIPAY_PUBLIC_KEY, "UTF-8",SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        String value = ((String) httpSession.getAttribute("name"));
        System.out.println(value);
        Double total_amount1 = Double.valueOf(params.get("total_amount"));
        if(value.equals("user_payment"))
        {
            System.out.println(signVerified);
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("商户订单号=" + out_trade_no);
            System.out.println("支付宝交易号=" + trade_no);
            System.out.println("付款金额=" + total_amount);

            //支付成功，修复支付状态
//            payService.updateById(Integer.valueOf(out_trade_no));
            service.jiaofei((Integer) httpSession.getAttribute("id"));
            return "page/system/index";//跳转付款成功页面
        }else
        {
            System.out.println(signVerified);
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("商户订单号=" + out_trade_no);
            System.out.println("支付宝交易号=" + trade_no);
            System.out.println("付款金额=" + total_amount);

            //支付成功，修复支付状态
//            payService.updateById(Integer.valueOf(out_trade_no));
            pservice.queren((Integer) httpSession.getAttribute("id"));
            return "page/template/index";//跳转付款成功页面
        }
    }

}


