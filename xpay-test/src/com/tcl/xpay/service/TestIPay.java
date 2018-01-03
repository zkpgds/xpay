package com.tcl.xpay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcl.xpay.vo.TpayVO;

public class TestIPay {

	private static final Logger logger = LoggerFactory.getLogger(TestIPay.class);

	public static void main(String[] args) {
		try {
			String profile = "development";
			if (args != null && args.length > 0) {
				profile = args[0];
			}
			System.setProperty("spring.profiles.active", profile);
			ApplicationContext springContext = new ClassPathXmlApplicationContext("classpath*:/spring/*-beans.xml");
			logger.info(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " service server started!");
			ITpayService tpayService = (ITpayService) springContext.getBean("tpayService");
			logger.info("findById={}", tpayService.findById(222222));
			TpayVO tpayVO = new TpayVO();
			tpayVO.setAppId("222");
			tpayVO.setChannelMerchantNo("test");
			tpayVO.setCreateTime(new Date());
			tpayVO.setChannelPayTime(new Date());
			tpayVO.setChannelRoute("222");
			tpayVO.setChannelTradeNo("222");
			tpayVO.setChannelTradeNoProxy("22202");
			BigDecimal bigDecimal = new BigDecimal("222");
			tpayVO.setDiscountAmount(bigDecimal);
			tpayVO.setMerchantNo("222");
			tpayVO.setNickname("小強");
			tpayVO.setOutNotifyStatus("111");
			tpayVO.setOutNotifyTime(new Date());
			tpayVO.setOutNotifyUrl("http://localhost:8080");
			tpayVO.setOutTradeExt("testttt");
			tpayVO.setOutTradeNo("222204");
			tpayVO.setOutTradeSource("terere");
			tpayVO.setOutTradeTime(new Date());
			tpayVO.setPayTime(new Date());
			tpayVO.setPayAmount(bigDecimal);
			tpayVO.setPayStatus("22");
			tpayVO.setThirdAppMark("2222");
			tpayVO.setTotalAmount(bigDecimal);
			tpayVO.setTradeNo("222204");
			tpayVO.setUpdateTime(new Date());
			tpayVO.setUserId("admin");
			tpayVO.setUserRelId("testadmin");
			tpayVO.setUserType("44");
			tpayVO.setVersion("1");
			tpayVO.setId(222222);
			// int i=tpayService.insert(null, tpayVO);
			// logger.info("{}",i);
			logger.info("findVO={}", tpayService.findVO(222222));

			TpayVO tpayVO1 = new TpayVO();
			tpayVO1.setAppId("111");
			tpayVO1.setMerchantNo("1225191506");
			tpayVO1.setThirdAppMark("helloworld");
			tpayVO1.setId(11);
			tpayService.updateById(tpayVO1);
			logger.info("findVO={}", tpayService.findById(11));
			tpayService.deleteById(null, tpayVO1);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(1);
		}
	}
}
