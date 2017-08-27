package net.npaka.rpg;
import android.content.res.*;
import android.content.*;
import android.graphics.*;
import android.view.*;
import java.util.Random;

// RPG 뷰
public class RPGView extends SurfaceView 
    implements SurfaceHolder.Callback,Runnable {
    // 시스템 정수
    private final static int // 장면(1)
        S_MAP    =0, // 맵
        S_APPEAR =1, // 출현
        S_COMMAND=2, // 명령
        S_ATTACK =3, // 공격
        S_DEFENCE=4, // 방어
        S_ESCAPE =5; // 도망친다
    private final static int // 키
        KEY_NONE  =-1,
        KEY_LEFT  =0,
        KEY_RIGHT =1,
        KEY_UP    =2,
        KEY_DOWN  =3,
        KEY_1     =4,
        KEY_2     =5,
        KEY_SELECT=6;
    private final static int[][] MAP={ // 맵(2)
        {3,3,3,3,3,3,3,3,3,3},
        {3,1,0,0,0,0,3,0,0,3},
        {3,0,0,0,0,0,3,3,0,3},
        {3,0,3,3,3,3,3,3,0,3},
        {3,0,0,3,0,0,0,3,0,3},
        {3,3,0,3,0,3,3,3,0,3},
        {3,0,0,3,0,0,0,0,0,3},
        {3,0,3,3,0,3,0,3,3,3},
        {3,0,0,0,0,3,0,0,2,3},
        {3,3,3,3,3,3,3,3,3,3}};

    // 용사 정수(3)
    private final static int[] 
        YU_MAXHP  ={0,30,50,70}, // 최대 체력
        YU_ATTACK ={0,5,10,30},  // 공격력
        YU_DEFENCE={0,0,5,10},  // 수비력
        YU_EXP    ={0,0,3,6};     // 필요 경험치

    // 적군 정수(4)
    private final static String[] 
        EN_NAME={"스라스라","그리드라"};// 적군 이름
    private final static int[] 
        EN_MAXHP  ={10,50}, // 최대 체력
        EN_ATTACK ={10,26}, // 공격력
        EN_DEFENCE={0,16},  // 수비력
        EN_EXP    ={1,99};   // 취득 경험치

    // 시스템
    private int      init=S_MAP; // 초기화(1)
    private int      scene;      // 장면(1)
    private int      key;        // 키
    private int      w;          // 폭
    private int      h;          // 높이
    private Graphics g;         // 그래픽
    private Bitmap[] bmp=new Bitmap[7]; // 비트맵
    private SurfaceHolder holder;  // 표면 홀더
    private Thread        thread;  //스레드

    // 용사 파라미터(3)
    private int yuX  =1; // X좌표
    private int yuY  =2; // Y좌표
    private int yuLV =1; // 레벨
    private int yuHP =30;// 체력
    private int yuEXP=0; // 경험치

    // 적군 파라미터(4)
    private int enType; // 종류
    private int enHP;   // 체력
    
    //constructor　 　
    public RPGView(Context context) {
        super(context);

        // 그림 파일 읽기
        Resources r=getResources();
        bmp[0]=BitmapFactory.decodeResource(r,R.drawable.rpg0);
        bmp[1]=BitmapFactory.decodeResource(r,R.drawable.rpg1);
        bmp[2]=BitmapFactory.decodeResource(r,R.drawable.rpg2);
        bmp[3]=BitmapFactory.decodeResource(r,R.drawable.rpg3);
        bmp[4]=BitmapFactory.decodeResource(r,R.drawable.rpg4);
        bmp[5]=BitmapFactory.decodeResource(r,R.drawable.rpg5);
        bmp[6]=BitmapFactory.decodeResource(r,R.drawable.rpg6);
        
        // 그래픽스의 생성
        holder=getHolder();
        holder.addCallback(this);
        g=new Graphics(holder);
    }

    // 표면 생성
    public void surfaceCreated(SurfaceHolder holder) {
        thread=new Thread(this);
        thread.start();
    }

    // 표면 파괴
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread=null;
    }

    // 표면 변경
    public void surfaceChanged(SurfaceHolder holder,
        int format,int w,int h) {
    }   
      
    // 스레드 처리
    public void run() {
        String str;
        w=getWidth();
        h=getHeight();
        while(thread!=null) {
            // 장면의 초기화
            if (init>=0) {
                scene=init;
                init=-1;
                key =KEY_NONE;
            }
            
            // 맵
            if (scene==S_MAP) {
                // 이동
                boolean flag=false;
                if (key==KEY_UP) {
                    if (MAP[yuY-1][yuX]<=2) {yuY--;flag=true;}
                } else if (key==KEY_DOWN) {
                    if (MAP[yuY+1][yuX]<=2) {yuY++;flag=true;}
                } else if (key==KEY_LEFT) {
                    if (MAP[yuY][yuX-1]<=2) {yuX--;flag=true;}
                } else if (key==KEY_RIGHT) {
                    if (MAP[yuY][yuX+1]<=2) {yuX++;flag=true;}
                }

                // 적 출현의 계산(5)
                if (flag) {
                    if (MAP[yuY][yuX]==0 && (rand.nextInt()>>>1)%10==0) {
                        enType=0;init=S_APPEAR;}
                    if (MAP[yuY][yuX]==1) yuHP=YU_MAXHP[yuLV];
                    if (MAP[yuY][yuX]==2) {enType=1;init=S_APPEAR;}
                }

                // 그리기
                g.lock();
                for (int j=-3;j<=3;j++) {
                    for (int i=-5;i<=5;i++) {
                        int idx=3;
                        if (0<=yuX+i && yuX+i<MAP[0].length &&
                            0<=yuY+j && yuY+j<MAP.length) {
                            idx=MAP[yuY+j][yuX+i];
                        }
                        g.drawBitmap(bmp[idx],w/2-40+80*i,h/2-40+80*j);
                    }
                }
                g.drawBitmap(bmp[4],w/2-40,h/2-40);
                drawStatus();
                g.unlock();
            }
            // 출현의 처리
            else if (scene==S_APPEAR) {
                // 초기화
                enHP=EN_MAXHP[enType];

                // 플래시
                sleep(300);
                for (int i=0;i<6;i++) {
                    g.lock();
                    if (i%2==0) {
                        g.setColor(Color.rgb(0,0,0));
                    } else {
                        g.setColor(Color.rgb(255,255,255));
                    }
                    g.fillRect(0,0,w,h);
                    g.unlock();
                    sleep(100);
                }

                // 메시지
                drawBattle(EN_NAME[enType]+"가 나타났다");
                waitSelect();
                init=S_COMMAND;
            }
            // 명령
            else if (scene==S_COMMAND) {
                drawBattle("　　1.공격　　2.도망친다");
                key=KEY_NONE;
                while (init==-1) {
                    if (key==KEY_1) init=S_ATTACK;
                    if (key==KEY_2) init=S_ESCAPE;
                    sleep(100);
                }
            }
            // 공격의 처리
            else if (scene==S_ATTACK) {
                // 메시지
                drawBattle("용사의 공격");
                waitSelect();

                // 플래시
                for (int i=0;i<10;i++) {
                    drawBattle("용사의 공격",i%2==0);
                    sleep(100);
                }

                // 공격의 계산(6)
                int damage=YU_ATTACK[yuLV]-EN_DEFENCE[enType]+
                    (rand.nextInt()>>>1)%10;
                if (damage<= 1) damage= 1;
                if (damage>=99) damage=99;
                
                // 메시지
                drawBattle(damage+"데미지 주었다!");
                waitSelect();
                
                // 체력의 계산
                enHP-=damage;
                if (enHP<=0) enHP=0;

                // 승리
                init=S_DEFENCE;
                if (enHP==0) {
                    // 메시지
                    drawBattle(EN_NAME[enType]+"를 넘어뜨렸다");
                    waitSelect();

                    // 경험치 계산
                    yuEXP+=EN_EXP[enType];
                    if (yuLV<3 && YU_EXP[yuLV+1]<=yuEXP) {
                        yuLV++;
                        drawBattle("레벨업했다");
                        waitSelect();
                    }

                    // 엔딩
                    if (enType==1) {
                        g.lock();
                        g.setColor(Color.rgb(0,0,0));
                        g.fillRect(0,0,w,h);
                        g.setColor(Color.rgb(255,255,255));
                        g.setFontSize(32);
                        str="Fin.";
                        g.drawString(str,(w-g.stringWidth(str))/2,200);
                        g.unlock();
                        waitSelect();
                        RPG.exit();
                    }
                    init=S_MAP;
                }
            }
            // 적 공격
            else if (scene==S_DEFENCE) {
                // 메시지
                drawBattle(EN_NAME[enType]+"의 공격");
                waitSelect();

                // 플래시
                for (int i=0;i<10;i++) {
                    if (i%2==0) {
                        g.lock();
                        g.setColor(Color.rgb(255,255,255));
                        g.fillRect(0,0,w,h);
                        g.unlock();
                    } else {
                        drawBattle(EN_NAME[enType]+"의 공격");
                    }
                    sleep(100);
                }

                // 방어의 계산(7)
                int damage=EN_ATTACK[enType]-YU_DEFENCE[yuLV]+
                    (rand.nextInt()>>>1)%10;
                if (damage<= 1) damage= 1;
                if (damage>=99) damage=99;
                
                // 메시지
                drawBattle(damage+"데미지 받았다!");
                waitSelect();
                
                // 체력의 계산
                yuHP-=damage;
                if (yuHP<=0) yuHP=0;

                // 패배
                init=S_COMMAND;
                if (yuHP==0) {
                    drawBattle("용사는 힘이 다했다");
                    waitSelect();
                    RPG.exit();
                }
            }
            // 도망친다
            else if (scene==S_ESCAPE) {
                // 메시지
                drawBattle("용사는 도망갔다");
                waitSelect();

                // 도망치는 것 계산(8)
                init=S_MAP;
                if (enType==1 || (rand.nextInt()>>>1)%100<=10) {
                    drawBattle(EN_NAME[enType]+"는 돌았다");
                    waitSelect();
                    init=S_DEFENCE;
                }
            }
            // 슬립
            key=KEY_NONE;
            sleep(200);
        }
    }

    // 전투 화면 그리기
    private void drawBattle(String message) {
        drawBattle(message,enHP>=0);
    }
    
    // 전투 화면 그리기
    private void drawBattle(String message,boolean visible) {
        int color=(yuHP!=0)?Color.rgb(0,0,0) :Color.rgb(2555,0,0);
        g.lock();
        g.setColor(color);
        g.fillRect(0,0,w,h);
        drawStatus();
        if (visible) {
            g.drawBitmap(bmp[5+enType],
                (w-bmp[5+enType].getWidth())/2,
                h-100-bmp[5+enType].getHeight());
        }
        g.setColor(Color.rgb(255,255,255));
        g.fillRect((w-504)/2,h-122,504,104);
        g.setColor(color);
        g.fillRect((w-500)/2,h-120,500,100);
        g.setColor(Color.rgb(255,255,255));
        g.setFontSize(32);
        g.drawString(message,(w-500)/2+50,h-90);
        g.unlock();
    }
    
    // 상태 그리기
    private void drawStatus() {
        int color=(yuHP!=0)?Color.rgb(0,0,0) :Color.rgb(255,0,0);
        g.setColor(Color.rgb(255,255,255));
        g.fillRect((w-504)/2,8,504,54);
        g.setColor(color);
        g.fillRect((w-500)/2,10,500,50);
        g.setColor(Color.rgb(255,255,255));
        g.setFontSize(32);
        g.drawString("용사　LV"+yuLV+"　HP"+yuHP+
            "/"+YU_MAXHP[yuLV],(w-500)/2+50,48);
    }
    
    // 터치 이벤트의 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX=(int) event.getX();
        int touchY=(int) event.getY();
        int touchAction=event.getAction();
        if (touchAction==MotionEvent.ACTION_DOWN) {
            if (scene==S_MAP) {
                if (Math.abs(touchX-w/2)>Math.abs(touchY-h/2)) {
                    key=(touchX-w/2<0)?KEY_LEFT:KEY_RIGHT;
                } else {
                    key=(touchY-h/2<0)?KEY_UP:KEY_DOWN;
                }
            } else if (scene==S_APPEAR || scene==S_ATTACK || 
                scene==S_DEFENCE || scene==S_ESCAPE) {
                key=KEY_SELECT;
            } else if (scene==S_COMMAND) {
                if (264<touchX && touchX<264+120 &&
                    300<touchY && touchY<300+80) {
                    key=KEY_1;
                } else if (414<touchX && touchX<414+130 &&
                    300<touchY && touchY<300+80) {
                    key=KEY_2;
                }
            }
        }
        return true;
    } 
    
    // 결정 키 대기
    private void waitSelect() {
        key=KEY_NONE;
        while (key!=KEY_SELECT) sleep(100);
    }
    
    // 슬립
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
    
    // 난수 구하기
    private static Random rand=new Random();
    public static int rand(int num) {
        return (rand.nextInt()>>>1) %num;
    }    
}
