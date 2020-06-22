package util;

import javafx.scene.input.DataFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @description:
 * @ClassName:RandomNum
 * @Description:
 * @Date: Create in 15:15 2019/5/16
 */
public class RandomNum {
    public static String getRandomByNowtime(){
        return DateFormatUtils.format(new Date(),"yyyy-MM-dd-HH:mm:ss");
    }
}