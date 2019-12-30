package com.springboot.fun;
 
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.chatroom.CreateChatRoomResult;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.chatroom.ChatRoomPayload;
import org.junit.Test;
 
/**
 * im操作
 *
 * @author zhuzhe
 * @date 2018/12/13 16:34
 * @email zhuzhe_mail@163.com
 */
/*@Slf4j*/
public class ImUtil {

    /*String appkey = "14647b0879a3373061bd7896";
    String masterSecret = "3311e0a2a56ee87a6cc3c016";*/
    //叶
    String appkey = "fca3861fe0ea801a880bd088";
    String masterSecret = "16f064c74dcd42bb91cb9acf";
    JMessageClient client = new JMessageClient(appkey, masterSecret);

    /**
     * deleteChatRoom
     */
    @Test
    public void deleteChatRoom() throws APIConnectionException, APIRequestException {
        ResponseWrapper responseWrapper = client.deleteChatRoom(15317450);
        System.out.println(11);
    }

    /**
     * createChatRoom
     */
    @Test

    public void createChatRoom() throws APIConnectionException, APIRequestException {
        CreateChatRoomResult chatRoom = client.createChatRoom(ChatRoomPayload.newBuilder()
                .setOwnerUsername("zhuzhe")
                .setName("聊天室2")
                .build());
        System.out.println(chatRoom);
    }

    /**
     * registerUsers
     */
    @Test
    public void registerUsers() throws APIConnectionException, APIRequestException {

        RegisterInfo registerInfo = RegisterInfo.newBuilder()
                .setUsername("hello1")
                .setPassword("hello1")
                .build();
        RegisterInfo[] registerInfos = new RegisterInfo[1];
        registerInfos[0] = registerInfo;
        String string = client.registerUsers(registerInfos);

        System.out.println(string);
    }

    /**
     * registerAdmins
     */
    @Test
    public void registerAdmins() throws APIConnectionException, APIRequestException {

        String string = client.registerAdmins("admin", "admin");
        System.out.println(string);
    }
}