package com.android.adobot.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile SmsDao _smsDao;

  private volatile CallLogDao _callLogDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Sms` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_id` INTEGER NOT NULL, `thread_id` TEXT, `phone` TEXT, `name` TEXT, `body` TEXT, `date` TEXT, `type` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CallLog` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `callId` INTEGER NOT NULL, `type` INTEGER NOT NULL, `phone` TEXT, `name` TEXT, `date` TEXT, `duration` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX `index_CallLog_date` ON `CallLog` (`date`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f6f19fd0834f35f9205cd75d1e466e6f\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Sms`");
        _db.execSQL("DROP TABLE IF EXISTS `CallLog`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSms = new HashMap<String, TableInfo.Column>(8);
        _columnsSms.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsSms.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0));
        _columnsSms.put("thread_id", new TableInfo.Column("thread_id", "TEXT", false, 0));
        _columnsSms.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsSms.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsSms.put("body", new TableInfo.Column("body", "TEXT", false, 0));
        _columnsSms.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsSms.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSms = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSms = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSms = new TableInfo("Sms", _columnsSms, _foreignKeysSms, _indicesSms);
        final TableInfo _existingSms = TableInfo.read(_db, "Sms");
        if (! _infoSms.equals(_existingSms)) {
          throw new IllegalStateException("Migration didn't properly handle Sms(com.android.adobot.database.Sms).\n"
                  + " Expected:\n" + _infoSms + "\n"
                  + " Found:\n" + _existingSms);
        }
        final HashMap<String, TableInfo.Column> _columnsCallLog = new HashMap<String, TableInfo.Column>(7);
        _columnsCallLog.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCallLog.put("callId", new TableInfo.Column("callId", "INTEGER", true, 0));
        _columnsCallLog.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        _columnsCallLog.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsCallLog.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCallLog.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsCallLog.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCallLog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCallLog = new HashSet<TableInfo.Index>(1);
        _indicesCallLog.add(new TableInfo.Index("index_CallLog_date", true, Arrays.asList("date")));
        final TableInfo _infoCallLog = new TableInfo("CallLog", _columnsCallLog, _foreignKeysCallLog, _indicesCallLog);
        final TableInfo _existingCallLog = TableInfo.read(_db, "CallLog");
        if (! _infoCallLog.equals(_existingCallLog)) {
          throw new IllegalStateException("Migration didn't properly handle CallLog(com.android.adobot.database.CallLog).\n"
                  + " Expected:\n" + _infoCallLog + "\n"
                  + " Found:\n" + _existingCallLog);
        }
      }
    }, "f6f19fd0834f35f9205cd75d1e466e6f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Sms","CallLog");
  }

  @Override
  public SmsDao smsDao() {
    if (_smsDao != null) {
      return _smsDao;
    } else {
      synchronized(this) {
        if(_smsDao == null) {
          _smsDao = new SmsDao_Impl(this);
        }
        return _smsDao;
      }
    }
  }

  @Override
  public CallLogDao callLogDao() {
    if (_callLogDao != null) {
      return _callLogDao;
    } else {
      synchronized(this) {
        if(_callLogDao == null) {
          _callLogDao = new CallLogDao_Impl(this);
        }
        return _callLogDao;
      }
    }
  }
}
