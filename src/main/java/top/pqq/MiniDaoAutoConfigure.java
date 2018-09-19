package top.pqq;

import org.jeecgframework.minidao.factory.MiniDaoBeanScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.pqq.properties.MiniDaoProperties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(MiniDaoBeanScannerConfigurer.class)
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(MiniDaoProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MiniDaoAutoConfigure {

    private Logger logger = LoggerFactory.getLogger(MiniDaoAutoConfigure.class);
    @Autowired
    private MiniDaoProperties properties;



    @Bean
    @ConditionalOnMissingBean
    public MiniDaoBeanScannerConfigurer miniDaoHandler() {
        MiniDaoBeanScannerConfigurer miniDaoHandler = new MiniDaoBeanScannerConfigurer();
        miniDaoHandler.setAnnotation(properties.getAnnotation());//使用的注解,默认是Minidao,推荐 Repository
        miniDaoHandler.setBasePackage(properties.getBasePackage());//dao地址,配置符合spring方式
        miniDaoHandler.setDbType(properties.getDbType());//数据库类型  mysql/postgresql/oracle/sqlserver
        miniDaoHandler.setShowSql(properties.isShowSql());//输出sql
        miniDaoHandler.setFormatSql(properties.isFormatSql());//格式化sql
        miniDaoHandler.setKeyType(properties.getKeyType());//是使用什么字母做关键字Map的关键字 默认值origin 即和sql保持一致,lower小写(推荐),upper 大写
        logger.info("注册‘miniDaoHandler’ 成功 ");
        return miniDaoHandler;
    }





}
