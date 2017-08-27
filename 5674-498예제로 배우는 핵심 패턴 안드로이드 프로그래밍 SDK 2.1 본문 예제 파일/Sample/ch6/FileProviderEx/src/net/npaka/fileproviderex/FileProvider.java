package net.npaka.fileproviderex;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

//파일을 제공하는 컨텐츠 프로바이더
public class FileProvider extends ContentProvider {// （3）
	//컨텐츠 프로바이더 초기화 （4）
	@Override
		public boolean onCreate() {
		return true;
	}
	
	//파일 제공 （5）
	@Override
		public ParcelFileDescriptor openFile(Uri uri,String mode)
		throws FileNotFoundException {
		File file = new File(URI.create(
			"file:///data/data/net.npaka.fileproviderex/files/"+
			uri.getLastPathSegment()));
		ParcelFileDescriptor parcel=
			ParcelFileDescriptor.open(
			file,ParcelFileDescriptor.MODE_READ_ONLY);
		return parcel;
	}
	
	//데이터베이스 쿼리 명령(미사용)
	@Override
		public Cursor query(Uri uri,String[] projection,String selection,
		String[] selectionArgs,String sortOrder) {
		return null;
	}
	
	//데이터베이스 삽입 명령(미사용)
	@Override
		public Uri insert(Uri uri,ContentValues values) {
		return null;
	}
	
	//데이터베이스 갱신 명령(미사용)
	@Override
		public int update(Uri uri,ContentValues values,
		String selection,String[] selectionArgs) {
		return 0;
	}
	
	//데이터베이스 삭제 명령(미사용)
	@Override
		public int delete(Uri uri,String selection,
		String[] selectionArgs) {
		return 0;
	}
	
	//종류 구하기(미사용)
	@Override
		public String getType(Uri uri) {
		return null;
	}
}