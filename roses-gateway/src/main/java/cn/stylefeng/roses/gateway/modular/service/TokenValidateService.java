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
package cn.stylefeng.roses.gateway.modular.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.gateway.core.exception.AuthExceptionEnum;
import cn.stylefeng.roses.kernel.jwt.properties.JwtProperties;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static cn.stylefeng.roses.gateway.core.constants.AuthConstants.AUTH_HEADER;

/**
 * Token校验的服务
 *
 * @author fengshuonan
 * @date 2018-08-13 21:50
 */
public abstract class TokenValidateService {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @author stylefeng
     * @Date 2018/8/13 22:11
     */
    public boolean doValidate(HttpServletRequest request) {

        //先获取token
        String tokenFromRequest = this.getTokenFromRequest(request);

        //校验token是否正确
        return this.validateToken(tokenFromRequest, request);
    }

    /**
     * 获取请求中的token
     *
     * @author stylefeng
     * @Date 2018/8/13 22:05
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        //获取token
        String authToken = request.getHeader(AUTH_HEADER);
        if (ToolUtil.isEmpty(authToken)) {

            //如果header中没有token，则检查请求参数中是否带token
            authToken = request.getParameter("token");
            if (ToolUtil.isEmpty(authToken)) {
                throw new ServiceException(AuthExceptionEnum.TOKEN_EMPTY);
            }
        } else {
            authToken = authToken.substring("Bearer ".length());
        }

        return authToken;
    }

    /**
     * 校验token
     *
     * @author stylefeng
     * @Date 2018/8/13 21:50
     */
    protected abstract boolean validateToken(String token, HttpServletRequest request);

}
