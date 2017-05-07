package com.easytoolsoft.template.security.shiro;

import com.easytoolsoft.template.common.auth.PasswordService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * 用户口令服务类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Service("ShiroPasswordService")
public class PasswordServiceImpl implements PasswordService {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    @Override
    public String genreateSalt() {
        return randomNumberGenerator.nextBytes().toHex();
    }

    @Override
    public String encryptPassword(String password, String credentialsSalt) {
        return new SimpleHash(
            algorithmName,
            password,
            ByteSource.Util.bytes(credentialsSalt),
            hashIterations).toHex();
    }
}
