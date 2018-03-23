package com.sendtomoon.mozart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.base.BaseComponent;
import com.sendtomoon.mozart.service.Job;
import com.sendtomoon.mozart.service.MainService;

@Component
public class JobImpl extends BaseComponent implements Job {

	// 首次执行的休眠时间
	@Value("${firstSleepTime}")
	private String firstSleepTime;

	// 执行次数
	private static int times = 0;

	// 更新ip job标识，当前只能运行一个job
	private static boolean switchJob = false;

	@Autowired
	MainService mainService;

	@Scheduled(cron = "${cornString}")
	public void changeDNS() {
		try {
			if (switchJob) {
				return;
			}
			switchJob = true;
			// if (times <= 0) {
			// logger.info("第" + times + "次，休眠：" + firstSleepTime + "ms.");
			// Thread.sleep(Long.valueOf(firstSleepTime));
			// logger.info("第" + times + "次休眠结束");
			// return;
			// }
			long start = System.currentTimeMillis();
			logger.info("第" + times + "次更新开始；");
			mainService.renewtoGoddy();
			long end = System.currentTimeMillis();
			logger.info("第" + times + "次；更新成功。花费：" + (end - start) + "ms.");
			return;
		} catch (Exception e) {
			logger.info(e.toString());
		} finally {
			times++;
			switchJob = false;
		}
	}

}
