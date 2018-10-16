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
import cn.stylefeng.roses.gateway.modular.service.TokenValidateService;
import cn.stylefeng.roses.kernel.jwt.utils.JwtTokenUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 纯token验证鉴权服
 *
 * @author fengshuonan
 * @date 2018-08-13 21:52
 */
@Service
public class PureTokenValidateServiceImpl extends TokenValidateService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean validateToken(String token, HttpServletRequest request) {

        try {
            boolean flag = jwtTokenUtil.isTokenExpired(token);
            if (flag) {
                throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
        }
    }
}
