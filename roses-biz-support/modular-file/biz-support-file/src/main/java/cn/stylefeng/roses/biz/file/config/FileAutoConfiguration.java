package cn.stylefeng.roses.biz.file.config;

import cn.stylefeng.roses.biz.file.config.properties.OssProperteis;
import cn.stylefeng.roses.biz.file.core.db.FileInitializer;
import cn.stylefeng.roses.biz.file.core.storage.FileOperator;
import cn.stylefeng.roses.biz.file.core.storage.oss.OssFileOperator;
import cn.stylefeng.roses.biz.file.modular.controller.FileController;
import cn.stylefeng.roses.biz.file.modular.controller.FileManagerController;
import cn.stylefeng.roses.biz.file.modular.provider.FileinfoServiceProvider;
import cn.stylefeng.roses.biz.file.modular.service.FileinfoService;
import cn.stylefeng.roses.kernel.model.constants.ConfigPrefixConstants;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 字典自动配置
 *
 * @author fengshuonan
 * @date 2018-07-27-下午1:57
 */
@Configuration
@ComponentScan("cn.stylefeng.roses.biz.file")
public class FileAutoConfiguration {

    /**
     * 阿里云配置
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午2:21
     */
    @Bean
    @ConfigurationProperties(prefix = ConfigPrefixConstants.ALIYUN_OSS)
    public OssProperteis ossProperteis() {
        return new OssProperteis();
    }

    /**
     * oss客户端sdk
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午6:10
     */
    @Bean
    public OSSClient ossClient(OssProperteis ossProperteis) {
        DefaultCredentialProvider defaultCredentialProvider =
                new DefaultCredentialProvider(ossProperteis.getAccessKeyId(), ossProperteis.getAccessKeySecret());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        return new OSSClient(ossProperteis.getEndpoint(), defaultCredentialProvider, clientConfiguration);
    }

    /**
     * 文件操作工具
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午2:21
     */
    @Bean
    public FileOperator fileOperator(OSSClient ossClient, OssProperteis ossProperteis) {
        return new OssFileOperator(ossClient, ossProperteis);
    }

    /**
     * 数据库初始化
     */
    @Bean
    public FileInitializer fileInitializer() {
        return new FileInitializer();
    }

    /**
     * 控制器
     */
    @Bean
    public FileController fileController() {
        return new FileController();
    }

    @Bean
    public FileManagerController fileManagerController() {
        return new FileManagerController();
    }

    /**
     * 服务
     */
    @Bean
    public FileinfoService fileinfoService() {
        return new FileinfoService();
    }

    /**
     * 服务提供者
     */
    @Bean
    public FileinfoServiceProvider fileinfoServiceProvider() {
        return new FileinfoServiceProvider();
    }
}
