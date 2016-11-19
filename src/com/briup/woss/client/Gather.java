package com.briup.woss.client;

import java.util.Collection;
import java.util.Date;

import com.briup.woss.bean.WossModuleInit;
import com.briup.woss.bean.BIDR;
import com.briup.woss.bean.ConfigurationAWare;
/**
 * Gather接口规定了采集模块所应有的方法。 
 * 当Gather执行gather()方法时，开始对AAA服务器的计费信息进行采集。
 * 将采集的数据封装成为一个BIDR的集合返回。
 * @author wang
 * @version new {@link Date}
 * @see com.briup.woss.WossModuleInit
 */
public interface Gather extends WossModuleInit,ConfigurationAWare{
	/**
	 * 采集AAA服务器的计费信息，将数据封装为BIDR集合返回。
	 * @return 采集封装BIDR数据的集合
	 * @throws Exception
	 */
	Collection<BIDR> gather() throws Exception;
}
