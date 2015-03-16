package com.bizconf.vcaasz.component.email;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RDate;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Repeat;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

import com.bizconf.vcaasz.component.email.model.SendMail;
import com.bizconf.vcaasz.constant.EmailConstant;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.util.StringUtil;

public class EmailUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		SendMail mailInfo=new SendMail();
		mailInfo.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-23 15:30:00"));
		mailInfo.setStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-23 17:30:00"));
		mailInfo.setFromEmail("messenger@vcaas.cn");
		mailInfo.setFromName("vcaas");
		mailInfo.setServerHost("mail.vcaas.cn");
		mailInfo.setServerPort("25");
		mailInfo.setUserName("messenger@vcaas.cn");
		mailInfo.setUserPass("0VH5UJ0cRRClSixPsNwB6SPI40");
		String mailSubject="发送邮件测试）";
		mailInfo.setSubject(mailSubject);
		mailInfo.setContentType("html");
		mailInfo.setUid("abcd5632ead8778c");
		mailInfo.setOrganizerEmail("martin_wang@bizconf.cn");
		mailInfo.setOrganizerName("martin_wang");
		mailInfo.setWarnType(EmailConstant.WARNTYPE_CANCEL);
		
		
		//EmailService emailService = AppContextFactory.getAppContext().getBean(EmailService.class);
		//EmailInfo info = emailService.getEmailInfoById(101024);
		
//		mailInfo.setContent("<font color=\"red\">2012年技术总结总结会议</font><br/>请点击下面的网址看会议介绍，<a href=\"http://www.baidu.com\" target=\"_blank\">百度</a><br/><br/><br/><br/>");
		
		//String content = getContent(new File("D:/martin/portal/audio-portal/WebContent/templates/email02.html"));
		String content = "nonononos";
		//System.out.println("the content is:"+content);
		mailInfo.setContent(content);
		mailInfo.setValidate(true);
		mailInfo.setToEmail("byron_huai@bizconf.cn");

		//mailInfo.addFilePath("D:\\工作日志\\git使用步骤.txt");
		//mailInfo.addFilePath("D:\\工作日志\\2013-01\\晚会小品.docx");
//		mailInfo.addCcEmail("chris_gao@bizconf.cn");
		
		//mailInfo.setCalFlag(true);
		
		mailInfo.setTimeZone("Asia/Shanghai");
		java.util.Calendar calendar=java.util.Calendar.getInstance();
		calendar.set(2014,8, 22, 14, 20,0);
		//mailInfo.setStartTime(calendar.getTime());

//		java.util.Calendar stopCalendar=java.util.Calendar.getInstance();
		calendar.set(2014,8, 22, 18, 20,0);
		//mailInfo.setStopTime(calendar.getTime());
		
		mailInfo.setAddress("office");
		mailInfo.setWarnSubject(mailSubject+"Asia/Shanghai");
		mailInfo.setBeforeMinute(30);
		mailInfo.setWarnCount(3);
		mailInfo.setGapMinute(15);
		mailInfo.setCalFlag(true);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ConfCycle cycle = new ConfCycle();
		cycle.setCycleType(3);
		cycle.setRepeatCount(5);
		cycle.setCycleValue("23");
		cycle.setBeginDate(sdf.parse("2014-08-23 14:55:00"));
		cycle.setEndDate(sdf.parse("2100-08-23 14:55:00"));
		cycle.setInfiniteFlag(0);
		
		send(mailInfo, cycle);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		ConfCycle cycle = new ConfCycle();
//		cycle.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-07-27"));
//		cycle.setInfiniteFlag(0);
//		cycle.setRepeatCount(3);
//		cycle.setCycleType(1);
//		cycle.setCycleValue("2");
//		Recur recur =  getRecurByCycle(cycle);
//		Date now = DateUtil.getGmtDate(null);
//		Date seed = sdf.parse("2014-07-21 12:00:00");
//		Date start = sdf.parse("2014-07-13 12:00:00");
//		Date nextDate = recur.getNextDate(new net.fortuna.ical4j.model.Date(seed), new net.fortuna.ical4j.model.Date(start));
//		System.out.println(nextDate);
//		System.out.println(sdf.format(nextDate));
		
	}
	
	
	private static String getContent(File file){
		String content = null;
		StringBuilder budiler= new StringBuilder();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br=new BufferedReader(reader);
			while((content=br.readLine())!=null){
				
				budiler.append(content);
			}
			br.close();
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return budiler.toString();
	}
	
	private static Properties getEmailProperties(SendMail mailInfo){
		Properties props=null;
		if(mailInfo != null){
			props = new Properties();
			props.put("mail.smtp.host", mailInfo.getServerHost()); // SMTP服务器
			props.put("mail.smtp.port", mailInfo.getServerPort()); // 设置smtp端口
//			props.put("mail.transport.protocol", "smtp"); // 发邮件协议
			props.put("mail.smtp.auth", mailInfo.isValidate() ? "true" : "false");
		}
		return props;
	}
	
	public static boolean send(SendMail mailInfo){
		
		return send(mailInfo,null);
	}
	
	/**
	 * 带周期日历提醒的邮件
	 * @param mailInfo
	 * @param cycle
	 * @return
	 */
	public static boolean send(SendMail mailInfo,ConfCycle cycle){
		boolean sendStatus=false;
		if(mailInfo != null ){
			// 判断是否需要身份认证
			boolean validate=mailInfo.isValidate();
			EmailAuthor authenticator = null;
			Properties properties = getEmailProperties(mailInfo);
			if(validate){
				if (mailInfo.isValidate()) {
					// 如果需要身份认证，则创建一个密码验证器
					authenticator = new EmailAuthor(mailInfo.getUserName(),mailInfo.getUserPass());
				}
			}
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			//Session mailSession = Session.getDefaultInstance(properties,authenticator);
			Session mailSession = Session.getInstance(properties,authenticator);
			Message mailMessage=null;
			Address[] ccEmailArray=null;
			
			try {
				// 根据session创建一个邮件消息
				mailMessage = new MimeMessage(mailSession);
				// 设置邮件消息的发送者
				mailMessage.setFrom(new InternetAddress(mailInfo.getFromEmail(),mailInfo.getFromName()));
				
				// 设置邮件接收者
				mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.getToEmail()));
				
				
				
				//设置邮件抄送者
				List<String> emailCcList=mailInfo.getCcEmailList();
				if(emailCcList != null && emailCcList.size() > 0){
					ccEmailArray=new Address[emailCcList.size()];
					for(int ii=0;ii<emailCcList.size();ii++){
						ccEmailArray[ii]=new InternetAddress(emailCcList.get(ii));
					}
					mailMessage.setRecipients(Message.RecipientType.CC, ccEmailArray);
				}
				emailCcList=null;
				
				
				
				Multipart multipart = null;//new MimeMultipart();
				
				
				
				//如果是添加OUTLOOK日历
				if(mailInfo.isCalFlag()){
					multipart=getCalMultipart(mailInfo,cycle);
				}
				
				if(multipart==null){
					multipart=new MimeMultipart();
					
				}
				
				// 设置邮件消息的主题
				mailMessage.setSubject(mailInfo.getSubject());
				
				
				
				//设置邮件附件
				List<String> fileList=mailInfo.getFilePathList();
				if(fileList != null && fileList.size() > 0){
					BodyPart filePart= null;
					DataSource dataSource =null;
					for(String eachFilePath:fileList){
						if(eachFilePath != null){
							dataSource = new FileDataSource(eachFilePath);
							if(dataSource != null){
								filePart = new MimeBodyPart();
								filePart.setDataHandler(new DataHandler(dataSource));
								filePart.setFileName(MimeUtility.encodeText(new File(eachFilePath).getName()));
								multipart.addBodyPart(filePart);
							}
							filePart= null;
							dataSource =null;
						}
					}
				}
				fileList=null;
				
				
				
				//设置邮件内容及内容格式
				String contentType=mailInfo.getContentType();
				if(contentType==null || "".equals(contentType.trim())){
					contentType="txt";
				}
				contentType=contentType.toLowerCase();
				String mailContent=mailInfo.getContent();
				BodyPart bodyContent= new MimeBodyPart();
				if("html".equals(contentType)){
					bodyContent.setContent(mailContent, "text/html;charset=utf8");
				}else{
					bodyContent.setText(mailContent);
				}
				multipart.addBodyPart(bodyContent);
				bodyContent=null;
				
				
				
				// 设置邮件消息发送的时间
				mailMessage.setSentDate(new Date());
				mailMessage.addHeader("X-Priority","1");
				
				
				mailMessage.setContent(multipart);
				mailMessage.saveChanges();
				Transport.send(mailMessage);
				sendStatus = true;
				
				
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return sendStatus;
	}
	
	
	/**
	 * 获取邮件中OUTLook日历格式
	 * @param mailInfo
	 * @return
	 */
	public static Multipart getCalMultipart(SendMail mailInfo) {
		 
		return getCalMultipart(mailInfo,null);
	}
	
	
	public static Multipart getCalMultipart(SendMail mailInfo,ConfCycle cycle) {
		Multipart multipart = null;//
		if (mailInfo != null) {
			try{
				multipart=new MimeMultipart();
				BodyPart icalBodyPart=new MimeBodyPart();
				
				//时区
				String timeZone=mailInfo.getTimeZone();
				TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
				TimeZone timezone = registry.getTimeZone(timeZone);
				
				//会议开始时间、结束时间、地点
				java.util.Calendar calendar=java.util.Calendar.getInstance();
				calendar.setTimeZone(timezone);
				calendar.setTime(mailInfo.getStartTime());
				//设置会议开始时间
				DateTime startTime = new DateTime(calendar.getTime());
				Recur recur = getRecurByCycle(cycle);
				calendar.setTime(mailInfo.getStopTime());
				
				DateTime endTime = new DateTime(calendar.getTime());
				
				
				VEvent vevent =new VEvent(startTime, endTime, mailInfo.getSubject());
				
				
				vevent.getProperties().add(timezone.getVTimeZone().getTimeZoneId());//时区
				vevent.getProperties().add(new Location(mailInfo.getAddress()));//会议地点
				
				if(recur!=null){
//					Date nextRecurDate = recur.getNextDate(new net.fortuna.ical4j.model.Date(DateUtil.getGmtDate(null)), 
//							new net.fortuna.ical4j.model.Date(DateUtil.getGmtDate(null)));
					//recur.getNextDate(seed, startDate);
					RRule rule = new RRule(recur);
					vevent.getProperties().add(rule);
				}
				
				//under this line is for gmail remind
				ParameterList pls =  new ParameterList();
				pls.add(new Cn(mailInfo.getToEmail()));
				pls.add(new Rsvp(true));
				Attendee attendee = new Attendee(pls,mailInfo.getToEmail()); 
				attendee.setCalAddress(new URI("mailto:"+mailInfo.getToEmail()));
				vevent.getProperties().add(attendee);//参会者
				//up this line is for gmail remind 
				
				
				Organizer organizer = new Organizer();
				if(!StringUtil.isEmpty(mailInfo.getOrganizerName())){
					organizer.getParameters().add(new Cn(mailInfo.getOrganizerName()));
				}else if(!StringUtil.isEmpty(mailInfo.getFromName())){
					organizer.getParameters().add(new Cn(mailInfo.getFromName()));
				}else{
					organizer.getParameters().add(new Cn(mailInfo.getFromEmail()));
				}
				
				if(StringUtil.isEmpty(mailInfo.getOrganizerEmail())){
					organizer.setCalAddress(new URI("mailto:"+mailInfo.getFromEmail()));
				}else{
					organizer.setCalAddress(new URI("mailto:"+mailInfo.getFromEmail()));
				}
		        vevent.getProperties().add(organizer);//邮件主题
		        vevent.getProperties().add(new Description(mailInfo.getSubject()));
		        
		        
		        if(StringUtil.isEmpty(mailInfo.getUid())){
		        	vevent.getProperties().add(new UidGenerator("meeting invite").generateUid());// 设置uid
		        }else{
		        	Uid id = new Uid(mailInfo.getUid());
		        	vevent.getProperties().add(id);// 设置uid
		        }
		        
		        
				//设置提醒,
				//时间 提前10分钟
				Integer beforeMinute=mailInfo.getBeforeMinute();
				if(beforeMinute!=null && beforeMinute .intValue() >0){
					beforeMinute=-1 * beforeMinute.intValue() ;
				}else{
					beforeMinute=-10;
				}
				
				if(mailInfo.getWarnType() != EmailConstant.WARNTYPE_CANCEL){
					VAlarm valarm = new VAlarm(new Dur(0, 0, beforeMinute , 0));
					//提醒总次数
					Integer warnCount=mailInfo.getWarnCount();
					if(warnCount==null || warnCount.shortValue() < 0){
						warnCount=3;
					}
					valarm.getProperties().add(new Repeat(warnCount));
					
					//间隔分钟数
					Integer gapMinute=mailInfo.getGapMinute();
					if(gapMinute==null || gapMinute.intValue() < 0){
						gapMinute=5;
					}
					valarm.getProperties().add(new Duration(new Dur(0, 0, gapMinute, 0)));
					
					//提醒窗口显示的文字信息
					valarm.getProperties().add(Action.DISPLAY);
	//		        String warn
					valarm.getProperties().add(new Description(mailInfo.getWarnSubject()));
					vevent.getAlarms().add(valarm);//将VAlarm加入VEvent
				}
				
				Calendar icsCalendar = new Calendar();
				icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
				icsCalendar.getProperties().add(Version.VERSION_2_0);
				if(mailInfo.getWarnType() != EmailConstant.WARNTYPE_CANCEL){
					icsCalendar.getProperties().add(Method.REQUEST);
				}else{
					Sequence sequence = new Sequence();
					sequence.setValue("2");
					vevent.getProperties().add(sequence);
					icsCalendar.getProperties().add(Method.CANCEL);
				}
				icsCalendar.getComponents().add(vevent);//将VEvent加入Calendar
				CalendarOutputter co = new CalendarOutputter(false);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				icsCalendar.validate();
				
				//测试代码
//				StringWriter out = new StringWriter();
//				co.output(icsCalendar, out);
//				System.out.println(out.toString());
				
				co.output(icsCalendar, os);
				
				byte[] mailbytes = os.toByteArray();
				
				if(mailInfo.getWarnType() != EmailConstant.WARNTYPE_CANCEL){
					icalBodyPart.setContent(mailbytes, "text/calendar;method=REQUEST;charset=UTF-8");
				}else{
					icalBodyPart.setContent(mailbytes, "text/calendar;method=CANCEL;charset=UTF-8");
				}
				
				multipart.addBodyPart(icalBodyPart);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return multipart;
	}	
	
	public static Recur getRecurByCycle(ConfCycle cycle){
		if(cycle!=null){
			Recur recur = new Recur(); 
			//周期信息
			String cycleInfo = cycle.getCycleValue();
			String infos[] = cycleInfo.split(";");
			
			int index = 0 ;
			List<Integer> indexs = new ArrayList<Integer>();
			int seq = 0;
			if(infos.length == 1){
					String indexstr[] = infos[0].split(",");
					for (int i = 0; i < indexstr.length; i++) {
						indexs.add(Integer.parseInt(indexstr[i]));
					}
					index = indexs.get(0);
				
			}else if(infos.length == 2){
				seq = Integer.parseInt(infos[0]);
				index = Integer.parseInt(infos[1]);
			}
			
			//周期的类型
			int cycleType = cycle.getCycleType().intValue();
			switch (cycleType) {
			case 1:
				recur.setFrequency(Recur.DAILY);
				recur.setInterval(index);
				break;
			case 2:
				recur.setFrequency(Recur.WEEKLY);
				for (Integer weekday:indexs) {
					switch (weekday) {
					case 1:
						recur.getDayList().add(WeekDay.SU);
						break;
					case 2:
						recur.getDayList().add(WeekDay.MO);
						break;
					case 3:
						recur.getDayList().add(WeekDay.TU);
						break;
					case 4:
						recur.getDayList().add(WeekDay.WE);
						break;
					case 5:
						recur.getDayList().add(WeekDay.TH);
						break;
					case 6:
						recur.getDayList().add(WeekDay.FR);
						break;
					case 7:
						recur.getDayList().add(WeekDay.SA);
						break;
					default:
						throw new RuntimeException("un support week index!!!");
					}
				}
				break;
			case 3:
				recur.setFrequency(Recur.MONTHLY);
				if(seq != 0){
					switch (index) {
					case 1:
						recur.getDayList().add(WeekDay.SU);
						break;
					case 2:
						recur.getDayList().add(WeekDay.MO);
						break;
					case 3:
						recur.getDayList().add(WeekDay.TU);
						break;
					case 4:
						recur.getDayList().add(WeekDay.WE);
						break;
					case 5:
						recur.getDayList().add(WeekDay.TH);
						break;
					case 6:
						recur.getDayList().add(WeekDay.FR);
						break;
					case 7:
						recur.getDayList().add(WeekDay.SA);
						break;
					default:
						throw new RuntimeException("un support week index!!!");
					}
					recur.getSetPosList().add(seq);
				}else{
					recur.getMonthDayList().add(index);
				}
				break;
			default:
				throw new RuntimeException("un know cycle type! ");
				 
			}
			boolean unlimited = cycle.getInfiniteFlag().intValue() == 0 ?false:true;
			//设置结束周期
			if (!unlimited) {
				int count = cycle.getRepeatCount();
				if(count>0){
					recur.setCount(count);
				}else{
					recur.setUntil(new net.fortuna.ical4j.model.Date(cycle.getEndDate()));
				}
			}
			return recur;
		}
		return  null;
	}
	
	public static void addRDate(String subject) 
	        throws IOException, ParserException, ValidationException, 
			URISyntaxException, java.text.ParseException { 
	    Calendar calendar = new Calendar(); 
	    calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN")); 
	    calendar.getProperties().add(Version.VERSION_2_0); 
	    calendar.getProperties().add(CalScale.GREGORIAN); 
		
	    PeriodList periodList = new PeriodList(); 
	    ParameterList paraList = new ParameterList(); 
	    DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm"); 
	    DateTime startDate1 = new DateTime(format.parse(("11/09/2009 08:00"))); 
	    DateTime startDate2 = new DateTime(format.parse(("11/10/2009 09:00"))); 
	    DateTime endDate1 = new DateTime(format.parse(("11/09/2019 09:00"))); 
	    DateTime endDate2 = new DateTime(format.parse(("11/10/2019 11:00"))); 
	    periodList.add(new Period(startDate1, endDate1)); 
	    periodList.add(new Period(startDate2, endDate2)); 
		
	    VEvent event = new VEvent(startDate1, endDate1, subject); 
	    event.getProperties().add(new Uid(new UidGenerator("iCal4j").
			generateUid().getValue())); 
	    paraList.add(ParameterFactoryImpl.getInstance().createParameter(
			Value.PERIOD.getName(), Value.PERIOD.getValue())); 
	    RDate rdate = new RDate(paraList,periodList); 
	    event.getProperties().add(rdate); 
	    calendar.getComponents().add(event); 
	    // 验证
	    calendar.validate(); 
	    // FileOutputStream fout = new FileOutputStream(out); 
	   
	    StringWriter sWriter = new StringWriter();
	    
	    CalendarOutputter outputter = new CalendarOutputter(); 
	    outputter.output(calendar, sWriter);	
	    
	    System.out.println(sWriter.toString());
	    
	    
	}
	
	public static void addRrule(String subject) 
			throws IOException, ParserException, ValidationException, 
			URISyntaxException, java.text.ParseException { 
		 	Calendar calendar = new Calendar();     
		    DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm"); 
		    DateTime start = new DateTime(format.parse("11/07/2014 08:00").getTime()); 
		    DateTime end = new DateTime(format.parse("11/09/2014 09:00").getTime()); 

		    calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN")); 
		    calendar.getProperties().add(Version.VERSION_2_0); 
		    calendar.getProperties().add(CalScale.GREGORIAN); 
		   
		    VEvent event = new VEvent(start, end, subject); 
		    event.getProperties().add(new Uid(new UidGenerator("iCal4j").generateUid()
				.getValue())); 
		   

		    //Recur recur = new Recur(Recur.WEEKLY, 4); 
		    //TODO Recur recur = new Recur("FREQ=MONTHLY;INTERVAL=2;BYWEEKDAY=2");
		    // recur.setFrequency("FREQ=MONTHLY;");
		    //recur.setInterval(2); 
		    //DAILY /WEEKLY/MONTHLY
		    //INTERVAL
		    //BYWEEKDAY/BYDAY=MO,FR/
		    //BYSETPOS=2 第
		    //BYMONTHDAY 月
		    //BYWEEKDAY
		    Recur recur1 = new Recur("FREQ=WEEKLY;INTERVAL=2;BYWEEKDAY=2;");
		    RRule rule = new RRule(recur1); //每 2 个月的第 7 天发生，生效时间: 2014/11/7，从 8:00 开始的 49 小时。

		    
		    //DtStart dtStart = new DtStart(new net.fortuna.ical4j.model.Date(), true);
		   // VTimeZone vTimeZone = new VTimeZone();
		    //vTimeZone.getProperties().add(property)
		    
		    event.getProperties().add(rule); 
		    calendar.getComponents().add(event); 
			 
			// 验证
		    calendar.validate(); 
		   
		    StringWriter sWriter = new StringWriter();
			CalendarOutputter outputter = new CalendarOutputter(); 
			outputter.output(calendar, sWriter);	
			
			System.out.println(sWriter.toString());
		
		
	}

}
