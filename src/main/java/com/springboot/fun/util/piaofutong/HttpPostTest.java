package com.springboot.fun.util.piaofutong;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import nu.xom.Element;
import org.apache.catalina.startup.Tool;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/*
  *
  * 票付通
  * */
public class HttpPostTest {
    public String testPost(String urlStr,String xmlInfo) {
        StringBuffer buf = null;
        try {  
            URL url = new URL(urlStr);  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);
            con.setRequestProperty("Pragma", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());      
            /*String xmlInfo = getXmlInfo();*/
            out.write(new String(xmlInfo));  
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
            String line = "";  
             buf = new StringBuffer();  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
                buf.append(new String(line.getBytes(),"UTF-8"));  
            }  
            System.out.println("[--------------------"+buf.toString());
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return buf.toString();
    }






/*这个先不用，直接写到接口里面*/
    private String getXmlInfo() {
        // 通过wsdl文件可以查看接口xml格式数据，构造调用接口xml数据  
        String xml = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                    + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                    +        " <tns:Get_ScenicSpot_List>"
                    +            "<ac>100019</ac>"
                    +            " <pw>8fd46f8a187f6665ae716087a0a377d1</pw>"
                    +            "<n>0</n>"
                    +            " <m>2</m>"
                    +            "</tns:Get_ScenicSpot_List>"
                    +            " </soap:Body>"
                    +            "</soap:Envelope>";


        return xml;
    }





      /**
       * 发起webservice请求
       * @param url
       * @param soap
       * @param SOAPAction
       * @return
       */
      public static String doPostSoap(String url, String soap, String SOAPAction) {
          //请求体
          String retStr = "";
          // 创建HttpClientBuilder
          HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
          // HttpClient
          CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
          HttpPost httpPost = new HttpPost(url);
          //  设置请求和传输超时时间


          try {
              httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
              httpPost.setHeader("SOAPAction", SOAPAction);
              StringEntity data = new StringEntity(soap,
                      Charset.forName("UTF-8"));
              httpPost.setEntity(data);
              CloseableHttpResponse response = closeableHttpClient
                      .execute(httpPost);
              HttpEntity httpEntity = response.getEntity();
              if (httpEntity != null) {
                  // 打印响应内容
                  retStr = EntityUtils.toString(httpEntity, "UTF-8");
                  System.err.println("response:" + retStr);
              }
              // 释放资源
              closeableHttpClient.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          return retStr;
      }






   public static void main(String[] args) throws IOException, JSONException {
      String url = "http://open.12301dev.com/openService/pftMX.php";
       String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
               + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
               +        " <tns:Get_ScenicSpot_List>"
               +            "<ac>100019</ac>"
               +            " <pw>8fd46f8a187f6665ae716087a0a377d1</pw>"
               +            "<n>0</n>"
               +            " <m>2</m>"
               +            "</tns:Get_ScenicSpot_List>"
               +            " </soap:Body>"
               +            "</soap:Envelope>";
      String xml=  new HttpPostTest().testPost(url,xmlInf);
       /*String xml="<class id="
               + "'1'"
               + "><student>  <UUaddtime>2019-05-30 14:33:41</UUaddtime>    <UUarea>江苏省|苏州市</UUarea>   " +
               " <UUid>60288</UUid>    <UUimgpath>images/defaultThum.jpg</UUimgpath>    <UUtitle>南山景区（勿动）</UUtitle> " +
               "   <UUp_type>A</UUp_type></student></class>";*/
         /*   String xml="<class id="
               + "'1'"
               + "><student>  <Rec ID=\"UU1\">    <UUaddtime>2019-05-30 14:33:41</UUaddtime>    <UUarea>江苏省|苏州市</UUarea>    <UUid>60288</UUid>    <UUimgpath>images/defaultThum.jpg</UUimgpath>    <UUtitle>南山景区（勿动）</UUtitle>    <UUp_type>A</UUp_type>  </Rec> <Rec ID=\"UU1\">    <UUaddtime>2019-05-30 14:33:41</UUaddtime>    <UUarea>江苏省|苏州市</UUarea>    <UUid>60288</UUid>    <UUimgpath>images/defaultThum.jpg</UUimgpath>    <UUtitle>南山景区（勿动）</UUtitle>    <UUp_type>A</UUp_type>  </Rec> </student></class>";
    */
            /*   String xml="<class id="
               + "'1'"
               + "><student>   &lt;UUaddtime&gt;2019-05-30 14:33:41&lt;/UUaddtime&gt;" +
               "    &lt;UUarea&gt;江苏省|苏州市&lt;/UUarea&gt;" +
               "    &lt;UUid&gt;60288&lt;/UUid&gt;" +
               "    &lt;UUimgpath&gt;images/defaultThum.jpg&lt;/UUimgpath&gt;" +
               "    &lt;UUtitle&gt;南山景区（勿动）&lt;/UUtitle&gt;" +
               "    &lt;UUp_type&gt;A&lt;/UUp_type&gt;</student><student>   &lt;UUaddtime&gt;2019-05-30 14:33:41&lt;/UUaddtime&gt; " +
               "                 &lt;UUarea&gt;江苏省|苏州市&lt;/UUarea&gt; " +
               "                  &lt;UUid&gt;60288&lt;/UUid&gt; " +
               "                  &lt;UUimgpath&gt;images/defaultThum.jpg&lt;/UUimgpath&gt; " +
               "                  &lt;UUtitle&gt;南山景区（勿动）&lt;/UUtitle&gt; " +
               "                 &lt;UUp_type&gt;A&lt;/UUp_type&gt;</student></class>";*/

        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
       String sessionkey = referJson.get("SOAP-ENV:Body").toString();
       System.out.println(sessionkey);
       int strStartIndex = sessionkey.indexOf("<Data>");
       int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
       System.out.println(strStartIndex+"pp"+endStartIndex);
    String re=sessionkey.substring(strStartIndex,endStartIndex);

      re=re.replaceAll("\\\\","");
       StringEscapeUtils.unescapeJava(re);
       System.out.println(re);

       String xml2="<class id="
               + "'1'"
               + ">"+re+"</class>";
       JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
       JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());

        System.out.println(referJson2);

    }
}