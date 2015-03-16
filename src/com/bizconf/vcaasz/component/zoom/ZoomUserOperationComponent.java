package com.bizconf.vcaasz.component.zoom;

import java.util.List;
import java.util.Map;


/** 
 *   
 * @package com.bizconf.video.component.mcu 
 * @description: zoom 用户相关的操作接口
 * @author Martin
 * @date 2014年6月6日 上午9:57:15 
 * @version 
 */
public interface ZoomUserOperationComponent {
	
	/**
	 * 创建用户
	 * @param email 邮件地址
	 * @param type  用户类型
	 * @param password 用户密码
	 * @param firstName 
	 * @param lastName
	 * @param disableChat 是否允许聊天
	 * @param enableEncrytion
	 * @return
	 */
	public Map<String, Object> create(String apiKey,String apiToken,String email,int type,int meetingCapacity,String password,String firstName,
			String lastName,boolean disableChat,boolean enableEncrytion);

	
	/**
	 * ma 接口创建用户
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param email
	 * @param type
	 * @param meetingCapacity
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param disableChat
	 * @param enableEncrytion
	 * @return
	 */
	public Map<String, Object> maCreate(String apiKey,String apiToken,String accountId,
			String email, int type,int meetingCapacity, String password,
			String firstName, String lastName, boolean disableChat,
			boolean enableEncrytion);
	
	/**
	 * 修改用户信息
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param disableChat
	 * @param enableEncrytion
	 * @return
	 */
	public int update(String apiKey,String apiToken,String userId,int type,int meetingCapacity,String firstName,
			String lastName,boolean disableChat,boolean enableEncrytion);
	
	
	/**
	 * ma 接口修改用户
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param userId
	 * @param type
	 * @param meetingCapacity
	 * @param firstName
	 * @param lastName
	 * @param disableChat
	 * @param enableEncrytion
	 * @return
	 */
	public int maUpdate(String apiKey,String apiToken,String accountId,
			String userId, int type, int meetingCapacity,String firstName,
			String lastName, boolean disableChat, boolean enableEncrytion);
	/**
	 * 
	 * @param userId
	 * @param meetingCapacity
	 * @return
	 */
	public boolean modifyPortNum(String apiKey,String apiToken,String userId,int meetingCapacity);

	
	/**
	 * ma 修改用户并发会议方数
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param userId
	 * @param meetingCapacity
	 * @return
	 */
	public boolean maModifyPortNum(String apiKey,String apiToken,
			String accountId, String userId, int meetingCapacity);
	
	/**
	 * 修改密码
	 * @param password
	 * @return
	 */
	public boolean modifyPassword(String apiKey,String apiToken,String userId,String password);
	
	
	/**
	 * ma 接口方式修改
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean maModifyPassword(String apiKey,String apiToken,
			String accountId, String userId, String password);
	
	/**
	 * 删除一个用户
	 * @param id
	 * @return
	 */
	public boolean delete(String apiKey,String apiToken,String id);
	
	
	/**
	 * ma接口方式删除
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param userId
	 * @return
	 */
	public boolean maDelete(String apiKey,String apiToken,
			String accountId,String userId);
	/**
	 * 查询所有的用户信息列表
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	List<Map<String, Object>> listAll(String apiKey,String apiToken,int pageSize,int pageNumber);
	
	
	/**
	 * 分页查询所有的用户信息列表
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	Map<String, Object> pagelistInfo(String apiKey,String apiToken,int pageSize,int pageNumber);




	/**
	 * 获取某个用户的信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> get(String apiKey,String apiToken,String id);
	
	
	
	/**
	 * 获取某个用户的TOKEN信息
	 * @param id
	 * @return
	 */
	public String getZoomToken(String apiKey,String apiToken,String id);
	
	
	/**
	 * 获取某个用户的PMI信息
	 * @param id
	 * @return
	 */
	public String getZoomPMI(String apiKey,String apiToken,String id);
	
	/**
	 * MA 获取用户PMI
	 * @param apiKey
	 * @param apiToken
	 * @param account
	 * @param id
	 * @return
	 */
	public String maGetZoomPMI(String apiKey,String apiToken,
			String account,String id);
	
	/**
	 * 修改某个用户的PMI信息
	 * @param id
	 * @param id
	 * return 
	 * */
	public boolean setZoomPMI(String apiKey,String apiToken,String id,String pmi);
	
	
	/**
	 * ma 接口修改某个用户的PMI信息
	 * @param apiKey
	 * @param apiToken
	 * @param accountId
	 * @param id
	 * @param pmi
	 * @return
	 */
	public boolean maSetZoomPMI(String apiKey,String apiToken,
			String accountId, String id, String pmi);
	
	
	/**
	 * 修改该用户的所属账号
	 * @param masterKey master account的key
	 * @param masterSecret master account的Secret
	 * @param accountId 新的sub account的id
	 * @param userId 需要更改的用户zoom id
	 * @return
	 */
	public int changeUserOwnerAccount(String masterKey, String masterSecret,String accountId,String userId);
	
}
