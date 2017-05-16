package com.easytoolsoft.template.security.shiro;

import com.easytoolsoft.commons.support.security.PasswordService;
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
public class ShiroPasswordService implements PasswordService {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    @Override
    public String genreateSalt() {
        return this.randomNumberGenerator.nextBytes().toHex();
    }

    @Override
    public String encode(CharSequence rawPassword, String credentialsSalt) {
        return new SimpleHash(
            algorithmName,
            rawPassword,
            ByteSource.Util.bytes(credentialsSalt),
            hashIterations).toHex();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.encode(rawPassword, "shiro");
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.matches(rawPassword, encodedPassword, "shiro");
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword, String credentialsSalt) {
        String encode = this.encode(rawPassword, credentialsSalt);
        return encode.equals(encodedPassword);
    }

    public static void main(String... args) {
        String credentialsSalt = "test283facc4dc9896ddb303a736be9530ea";
        ShiroPasswordService passwordService = new ShiroPasswordService();
        System.out.println(passwordService.encode("123456", credentialsSalt));
    }
}
