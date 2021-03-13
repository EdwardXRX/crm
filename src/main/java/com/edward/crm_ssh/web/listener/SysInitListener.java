package com.edward.crm_ssh.web.listener;

import com.edward.crm_ssh.settings.domain.DicValue;
import com.edward.crm_ssh.settings.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * @ProjectName: WEB_CMR
 * @Package: com.edwardxrx.crm.web.listener
 * @ClassName: SysInitListener
 * @Author: EdwardX
 * @Description:
 * @Date: 2020/12/17 16:23
 * @Version: 1.0
 */
public class SysInitListener implements ServletContextListener {

    @Autowired
    @Qualifier("DicServiceImpl")
    private DicService ds;

    /*
        该方法是用来监听上下文域对象的方法，当服务器启动，上下文域对象创建
        对象创建完毕之后
        就会立即执行本方法

        event:该参数能够取得监听的对象
                监听的是什么对象
                就可以通过该参数取得什么对象

                例如我们监听的是上下文域对象

                通过该参数就可以取得此对象
    */

    @Override
    public void contextInitialized(ServletContextEvent event) {


DicService ds =
WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean(DicService.class);

        System.out.println("服务器缓存数据字典开始");

        if(ds == null){
            System.out.println("ds为空");
        }else
        {
            System.out.println("ds不为空");
        }

        ServletContext application = event.getServletContext();

        //取数据字典


        //application.setAttribute("key",shujuzidian );

        //打包成一个map
        //业务层应该是这样保存数据的
        /*
            map.put("applicationList",dicList1);
            map.put("clueList",dicList2);
            map.put("stageList",dicList3);
        */
        Map<String, List<DicValue>> map = ds.getAll();

        Set<String> set = map.keySet();

        for (String key : set) {
            application.setAttribute(key, map.get(key));
        }

        System.out.println("服务器缓存数据字典结束");


        Map<String, String> pMap = new HashMap<>();
        //解析properties文件
        ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> e = rb.getKeys();

        while (e.hasMoreElements()) {
            //阶段
            String key = e.nextElement();
            //可能性
            String value = rb.getString(key);

            pMap.put(key, value);
        }

        //将pMap保存到服务器缓存中
        application.setAttribute("pMap", pMap);


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {


    }
}
