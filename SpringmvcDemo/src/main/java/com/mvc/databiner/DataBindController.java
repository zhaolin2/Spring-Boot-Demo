package com.mvc.databiner;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author zl
 * 数据绑定 可以自动绑定的有以下类型
 * HttpServletRequest HttpServletResponse HttpSession  Model/ModelMap
 * 基本类型 8种
 * 8种基本类型的包装类型   String
 * pojo(不过只能绑定pojo中的基本类型和String)
 * 数组
 * List  因为是接口  所以只能使用 {@link RequestBody} 来进行接收 或者放在vo中进行接收
 * Map   Content-Type为applicationon/json  也要加{@link RequestBody} 才能进行绑定
 * POST 4种形式
 * Content-type：
 *
 * 1、application/x-www-form-urlencoded:@RequestBody不是必须加的
 *  一般直接写参数就可以接收 String test
 *
 * 2、mutipart/form-data:@RequestBody不能处理这种格式
 *
 * 3、其他格式，比如application/json,application/xml等，必须使用@RequestBody来处理
 *
 * 4.tetx/plain @RequestBody String
 */
@RestController
public class DataBindController {

    /**
     * content-type=
     * @param test
     * @return
     */
    @GetMapping(value = "/databinder",produces = {"application/xml"})
    public String dataBind(@RequestParam String test,@RequestHeader("Content-Type") String contentType){

        System.out.println("content-type: "+contentType);
        System.out.println(test);
        return "绑定成功";
    }

    @PostMapping("/databinder")
    public String dataBindPost(String test,Date date ,@RequestHeader("Content-Type") String contentType){

        // content-type: application/x-www-form-urlencoded
        System.out.println("content-type: "+contentType);
        System.out.println(test+"--"+date.toString());
        return "绑定成功";
    }

    @PostMapping("/databinderData")
    public String dataBindPostData(@RequestBody Data data,@RequestHeader("Content-Type") String contentType){

        // content-type: application/json
        System.out.println("content-type: "+ contentType);
        System.out.println(data.getTest()+"--"+data.getDate());
        return "绑定成功";
    }

    @PostMapping("/databinderMap")
    public String dataBindPost(@RequestBody Map<String, String> map,@RequestHeader("Content-Type") String contentType){

        //content-type: application/json
        System.out.println("content-type: "+contentType);

        System.out.println(map.toString());
        return "绑定成功";
    }

    @PostMapping("/databinderString")
    public String dataBindString(@RequestBody String string,@RequestHeader("Content-Type") String contentType){

        //content-type:content-type: text/plain
        System.out.println("content-type: "+contentType);

        /**
         * {
         *   "map1\":\"123\",  \"map2\":\"456"
         * }
         */
        System.out.println(string);
        return "绑定成功";
    }

    @PostMapping("/databinderFile")
    public String dataBindFile(@RequestParam("file") MultipartFile file, @RequestHeader("Content-Type") String contentType,HttpServletRequest request){

        //content-type:content-type: content-type: multipart/form-data; boundary=--------------------Hutool_ou7lvyr1mabibx3r
        System.out.println("content-type: "+contentType);

        /**
         * {
         *   "map1\":\"123\",  \"map2\":\"456"
         * }
         */
        System.out.println(file.getSize());
        System.out.println(request.toString());
        return "绑定成功";
    }
}
