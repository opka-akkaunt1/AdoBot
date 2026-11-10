package com.android.adobot.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;

public class SmsDao_Impl implements SmsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSms;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfSms;

  public SmsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSms = new EntityInsertionAdapter<Sms>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Sms`(`id`,`_id`,`thread_id`,`phone`,`name`,`body`,`date`,`type`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Sms value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value._id);
        if (value.thread_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.thread_id);
        }
        if (value.phone == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.phone);
        }
        if (value.name == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.name);
        }
        if (value.body == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.body);
        }
        if (value.date == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.date);
        }
        stmt.bindLong(8, value.type);
      }
    };
    this.__deletionAdapterOfSms = new EntityDeletionOrUpdateAdapter<Sms>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Sms` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Sms value) {
        stmt.bindLong(1, value.id);
      }
    };
  }

  @Override
  public void insert(Sms sms) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSms.insert(sms);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Sms sms) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfSms.handle(sms);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Sms findByIdAndThreadId(int id, String thread_id) {
    final String _sql = "SELECT * FROM Sms WHERE id  = ? AND thread_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    _argIndex = 2;
    if (thread_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, thread_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfBody = _cursor.getColumnIndexOrThrow("body");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final Sms _result;
      if(_cursor.moveToFirst()) {
        _result = new Sms();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        _result._id = _cursor.getInt(_cursorIndexOfId_1);
        _result.thread_id = _cursor.getString(_cursorIndexOfThreadId);
        _result.phone = _cursor.getString(_cursorIndexOfPhone);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.body = _cursor.getString(_cursorIndexOfBody);
        _result.date = _cursor.getString(_cursorIndexOfDate);
        _result.type = _cursor.getInt(_cursorIndexOfType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Sms findByAttributes(String phone, String body, String date, int type) {
    final String _sql = "SELECT * FROM Sms WHERE phone  = ? AND body = ? AND date = ? AND type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (phone == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phone);
    }
    _argIndex = 2;
    if (body == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, body);
    }
    _argIndex = 3;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 4;
    _statement.bindLong(_argIndex, type);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfBody = _cursor.getColumnIndexOrThrow("body");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final Sms _result;
      if(_cursor.moveToFirst()) {
        _result = new Sms();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        _result._id = _cursor.getInt(_cursorIndexOfId_1);
        _result.thread_id = _cursor.getString(_cursorIndexOfThreadId);
        _result.phone = _cursor.getString(_cursorIndexOfPhone);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.body = _cursor.getString(_cursorIndexOfBody);
        _result.date = _cursor.getString(_cursorIndexOfDate);
        _result.type = _cursor.getInt(_cursorIndexOfType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Sms first() {
    final String _sql = "SELECT * FROM Sms LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfBody = _cursor.getColumnIndexOrThrow("body");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final Sms _result;
      if(_cursor.moveToFirst()) {
        _result = new Sms();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        _result._id = _cursor.getInt(_cursorIndexOfId_1);
        _result.thread_id = _cursor.getString(_cursorIndexOfThreadId);
        _result.phone = _cursor.getString(_cursorIndexOfPhone);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.body = _cursor.getString(_cursorIndexOfBody);
        _result.date = _cursor.getString(_cursorIndexOfDate);
        _result.type = _cursor.getInt(_cursorIndexOfType);
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
