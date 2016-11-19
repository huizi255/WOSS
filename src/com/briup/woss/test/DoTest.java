package com.briup.woss.test;

import java.util.Collection;
import java.util.List;

import com.briup.woss.bean.BIDR;
import com.briup.woss.client.ClientImpl;
import com.briup.woss.client.GatherImpl;
import com.briup.woss.common.ConfigurationImpl;
import com.briup.woss.server.ServerImpl;

public class DoTest {
	public static void main(String[] args) {
		DoTest d = new DoTest();
//		d.test3();
//		d.test1();
		d.test2();
	}
	public void test1(){
		GatherImpl gi = new GatherImpl();
		try {
			gi.gather();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test2(){
		try {
			new ConfigurationImpl();
//			System.out.println(new GatherImpl().gather());
			Collection<BIDR> collection = new GatherImpl().gather();
			/*for(BIDR b:new GatherImpl().gather()){
			System.out.println(b);   		//返回list集合
			}*/
			new ClientImpl().send(collection);
//			new ClientImpl().send(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test3(){
		ServerImpl si = new ServerImpl();
		try {
			si.receiver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
