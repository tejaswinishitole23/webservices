package in.rk.learn.web.java.springbootrest.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
   @Scheduled(fixedRate = 100000)
   public void fixedRateSch() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

      Date now = new Date();
      String strDate = sdf.format(now);
      System.out.println("Fixed Rate scheduler:: " + strDate);
   }
   
   @Scheduled(fixedDelay = 100000, initialDelay = 10000)
   public void fixedDelaySch() {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	   
	   Date now = new Date();
	   String strDate = sdf.format(now);
	   System.out.println("Fixed Delay scheduler:: " + strDate);
   }
}