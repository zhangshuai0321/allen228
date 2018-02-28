package demo.sharesdk.cn.mobimdemoums;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mob.MobSDK;
import com.mob.imsdk.MobIM;
import com.mob.imsdk.MobIMCallback;
import com.mob.imsdk.MobIMMessageReceiver;
import com.mob.imsdk.model.IMConversation;
import com.mob.imsdk.model.IMGroup;
import com.mob.imsdk.model.IMMessage;
import com.mob.ums.UMSSDK;
import com.mob.ums.OperationCallback;
import com.mob.ums.User;
import com.mob.ums.gui.UMSGUI;
import com.mob.wrappers.UMSSDKWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button4 = (Button) findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMSGUI.showLogin(new OperationCallback<User>() {
					public void onSuccess(User user) {
//                        Toast.makeText(MainActivity.this,"登录成功"+ user,1).show();
                        System.out.println("---------------------");
                        System.out.println("---------------------"+user.toString());
                        String name = user.nickname.get();
                        String id = user.id.get();
                        System.out.println("---------------------id"+UMSSDK.getLoginUserId());

                        MobSDK.setUser(id, name,"https://lanre.smartemple.cn/image/share.png", null);
                    }

					public void onFailed(Throwable t) {

					}
				});
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("---------------------id"+UMSSDK.getLoginUserId());
                IMMessage imMessage = MobIM.getChatManager().createTextMessage("00f3f1d0a7429000","abclarry", IMConversation.TYPE_USER);//17601354876
//                IMMessage imMessage = MobIM.getChatManager().createTextMessage("00c8930edaca6000","abclarry2", IMConversation.TYPE_USER); //18521782262的id
                MobIM.getChatManager().sendMessage(imMessage, new MobIMCallback<Void>() {
                    public void onSuccess(Void result)  {
                        // TODO 处理消息发送成功的结果
                        System.out.println("---------------------发送消息成功");
                        Toast.makeText(MainActivity.this,"发送消息成功",0).show();
                    }
                    public void onError(int code, String message)  {
                        // TODO 根据错误码（code）处理错误返回
                        System.out.println("---------------------失败"+message+"--CODE"+code);
                    }
                });



            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobIM.getChatManager().getMessageList("00f3f1d0a7429000", IMConversation.TYPE_USER, 5, 1, new MobIMCallback<List<IMMessage>>() {
                    @Override
                    public void onSuccess(List<IMMessage> imMessages) {
                        System.out.println("----------1" + imMessages);
                        for (int i = 0;i<imMessages.size();i++){
                            IMMessage imMessage =  imMessages.get(i);
                            System.out.println("------------>>>"+imMessage.getBody());
                        }
                        Toast.makeText(MainActivity.this,"打印列表成功请看日志",0).show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        System.out.println("----------" + s);

                    }
                });


            }
        });
    }


}
