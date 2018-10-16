/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.roses.gateway.modular.service.impl;

import cn.stylefeng.roses.gateway.core.exception.AuthExceptionEnum;
import cn.stylefeng.roses.gateway.modular.consumer.AuthServiceConsumer;
import cn.stylefeng.roses.gateway.modular.consumer.ResourceServiceConsumer;
import cn.stylefeng.roses.gateway.modular.service.TokenValidateService;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.resource.ResourceDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 集成scanner的鉴权服务
 *
 * @author fengshuonan
 * @date 2018-08-13 21:52
 */
//@Service
public class WithScannerTokenValidateServiceImpl extends TokenValidateService {

    @Autowired
    private ResourceServiceConsumer resourceServiceConsumer;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public boolean validateToken(String token, HttpServletRequest request) {

        String requestURI = null;
        if (request != null) {
            //获取context-path加servlet-path
            requestURI = request.getRequestURI();

            //如果是zuul开头的url，则去掉zuul再去资源校验
            if (requestURI.startsWith("/zuul")) {
                requestURI = requestURI.substring(5);
            }
        }

        //获取当前接口是否需要鉴权
        ResourceDefinition currentResource = resourceServiceConsumer.getResourceByUrl(requestURI);
        if (currentResource == null) {
            return true;
        }

        //判断是否需要登录
        if (currentResource.getRequiredLogin()) {

            //验证token是否正确
            boolean flag = authServiceConsumer.checkToken(token);
            if (flag) {
                return true;
            } else {
                throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
            }
        } else {
            return true;
        }
    }
}
