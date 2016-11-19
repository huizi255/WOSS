package com.briup.woss.server;

import java.util.Date;

import com.briup.woss.bean.ConfigurationAWare;
import com.briup.woss.bean.WossModuleInit;
import com.briup.woss.common.Configuration;
/**
 * Server接口提供了采集系统网络模块服务器端的标准。
 * Server的实现类需要实现与采集体统客户端进行交互，
 * 并调用DBStore将接收的数据持久化。
 * 当Server的实现类执行revicer()方法时，Server开始监听端口。
 * 当调用Server的shutdown方法时，Server将安全的停止服务。
 * @author wang
 * @version new {@link Date}
 * @see com.briup.woss.WossModuleInit
 */
public interface Server extends WossModuleInit,ConfigurationAWare{
	/**
	 *  该方法用于启动这个Server服务，
	 *  开始接收客户端传递的信息，
	 *  并且调用DBStore将接收的数据持久化。
	 * @throws Exception
	 */
	void receiver() throws Exception;
	/**
	 * 该方法用于使Server安全的停止运行。
	 * @throws Exception
	 */
	void shutDown() throws Exception;
}
