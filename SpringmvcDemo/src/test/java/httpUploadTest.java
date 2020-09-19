
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.util.HashMap;

/**
 * @Description:
 * @Author: zl
 * @date: 2020/7/28
 */
public class httpUploadTest {

    @Test
    public void httpclient(){

//        HashMap<String, Object> paramMap = new HashMap<>();
////文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
//        paramMap.put("file", FileUtil.file("D:\\test.txt"));
//
//        String result= HttpUtil.post("http://localhost:9900/databinderFile", paramMap);


//        HttpClient context = new DefaultHttpClient();
//        HttpPost post = new HttpPost("url");
//        post.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//        post.setHeader("Accept-Encoding","gzip, deflate");  //像header这些自己去设置吧
//
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addBinaryBody("name=\"File\"; filename=\"testImg.png\"", new File("C:\\Users\\Administrator\\Desktop\\testImg.png"));//添加文件
//        builder.addTextBody("Language", "9");  //添加文本类型参数
//        post.setEntity(builder.build());
//        HttpResponse response = context.execute(post);
//        byte[] res =null;
//        //获取参数
//        /**请求发送成功，并得到响应**/
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            res = EntityUtils.toByteArray(response.getEntity());
//        }
//        System.out.println(Jsoup.parse(new String( uncompress(res),"utf-8")).select("div[class=div_res]"));
    }
}
