package jp.co.se.android.recipe.chapter15;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

public class MyContentProvider extends ContentProvider {
    // 컨텐츠 프로바이더에 접속할 때 작성되는 Uri의 Authority
    public static final String AUTHORITY = "jp.co.se.android.recipe.chapter15";
    // Authority를 비롯한 Uri의 문자열

    public static final String CONTENT_AUTHORITY = "content://" + AUTHORITY
            + "/";

    // 코멘트 테이블에 접근하는 Uri
    public static final Uri COMMENTS_CONTENT_URI = Uri.parse(CONTENT_AUTHORITY
            + MySQLiteOpenHelper.TABLE_COMMENTS);

    // 컨텐츠의 Uri의 판별 코드
    public static final int CODE_COMMENT = 0;
    // ID 지정으로 코멘트의 Uri의 판별 코드
    public static final int CODE_COMMENT_ID = 1;

    // 컨텐츠 프로바이더 내에서 공통으로 사용하는  SQLiteOpenHelper 인스턴스
    private MySQLiteOpenHelper mDatabaseHelper = null;
    // Uri의 판별에서 사용
    private UriMatcher mUriMatcher = null;

    @Override
    public boolean onCreate() {
    	// Uri판별에 UriMatcher를 생성
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 컨텐츠의 Uri판별을 위한 패턴을 등록
        mUriMatcher.addURI(AUTHORITY, MySQLiteOpenHelper.TABLE_COMMENTS,
                CODE_COMMENT);
        // ID지정으로 코멘트의 Uri 판별을 위한 패턴을 등록
        mUriMatcher.addURI(AUTHORITY, MySQLiteOpenHelper.TABLE_COMMENTS + "/#",
                CODE_COMMENT_ID);

        // SQLiteOpenHelper 인스턴스를 구한다
        mDatabaseHelper = new MySQLiteOpenHelper(getContext());

        return true;
    }

    @Override
    public String getType(Uri uri) {
        // 이 샘플에서는 여러 개의 Uri에 대응하지 않기 때문에 구현하지 않는다 
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        // 검색 query를 작성하기 위한 SQLiteQueryBuilder를 생성
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // UriMatcher를 사용하여 Uri에서 검색하는 데이터 종류 별을 판정
        int match = mUriMatcher.match(uri);
        switch (match) {
        case CODE_COMMENT:
            // 코멘트에 대한 query 설정
            queryBuilder.setTables(MySQLiteOpenHelper.TABLE_COMMENTS);
            break;
        case CODE_COMMENT_ID:
            // ID 특정 코멘트에 대한 query 설정
            queryBuilder.setTables(MySQLiteOpenHelper.TABLE_COMMENTS);
            queryBuilder.appendWhere(BaseColumns._ID + "="
                    + uri.getLastPathSegment());
            break;
        default:
            // 예상되지 않은 데이터 종류별이므로 예외를 발생
            throw new IllegalArgumentException("Unknown URI");
        }

        // query를 실행하여 검색 결과를 반환
        Cursor cursor = queryBuilder.query(
                mDatabaseHelper.getReadableDatabase(), projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // UriMatcher를 사용하고 Uri로부터 삭제하는 데이트 종류별을 판정
        int match = mUriMatcher.match(uri);
        switch (match) {
        case CODE_COMMENT: {
            // 코멘트에 대한 delete를 실행하여 삭제 수를 반환
            return delete(MySQLiteOpenHelper.TABLE_COMMENTS, selection,
                    selectionArgs);
        }
        case CODE_COMMENT_ID: {
            // Uri로부터 ID를 구함
            long id = ContentUris.parseId(uri);
            // ID 특정 코멘트에 대한 delete를 실행하여 삭제 수를 반환
            return delete(MySQLiteOpenHelper.TABLE_COMMENTS, BaseColumns._ID
                    + " = ?", new String[] { Long.toString(id) });
        }
        }
        // 처리가 아니므로 0을 반환
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // UriMatcher을 사용하여 Uri로부터 삽입하는 데이터 종류 별을 판정
        int match = mUriMatcher.match(uri);
        switch (match) {
        case CODE_COMMENT: {
            // 코멘트에 대한 insert를 실행하여, 결과로 얻은 ID로부터 Uri을 생성하여 반환
            return ContentUris.withAppendedId(COMMENTS_CONTENT_URI,
                    insert(MySQLiteOpenHelper.TABLE_COMMENTS, values));
        }
        }
        // 처리가 아니므로 null을 반환
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // UriMatcher을 사용하여 Uri로부터 삽입하는 데이터 종류별을 판정
        int match = mUriMatcher.match(uri);
        switch (match) {
        case CODE_COMMENT: {
            // 코멘트에 대한 update를 실행하는 갱신 수를 반환
            return update(MySQLiteOpenHelper.TABLE_COMMENTS, values, selection,
                    selectionArgs);
        }
        case CODE_COMMENT_ID: {
            long id = ContentUris.parseId(uri);
            // ID 지정의 코멘트에 대한 update을 실행하는 갱신 수를 반환
            return update(MySQLiteOpenHelper.TABLE_COMMENTS, values,
                    BaseColumns._ID + " = ?",
                    new String[] { Long.toString(id) });
        }
        }
        //처리가 아니므로 0을 반환
        return 0;
    }

    public int delete(String table, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        return db.delete(table, selection, selectionArgs);
    }

    private long insert(String table, ContentValues values) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        return db.insert(table, null, values);
    }

    private int update(String table, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        return db.update(table, values, selection, selectionArgs);
    }

}
