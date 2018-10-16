package cn.stylefeng.roses.biz.log.modular.mapper;

import cn.stylefeng.roses.biz.log.api.entity.TraceLog;
import cn.stylefeng.roses.biz.log.modular.model.TraceLogCondition;
import cn.stylefeng.roses.biz.log.modular.model.TraceLogParams;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-30
 */
public interface TraceLogMapper extends BaseMapper<TraceLog> {

    /**
     * 获取traceLog的总长度
     *
     * @author fengshuonan
     * @Date 2018/8/1 下午1:36
     */
    Long getTraceLogCount();

    /**
     * 获取调用链日志列表
     *
     * @author fengshuonan
     * @Date 2018/7/31 下午5:51
     */
    List<TraceLog> getTraceLogList(TraceLogParams traceLogParams);

    /**
     * 获取调用链日志列表(条件查询)
     *
     * @author fengshuonan
     * @Date 2018/7/31 下午5:51
     */
    List<TraceLog> getTraceLogListByCondition(TraceLogCondition traceLogCondition);

    /**
     * 获取调用链日志列表(条件查询和分页)
     *
     * @author fengshuonan
     * @Date 2018/7/31 下午5:51
     */
    List<TraceLog> getTraceLogListPageByCondition(Page<TraceLog> page, TraceLogCondition traceLogCondition);
}
