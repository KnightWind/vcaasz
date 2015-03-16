package com.bizconf.vcaasz.entity;

/**
 * 会议参数设置
 * @author shhc
 *
 */
public class DefaultConfig implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5347603210737122134L;
	
	/**
	 * 主键ID号
	 */
	private Integer id;
	
	/**
	 * 用户ID号
	 */
	private Integer userId;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 会议类型
	 */
	private Integer mtgType;
	
	/**
	 * 最大人数
	 */
	private Integer maxUser;
	
	/**
	 * 最大音频数
	 */
	private Integer maxAudio;
	
	/**
	 * 最大视频数
	 */
	private Integer maxVideo;
	
	/**
	 * 视频设置类型
	 * 优先保证画质清晰 --最大分辨率为：1280*720，默认为：640*480
	 * 优先保证视频流畅 -- 最大分辨率704*576，默认为：640*480
	 * 优先保证网络带宽 --最大分辨率：352*288，默认为：160*120
	 */
	private String videoType;
	
	/**
	 * 最大分辨率
	 */
	private String maxDpi;
	
	/**
	 * 默认分辨率
	 */
	private String defaultDpi;
	
	/**
	 * 开启Ipad功能
	 */
	private Integer openIpad;
	
	/**
	 * 允许提前入会时间
	 * 以分钟为单位
	 */
	private Integer aheadTimes = 10;
	
	/**
	 * 权限配置管理：共32位，（1：使用，0：不使用）
	 *  第1位：换页 
		第2位：批注 
		第3位：与所有人聊天 
		第4位：与主持人聊天 
		第5位：与参会人聊天 
	 *   其它全为0
	 */
	private String priviBits = "";
	
	/**
	 * Client功能配置
	 * 共34位，0是不启用，1是启用  
		第2位	文档共享
		第3位	屏幕共享
		第4位	白板
		第5位	笔记
		第8位	视频
		第9位	音频
		第10位	聊天
		第11位	公告
		第12位	文件传输
		第13位	录制
		第15位	会议助理
		第16位	私聊
		第17位	组聊
		第22位	媒体共享

	 * */
	private String clientConfig;
	
	/**
	 * 
	 * 会议的功能位由01 字符串组成, 32 位, 每一位表示一个特定的功能
		• 第1 位表示该会议会控时, 是否需要对操作者鉴权
		• 第2 位表示服务器混音
		• 第3 位表示自动外邀
		• 第4 位表示是否录制
		• 第5 位表示标清或高清会议
		• 第6 位表示是否智真会议
		• 第7 位表示是否使用安全会议号
		• 第8 位表示会议模式(0 : 自由模式; 1 : 主持人模式)
		• 第9 位表示是否为安全会议
		• 第10 位表示该会议是否通过用户pin 码入会
		• 第11 位表示麦克风状态是否关闭(0 : 关闭; 1 : 打开)
		• 第12 位表示是否是UC 会议(0: 否; 1: 是)
		• 第13 位表示是否启用会场放音(0: 否; 1: 是)
		• 第14 位表示是否启用录制会场录音(0: 否; 1: 是)
		• 第15 位表示创建语音会议时是否需要预留多媒体资源(0: 否;1: 是)
		• 第16 位表示创建会议时IVR 的放音类型(0: 标识播放预配置的语音; 1: 标识静默方式，即不放音)
		• 第17 位表示是否启用入会过滤功能(0：否，1：是)
		• 第18 位表示是否支持超大会场(0：否，1：是)
		• 第19 位表示预约会议时是否屏蔽创建会场(0：不屏蔽；1：屏蔽)
		• 第20 位表示该预约会议是否加密会议(0：非加密会议；1：加密会议)
		• 第21 位表示是否勾选Internet Lock-Out Controls(0 表示禁用涉外会议,Internet 用户可接入.1 表示启用涉外会议,Ineternet 无法接入)
		• 其他用0 补全

	 */
	private String funcBits = "";
	
	
	/*
	 * 会议功能：默认缺省设置目前只设置电话功能，视频功能不做配置
	 * 		0、数据会议功能（页面什么都不勾选）
	 * 		1 、电话功能
	 * 		2、视频功能 
	 * 		3、视频功能 + 电话功能
	 * */
	private Integer confType = 0;
	
	/**
	 * 外呼功能（当会议由电话功能时才可以外呼）
	 */
	private int allowCall = 0;  

	// Constructors

	/** default constructor */
	public DefaultConfig() {
	}

	public DefaultConfig(Integer id, Integer userId, Integer siteId,
			Integer mtgType, Integer maxUser, Integer maxAudio,
			Integer maxVideo, String videoType, String maxDpi,
			String defaultDpi, Integer openIpad, Integer aheadTimes,
			String priviBits, String clientConfig, String funcBits,
			Integer confType, int allowCall) {
		super();
		this.id = id;
		this.userId = userId;
		this.siteId = siteId;
		this.mtgType = mtgType;
		this.maxUser = maxUser;
		this.maxAudio = maxAudio;
		this.maxVideo = maxVideo;
		this.videoType = videoType;
		this.maxDpi = maxDpi;
		this.defaultDpi = defaultDpi;
		this.openIpad = openIpad;
		this.aheadTimes = aheadTimes;
		this.priviBits = priviBits;
		this.clientConfig = clientConfig;
		this.funcBits = funcBits;
		this.confType = confType;
		this.allowCall = allowCall;
	}



	public DefaultConfig( Integer maxAudio,
			Integer maxVideo, String videoType, String maxDpi,
			String defaultDpi, Integer openIpad, Integer aheadTimes,
			String priviBits, String clientConfig, String funcBits) {
		this.maxAudio = maxAudio;
		this.maxVideo = maxVideo;
		this.videoType = videoType;
		this.maxDpi = maxDpi;
		this.defaultDpi = defaultDpi;
		this.openIpad = openIpad;
		this.aheadTimes = aheadTimes;
		this.priviBits = priviBits;
		this.clientConfig = clientConfig;
		this.funcBits = funcBits;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getMtgType() {
		return this.mtgType;
	}

	public void setMtgType(Integer mtgType) {
		this.mtgType = mtgType;
	}

	public Integer getMaxUser() {
		return this.maxUser;
	}

	public void setMaxUser(Integer maxUser) {
		this.maxUser = maxUser;
	}

	public Integer getMaxAudio() {
		return this.maxAudio;
	}

	public void setMaxAudio(Integer maxAudio) {
		this.maxAudio = maxAudio;
	}

	public Integer getMaxVideo() {
		return this.maxVideo;
	}

	public void setMaxVideo(Integer maxVideo) {
		this.maxVideo = maxVideo;
	}

	public String getVideoType() {
		return this.videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getMaxDpi() {
		return this.maxDpi;
	}

	public void setMaxDpi(String maxDpi) {
		this.maxDpi = maxDpi;
	}

	public String getDefaultDpi() {
		return this.defaultDpi;
	}

	public void setDefaultDpi(String defaultDpi) {
		this.defaultDpi = defaultDpi;
	}

	public Integer getOpenIpad() {
		return this.openIpad;
	}

	public void setOpenIpad(Integer openIpad) {
		this.openIpad = openIpad;
	}

	public Integer getAheadTimes() {
		return this.aheadTimes;
	}

	public void setAheadTimes(Integer aheadTimes) {
		this.aheadTimes = aheadTimes;
	}

	public String getPriviBits() {
		return priviBits;
	}

	public void setPriviBits(String priviBits) {
		this.priviBits = priviBits;
	}

	public String getClientConfig() {
		return clientConfig;
	}

	public void setClientConfig(String clientConfig) {
		this.clientConfig = clientConfig;
	}

	public String getFuncBits() {
		return funcBits;
	}

	public void setFuncBits(String funcBits) {
		this.funcBits = funcBits;
	}
	
	public Integer getConfType() {
		return confType;
	}

	public void setConfType(Integer confType) {
		this.confType = confType;
	}
	
	public int getAllowCall() {
		return allowCall;
	}

	public void setAllowCall(int allowCall) {
		this.allowCall = allowCall;
	}

	/**
	 * 是否有电话功能
	 */
	public boolean hasPhoneFunc() {
		if (this.confType.intValue() == 1 || this.confType.intValue() == 3) {
			 return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultConfig other = (DefaultConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefaultConfig [id=" + id + ", userId=" + userId + ", siteId="
				+ siteId + ", mtgType=" + mtgType + ", maxUser=" + maxUser
				+ ", maxAudio=" + maxAudio + ", maxVideo=" + maxVideo
				+ ", videoType=" + videoType + ", maxDpi=" + maxDpi
				+ ", defaultDpi=" + defaultDpi + ", openIpad=" + openIpad
				+ ", aheadTimes=" + aheadTimes + ", priviBits=" + priviBits
				+ ", clientConfig=" + clientConfig + ", funcBits=" + funcBits
				+ ", confType=" + confType + ", allowCall=" + allowCall + "]";
	}

}