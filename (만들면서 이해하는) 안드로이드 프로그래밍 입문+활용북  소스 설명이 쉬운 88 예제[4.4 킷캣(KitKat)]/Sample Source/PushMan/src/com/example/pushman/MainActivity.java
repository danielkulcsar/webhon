package com.example.pushman;

import java.io.*;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    CanvasView mView;
    Point mPoTouch;
    Bitmap[] mImage;
    int[][] mScreenImageType;
    int mGameLevelNum;
    
    final int COUNT_SCREEN_IMAGE_ROW = 12;
    final int COUNT_SCREEN_IMAGE_COL = 10;

    final int LENGTH_SCREEN_START_X  = 0;
    final int LENGTH_SCREEN_START_Y  = 0;
    int LENGTH_IMAGE_WIDTH = 48;
    int LENGTH_IMAGE_HEIGHT = 48;
    final int MAX_GAME_LEVEL_NUM = 36;

    final int IMAGE_TYPE_BACK = 0;
    final int IMAGE_TYPE_BLOCK = 1;
    final int IMAGE_TYPE_STONE = 2;
    final int IMAGE_TYPE_HOUSE_EMPTY = 3;
    final int IMAGE_TYPE_HOUSE_FULL = 4;
    final int IMAGE_TYPE_PUSH_MAN = 5;
    final int IMAGE_TYPE_PUSH_MAN_IN_HOUSE = 6;

    final int KEY_UP = 0;
    final int KEY_DOWN = 1;
    final int KEY_LEFT = 2;
    final int KEY_RIGHT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initVariable();

        mView = new CanvasView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mView, 0);

    	LoadDataFile(mGameLevelNum);
    }

    public void initVariable() {
        int i = 0, j = 0;

        mPoTouch = new Point(-1, -1);
        mImage = new Bitmap[6]; 
    	
        for(i=0; i < 6; i++)
            mImage[i] = null;

        mImage[IMAGE_TYPE_BACK] = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.img_back);
        mImage[IMAGE_TYPE_BLOCK] = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.img_block);
        mImage[IMAGE_TYPE_STONE] = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.img_stone);
        mImage[IMAGE_TYPE_HOUSE_EMPTY] = 
        	    BitmapFactory.decodeResource(getResources(), R.drawable.img_house_empty);
        mImage[IMAGE_TYPE_HOUSE_FULL] = 
        	    BitmapFactory.decodeResource(getResources(), R.drawable.img_house_full);
        mImage[IMAGE_TYPE_PUSH_MAN] = 
        	    BitmapFactory.decodeResource(getResources(), R.drawable.img_push_man);

        mScreenImageType = new 
        	    int[COUNT_SCREEN_IMAGE_ROW][COUNT_SCREEN_IMAGE_COL];
        for(j=0; j < COUNT_SCREEN_IMAGE_ROW; j++)
            for(i=0; i < COUNT_SCREEN_IMAGE_COL; i++)
                mScreenImageType[j][i] = 0;

        mGameLevelNum = 1;
    }

    protected class CanvasView extends View {
        public CanvasView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            DrawScreenImage(canvas);
        }
		
        public Rect getScreenRect() {
            Rect rtScreen = new Rect();
            rtScreen.left = 0;
            rtScreen.top = 0;
            rtScreen.right = this.getWidth();
            rtScreen.bottom = this.getHeight();
			
            return rtScreen;
        }

        public void DrawScreenImage(Canvas canvas) {
            Rect rScreen = getScreenRect();
            LENGTH_IMAGE_WIDTH = (int)(rScreen.width() 
            	    /  COUNT_SCREEN_IMAGE_COL);
            LENGTH_IMAGE_HEIGHT = LENGTH_IMAGE_WIDTH;
            int scrStartX = rScreen.left + LENGTH_SCREEN_START_X;
            int scrStartY = rScreen.top + LENGTH_SCREEN_START_Y;
            int imageType = 0, posX = 0, posY = 0, width = 0, height = 0;

            for(int j=0; j < COUNT_SCREEN_IMAGE_ROW; j++) {
                for(int i=0; i < COUNT_SCREEN_IMAGE_COL; i++) {
                    imageType = mScreenImageType[j][i];
                    if( IMAGE_TYPE_PUSH_MAN_IN_HOUSE 
                    	    == imageType )
                        imageType = IMAGE_TYPE_PUSH_MAN;
                    posX = scrStartX + i * LENGTH_IMAGE_WIDTH;
                    posY = scrStartY + j * LENGTH_IMAGE_HEIGHT;
                    width = LENGTH_IMAGE_WIDTH;
                    height = LENGTH_IMAGE_HEIGHT;

                    canvas.drawBitmap(mImage[imageType], null, 
                            new Rect(posX, posY, posX+width, posY+height), null);
                }
            }
        }
    }

    public String ReadTextFile(String strFilePath) {
    	String text = null;
    	try {
           InputStream is = getAssets().open(strFilePath);

           int size = is.available();
           byte[] buffer = new byte[size];
           is.read(buffer);
           is.close();

           text = new String(buffer);
    	} catch (IOException e) {
           throw new RuntimeException(e);
    	}
    	
    	return text;
    }

    public void LoadDataFile(int levelNum) {
    	String bufFile = null;
    	String filePath = null;

    	if( levelNum < 1 )
    		return;
    	if( levelNum > MAX_GAME_LEVEL_NUM )
    		return;

    	filePath = String.format("data/stage_%d.txt", levelNum);
    	bufFile = ReadTextFile(filePath);

    	int pos = 0, row = 0, col = 0, length = 0, imageType = 0;
    	String bufLine, bufItem;

    	while( (pos = bufFile.indexOf("\n")) >= 0 ) {
    		bufLine = bufFile.substring(0, pos);
    		bufLine.trim();
    		bufFile = bufFile.substring(pos+1);

    		length = bufLine.length();
    		if( length <= 1 )
    			continue;
    		if( length > COUNT_SCREEN_IMAGE_COL )
    			length = COUNT_SCREEN_IMAGE_COL;

    		for(col = 0; col < length; col++) {
    			bufItem = bufLine.substring(col, col+1);
    			imageType = Integer.parseInt(bufItem);
    			mScreenImageType[row][col] = imageType;
    		}

    		row ++;
    		if( COUNT_SCREEN_IMAGE_ROW <= row )
    			break;
    	}

    	mGameLevelNum = levelNum;

    	String titleText;
    	titleText = String.format("PushMan Level-%d", mGameLevelNum);
    	this.setTitle(titleText);

    	mView.invalidate();
    }

    public void onClick( View v ) {
        boolean gameComplete = false;
    	
        switch( v.getId() ) {
        case R.id.buttonLevelPrev :
            LoadDataFile(mGameLevelNum - 1);
            break;
        case R.id.buttonLevelNext :
            LoadDataFile(mGameLevelNum + 1);
            break;
        case R.id.buttonMoveUp :
            MovePushMan(KEY_UP);
            gameComplete = IsGameComplete();
            break;
        case R.id.buttonMoveDown :
            MovePushMan(KEY_DOWN);
            gameComplete = IsGameComplete();
            break;
        case R.id.buttonMoveLeft :
            MovePushMan(KEY_LEFT);
            gameComplete = IsGameComplete();
            break;
        case R.id.buttonMoveRight :
            MovePushMan(KEY_RIGHT);
            gameComplete = IsGameComplete();
            break;
        }

        if( gameComplete )
            OnCompletedGame();
    }
    
    public Point GetPositionPushMan() {
    	int col=0, row=0;
    	Point poPushMan = new Point();

    	for(row=0; row < COUNT_SCREEN_IMAGE_ROW; row++) {
    		for(col=0; col < COUNT_SCREEN_IMAGE_COL; col++) {
    			if( mScreenImageType[row][col] == IMAGE_TYPE_PUSH_MAN
    					|| mScreenImageType[row][col] == 
    				IMAGE_TYPE_PUSH_MAN_IN_HOUSE ) {
    				poPushMan.x = col;
    				poPushMan.y = row;
    				return poPushMan;
    			}
    		}
    	}

    	return poPushMan;
    }

    public boolean InsertStoneToCell(Point poCell) {
    	switch( mScreenImageType[poCell.y][poCell.x] ) {
    	case IMAGE_TYPE_BACK :
    	    mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_STONE;	
    	    break;
    	case IMAGE_TYPE_HOUSE_EMPTY :
    	    mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_FULL;
    	    break;
    	default :
    	    return false;
    	}
    	return true;
    }

    public void RecoverToEmptyCell(Point poCell) {
    	switch( mScreenImageType[poCell.y][poCell.x] ) {
    	case IMAGE_TYPE_STONE :
    		mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_BACK;
    		break;
    	case IMAGE_TYPE_HOUSE_FULL :
    		mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_EMPTY;
    		break;
    	case IMAGE_TYPE_PUSH_MAN :
    		mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_BACK;
    		break;
    	case IMAGE_TYPE_PUSH_MAN_IN_HOUSE :
    		mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_EMPTY;
    		break;
    	default :
    		break;
    	}
    }

    public void MovePushMan(int keyType) {
        Point poPushMan, poNewPush, poNewBall;
        poPushMan = GetPositionPushMan();
        poNewPush = new Point(poPushMan);
        poNewBall = new Point(poPushMan);

        switch( keyType ) {
        case KEY_UP :
            poNewPush.y -= 1;
            poNewBall.y -= 2;
            break;
        case KEY_DOWN :
            poNewPush.y += 1;
            poNewBall.y += 2;
            break;
        case KEY_LEFT :
            poNewPush.x -= 1;
            poNewBall.x -= 2;
            break;
        case KEY_RIGHT :
            poNewPush.x += 1;
            poNewBall.x += 2;
            break;
        }

        switch( mScreenImageType[poNewPush.y][poNewPush.x] ) {
        case IMAGE_TYPE_BACK :
            mScreenImageType[poNewPush.y][poNewPush.x] 
                    = IMAGE_TYPE_PUSH_MAN;
            break;
        case IMAGE_TYPE_BLOCK :
            return;
        case IMAGE_TYPE_STONE :
            if( InsertStoneToCell( poNewBall ) ) {
                mScreenImageType[poNewPush.y][poNewPush.x]
                        = IMAGE_TYPE_PUSH_MAN;
            }
            else 
                return;
            break;
        case IMAGE_TYPE_HOUSE_EMPTY :
            mScreenImageType[poNewPush.y][poNewPush.x]
                    = IMAGE_TYPE_PUSH_MAN_IN_HOUSE;
            break;
        case IMAGE_TYPE_HOUSE_FULL :
            if( InsertStoneToCell( poNewBall ) ) {
                mScreenImageType[poNewPush.y][poNewPush.x]
                        = IMAGE_TYPE_PUSH_MAN_IN_HOUSE;
            }
            else 
                return;
            break;
        }

        RecoverToEmptyCell( poPushMan );
        mView.invalidate();
    }

    public boolean IsGameComplete() {
        int row=0, col=0;
        for(row=0 ; row < COUNT_SCREEN_IMAGE_ROW ; row++) {
            for(col=0 ; col < COUNT_SCREEN_IMAGE_COL ; col++) {
                if( mScreenImageType[row][col] == IMAGE_TYPE_STONE )
                    return false;
            }
        }

        return true;
    }

    public void OnCompletedGame() {
        String messageText = String.format("Conguraturation! You Passed Level-%d", 
                mGameLevelNum);
 
        new AlertDialog.Builder(this)
            .setMessage(messageText)
            .setTitle("Level Completed")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    LoadDataFile(mGameLevelNum + 1);
                }
        })
        .show();
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean gameComplete = false;

        super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mPoTouch.x = (int)event.getX();
            mPoTouch.y = (int)event.getY();
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE) {
            if( mPoTouch.x == -1 && mPoTouch.y == -1 )
                return true;

            int nMoveX = (int)event.getX() - mPoTouch.x;
            int nMoveY = (int)event.getY() - mPoTouch.y;

            if( Math.abs(nMoveX) >= LENGTH_IMAGE_WIDTH ) {
                if( nMoveX < 0 )
                   MovePushMan(KEY_LEFT);
                else
                    MovePushMan(KEY_RIGHT);
            }
            else if( Math.abs(nMoveY) >= LENGTH_IMAGE_HEIGHT ) {
                if( nMoveY < 0 )
                    MovePushMan(KEY_UP);
                else
                    MovePushMan(KEY_DOWN);
            }
            else
                return true;

            gameComplete = IsGameComplete();
            mPoTouch.x = (int)event.getX();
            mPoTouch.y = (int)event.getY();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) {
            mPoTouch.x = -1;
            mPoTouch.y = -1;
        }

        if( gameComplete ) {
            mPoTouch.x = -1;
            mPoTouch.y = -1;

            OnCompletedGame();
    	}
        return true;
    }


}
