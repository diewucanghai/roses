package cn.stylefeng.roses.biz.dict.config;

import cn.stylefeng.roses.biz.dict.core.db.DictInitializer;
import cn.stylefeng.roses.biz.dict.core.db.DictTypeInitializer;
import cn.stylefeng.roses.biz.dict.modular.controller.DictController;
import cn.stylefeng.roses.biz.dict.modular.controller.DictTypeController;
import cn.stylefeng.roses.biz.dict.modular.provider.DictServiceProvider;
import cn.stylefeng.roses.biz.dict.modular.provider.DictTypeServiceProvider;
import cn.stylefeng.roses.biz.dict.modular.service.DictService;
import cn.stylefeng.roses.biz.dict.modular.service.DictTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 字典的自动配置
 *
 * @author fengshuonan
 * @date 2018-07-24-下午5:12
 */
@Configuration
public class DictAutoConfiguration {

    /**
     * 数据库初始化
     */
    @Bean
    public DictInitializer dictInitializer() {
        return new DictInitializer();
    }

    @Bean
    public DictTypeInitializer dictTypeInitializer() {
        return new DictTypeInitializer();
    }

    /**
     * 控制器
     */
    @Bean
    public DictController dictController() {
        return new DictController();
    }

    @Bean
    public DictTypeController dictTypeController() {
        return new DictTypeController();
    }

    /**
     * 服务层
     */
    @Bean
    public DictService dictService() {
        return new DictService();
    }

    @Bean
    public DictTypeService dictTypeService() {
        return new DictTypeService();
    }

    /**
     * 服务提供者
     */
    @Bean
    public DictServiceProvider dictServiceProvider() {
        return new DictServiceProvider();
    }

    @Bean
    public DictTypeServiceProvider dictTypeServiceProvider() {
        return new DictTypeServiceProvider();
    }
}
