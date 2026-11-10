package com.android.adobot.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;

public class CallLogDao_Impl implements CallLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCallLog;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public CallLogDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCallLog = new EntityInsertionAdapter<CallLog>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CallLog`(`id`,`callId`,`type`,`phone`,`name`,`date`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CallLog value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getCallId());
        stmt.bindLong(3, value.getType());
        if (value.getPhone() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPhone());
        }
        if (value.getName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getName());
        }
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        stmt.bindLong(7, value.getDuration());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM calllog WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(CallLog callLog) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCallLog.insert(callLog);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public CallLog first() {
    final String _sql = "SELECT * FROM calllog LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCallId = _cursor.getColumnIndexOrThrow("callId");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfDuration = _cursor.getColumnIndexOrThrow("duration");
      final CallLog _result;
      if(_cursor.moveToFirst()) {
        _result = new CallLog();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpCallId;
        _tmpCallId = _cursor.getInt(_cursorIndexOfCallId);
        _result.setCallId(_tmpCallId);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _result.setPhone(_tmpPhone);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        _result.setDuration(_tmpDuration);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
