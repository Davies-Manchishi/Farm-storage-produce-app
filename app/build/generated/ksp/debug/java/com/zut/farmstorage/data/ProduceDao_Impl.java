package com.zut.farmstorage.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProduceDao_Impl implements ProduceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Produce> __insertionAdapterOfProduce;

  private final EntityDeletionOrUpdateAdapter<Produce> __deletionAdapterOfProduce;

  private final EntityDeletionOrUpdateAdapter<Produce> __updateAdapterOfProduce;

  public ProduceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduce = new EntityInsertionAdapter<Produce>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `produce` (`produceId`,`cropName`,`quantity`,`remainingQuantity`,`unit`,`harvestDate`,`storageLocation`,`notes`,`lastInspectionDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Produce entity) {
        statement.bindLong(1, entity.getProduceId());
        statement.bindString(2, entity.getCropName());
        statement.bindDouble(3, entity.getQuantity());
        statement.bindDouble(4, entity.getRemainingQuantity());
        statement.bindString(5, entity.getUnit());
        statement.bindLong(6, entity.getHarvestDate());
        statement.bindString(7, entity.getStorageLocation());
        statement.bindString(8, entity.getNotes());
        if (entity.getLastInspectionDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getLastInspectionDate());
        }
      }
    };
    this.__deletionAdapterOfProduce = new EntityDeletionOrUpdateAdapter<Produce>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `produce` WHERE `produceId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Produce entity) {
        statement.bindLong(1, entity.getProduceId());
      }
    };
    this.__updateAdapterOfProduce = new EntityDeletionOrUpdateAdapter<Produce>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `produce` SET `produceId` = ?,`cropName` = ?,`quantity` = ?,`remainingQuantity` = ?,`unit` = ?,`harvestDate` = ?,`storageLocation` = ?,`notes` = ?,`lastInspectionDate` = ? WHERE `produceId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Produce entity) {
        statement.bindLong(1, entity.getProduceId());
        statement.bindString(2, entity.getCropName());
        statement.bindDouble(3, entity.getQuantity());
        statement.bindDouble(4, entity.getRemainingQuantity());
        statement.bindString(5, entity.getUnit());
        statement.bindLong(6, entity.getHarvestDate());
        statement.bindString(7, entity.getStorageLocation());
        statement.bindString(8, entity.getNotes());
        if (entity.getLastInspectionDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getLastInspectionDate());
        }
        statement.bindLong(10, entity.getProduceId());
      }
    };
  }

  @Override
  public Object insert(final Produce produce, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProduce.insertAndReturnId(produce);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Produce produce, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProduce.handle(produce);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Produce produce, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProduce.handle(produce);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Produce>> getAll() {
    final String _sql = "SELECT * FROM produce ORDER BY harvestDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"produce"}, new Callable<List<Produce>>() {
      @Override
      @NonNull
      public List<Produce> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProduceId = CursorUtil.getColumnIndexOrThrow(_cursor, "produceId");
          final int _cursorIndexOfCropName = CursorUtil.getColumnIndexOrThrow(_cursor, "cropName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfRemainingQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingQuantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfStorageLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "storageLocation");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastInspectionDate");
          final List<Produce> _result = new ArrayList<Produce>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Produce _item;
            final long _tmpProduceId;
            _tmpProduceId = _cursor.getLong(_cursorIndexOfProduceId);
            final String _tmpCropName;
            _tmpCropName = _cursor.getString(_cursorIndexOfCropName);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpRemainingQuantity;
            _tmpRemainingQuantity = _cursor.getDouble(_cursorIndexOfRemainingQuantity);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final long _tmpHarvestDate;
            _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            final String _tmpStorageLocation;
            _tmpStorageLocation = _cursor.getString(_cursorIndexOfStorageLocation);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final Long _tmpLastInspectionDate;
            if (_cursor.isNull(_cursorIndexOfLastInspectionDate)) {
              _tmpLastInspectionDate = null;
            } else {
              _tmpLastInspectionDate = _cursor.getLong(_cursorIndexOfLastInspectionDate);
            }
            _item = new Produce(_tmpProduceId,_tmpCropName,_tmpQuantity,_tmpRemainingQuantity,_tmpUnit,_tmpHarvestDate,_tmpStorageLocation,_tmpNotes,_tmpLastInspectionDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Produce> getById(final long id) {
    final String _sql = "SELECT * FROM produce WHERE produceId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"produce"}, new Callable<Produce>() {
      @Override
      @Nullable
      public Produce call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProduceId = CursorUtil.getColumnIndexOrThrow(_cursor, "produceId");
          final int _cursorIndexOfCropName = CursorUtil.getColumnIndexOrThrow(_cursor, "cropName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfRemainingQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingQuantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfStorageLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "storageLocation");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastInspectionDate");
          final Produce _result;
          if (_cursor.moveToFirst()) {
            final long _tmpProduceId;
            _tmpProduceId = _cursor.getLong(_cursorIndexOfProduceId);
            final String _tmpCropName;
            _tmpCropName = _cursor.getString(_cursorIndexOfCropName);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpRemainingQuantity;
            _tmpRemainingQuantity = _cursor.getDouble(_cursorIndexOfRemainingQuantity);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final long _tmpHarvestDate;
            _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            final String _tmpStorageLocation;
            _tmpStorageLocation = _cursor.getString(_cursorIndexOfStorageLocation);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final Long _tmpLastInspectionDate;
            if (_cursor.isNull(_cursorIndexOfLastInspectionDate)) {
              _tmpLastInspectionDate = null;
            } else {
              _tmpLastInspectionDate = _cursor.getLong(_cursorIndexOfLastInspectionDate);
            }
            _result = new Produce(_tmpProduceId,_tmpCropName,_tmpQuantity,_tmpRemainingQuantity,_tmpUnit,_tmpHarvestDate,_tmpStorageLocation,_tmpNotes,_tmpLastInspectionDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
