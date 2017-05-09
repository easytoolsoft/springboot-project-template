package com.easytoolsoft.template.common.security;

/**
 * @author zhiwei.deng
 * @date 2017-05-07
 **/
public interface PasswordService {
    void setAlgorithmName(String algorithmName);

    void setHashIterations(int hashIterations);

    String genreateSalt();

    String encryptPassword(String password, String credentialsSalt);
}
