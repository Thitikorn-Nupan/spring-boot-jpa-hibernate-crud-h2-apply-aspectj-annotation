package com.ttknpdev.springbootcrudh2aopbasic.log;

import com.ttknpdev.springbootcrudh2aopbasic.SpringRestClient;
import com.ttknpdev.springbootcrudh2aopbasic.aspect.RobotLoggingAspect;
import com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao;
import org.apache.log4j.Logger;
// use by extends
public class Logging {
    protected final Logger robotDao = Logger.getLogger(RobotDao.class);
    protected final Logger robotLoggingAspect = Logger.getLogger(RobotLoggingAspect.class);
    protected final Logger springRestClient = Logger.getLogger(SpringRestClient.class);
}
