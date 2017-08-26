package jp.co.se.android.recipe.chapter17;

import java.util.ArrayList;
import java.util.List;

import jp.co.se.android.recipe.chapter17.util.IabHelper;
import jp.co.se.android.recipe.chapter17.util.IabResult;
import jp.co.se.android.recipe.chapter17.util.Inventory;
import jp.co.se.android.recipe.chapter17.util.Purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Ch1702 extends Activity {
    /** 로그 출력용 TAG */
    private static final String TAG = "Ch1702";

    /** 공개키 */
    private static final String PUBLIC_KEY = "앱 라이센스키를 입력";

    /** 앱 내 아이템의 ID */
 // 영속성 아이템
    static final String ITEM_GOLD = "gold";
 // 정기 구입형 아이템
    static final String ITEM_SILVER = "silver";
 // 소비형 아이템
    static final String ITEM_BRONZE = "bronze";

    /* 앱 내 아이템을 구입하기 위한 요구 코드 */
    static final int RC_REQUEST = 10001;

    /** 아이템 소비 플래그 */
    boolean mHasGold = false;
    boolean mHasSilver = false;
    boolean mHasBronze = false;

    /** 필드 */
    IabHelper mIabHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1702_main);
        // 앱 내 아이템 헬퍼를 작성
        mIabHelper = new IabHelper(this, PUBLIC_KEY);

        // 로그를 유효로 한다
        mIabHelper.enableDebugLogging(true);

        // 아이템 리스트를 생성
        List<String> itemList = new ArrayList<String>();
        itemList.add("골드 아이템　500원(영구 이용 가능)");
        itemList.add("실버 아이템　300원(정기 구입)");
        itemList.add("브론즈 아이템　100원(전부 사용)");

        // 스피너 초기화
        final Spinner spIab = (Spinner) findViewById(R.id.spinnerIab);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIab.setAdapter(adapter);

        // 앱 내 헬퍼의 셋업을 시작
        mIabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess()) {
                    // 앱 내 아이템의 소지 체크
                    mIabHelper.queryInventoryAsync(mGotInventoryListener);
                } else {
                    Log.d(TAG, "셋업이 실패했습니다: " + result);
                }
            }
        });

        // 구입 버튼을 탭
        findViewById(R.id.buyItem).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                exeBuyItem(spIab.getSelectedItemPosition());
            }
        });
    }

    /**
     * 아이템 구입 처리
     * 
     * @param itemType
     */
    private void exeBuyItem(int itemType) {
        String payload = "";
        switch (itemType) {
        case 0:
            // 골드 아이템
            mIabHelper.launchPurchaseFlow(this, ITEM_GOLD, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
            break;
        case 1:
            // 실버 아이템
            mIabHelper.launchPurchaseFlow(this, ITEM_SILVER,
                    IabHelper.ITEM_TYPE_SUBS, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
            break;
        case 2:
            // 브론즈 아이템
            mIabHelper.launchPurchaseFlow(this, ITEM_BRONZE, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
            break;
        default:
            break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 앱 내 아이템 헬퍼를 파기
        if (mIabHelper != null) {
            mIabHelper.dispose();
            mIabHelper = null;
        }
    }

    /**
     * 앱 내 아이템의 소지 검사 종료를 알리는 리스너.
     */
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                Inventory inventory) {
            if (result.isSuccess()) {
                // 골드 아이템을 갖고 있는지 확인 (영속성)
                Purchase itemGold = inventory.getPurchase(ITEM_GOLD);
                if (itemGold != null) {
                    mHasGold = verifyDeveloperPayload(itemGold);
                    Log.d(TAG, "골드 아이템을 소지:" + mHasGold);
                }
                // 실버 아이템을 갖고 있는지 확인(정기 구독형)
                Purchase itemSilver = inventory.getPurchase(ITEM_SILVER);
                if (itemSilver != null) {
                    mHasSilver = (itemSilver != null && verifyDeveloperPayload(itemSilver));
                    Log.d(TAG, "실버 아이템을 소지:" + mHasSilver);
                }
                // 브론즈 아이템을 갖고 있는지 확인(소모형)
                Purchase itemBronze = inventory.getPurchase(ITEM_BRONZE);
                if (itemBronze != null) {
                    mHasBronze = (itemBronze != null && verifyDeveloperPayload(itemBronze));
                    Log.d(TAG, "브론즈 아이템을 소지:" + mHasSilver);
                    // 브론즈 아이템을 소지하고 있으면 사용한다
                    if (mHasBronze) {
                        mIabHelper.consumeAsync(
                                inventory.getPurchase(ITEM_BRONZE),
                                mConsumeFinishedListener);
                        return;
                    }
                } else {
                    Toast.makeText(Ch1702.this, "브론즈 아이템을 소지하지 않았습니다",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d(TAG, "인벤트리 검색이 실패했습니다: " + result);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // onActivityResult의 결과를 앱 내 아이템 헬퍼로 전달한다
        if (mIabHelper != null) {
            if (!mIabHelper.handleActivityResult(requestCode, resultCode, data)) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        // TODO　스스로 베리파이 처리를 구현한다

        return true;
    }

    /**
     * 앱 내 아이템을 구입 완료를 알리는 리스너.
     */
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isSuccess()) {
                // 구입 완료한 앱 내 아이템의 소지 체크
                if (!verifyDeveloperPayload(purchase)) {
                    Log.d(TAG, "앱 내 아이템이 올바르게 구매할 수 없었습니다");
                    return;
                }
                if (purchase.getSku().equals(ITEM_GOLD)) {
                    // 골드 아이템을 소지했습니다
                    mHasGold = true;
                } else if (purchase.getSku().equals(ITEM_SILVER)) {
                    // 실버 아이템을 소지했습니다
                    mHasSilver = true;
                } else if (purchase.getSku().equals(ITEM_BRONZE)) {
                    // 브론즈 아이템을 소지했습니다
                    mHasBronze = true;
                }
            }
        }
    };

    /**
     * 소비형 아이템을 사용할 때 호출되는 리스너
     */
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                Toast.makeText(Ch1702.this, "브론즈 아이템을 소비했습니다",
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "아이템 소비에 실패했습니다: " + result);
            }
        }
    };
}
