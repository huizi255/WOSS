package com.briup.woss.server;

import java.util.Collection;
import java.util.Date;

import com.briup.woss.bean.WossModuleInit;
import com.briup.woss.bean.BIDR;
import com.briup.woss.bean.ConfigurationAWare;
/**
 * DBStore提供了入库模块的规范。
 * 该接口的实现类将BIDR集合持久化。 
 * @author wang
 * @version new {@link Date}
 * @see com.briup.woss.WossModuleInit
 */
public interface DBStore extends WossModuleInit,ConfigurationAWare{
	/**
	 * 将BIDR集合进行持久化 。
	 * @param collection 需要储存的BIDR集合 
	 * @throws Exception
	 */
	void saveToDB(Collection<BIDR> collection) throws Exception;
}
