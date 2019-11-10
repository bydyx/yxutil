package com.bydyx.yxutil.file;

import com.bydyx.yxutil.string.StringUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ImgUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11�� 10, 2019</pre>
 */
public class ImgUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createBufferedImage(String randomText)
     */
    @Test
    public void testCreateBufferedImage() throws Exception {
        String verifyCode = StringUtil.createVerifyCode();
        BufferedImage verifyImg = ImgUtil.createBufferedImage(verifyCode);
        ImageIO.write(verifyImg, "png", new File("C:\\usr\\test\\" + verifyCode + ".png"));
    }

} 
