package com.briup.woss.client;

import java.util.Collection;
import java.util.Date;

import com.briup.woss.bean.WossModuleInit;
import com.briup.woss.bean.BIDR;
import com.briup.woss.bean.ConfigurationAWare;
/**
 * Client接口是采集系统网络模块客户端的规范。
 * Client的作用就是与服务器端进行通信，传递数据。
 * @author wang
 * @version new {@link Date}
 * @see com.briup.woss.WossModuleInit
 */
public interface Client extends WossModuleInit,ConfigurationAWare {
	/**
	 * send方法用于与服务器端进行交互，发送BIDR集合至服务器端。
	 * @param collection
	 * @throws Exception
	 */
	void send(Collection<BIDR> collection) throws Exception;
}
