package com.lidong.demo.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lidong.demo.R;

import java.text.DateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_add)
    EditText etAdd;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.lv_note_display)
    ListView lvNoteDisplay;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NoteDao noteDao;
    private Cursor cursor = null;
    private SimpleCursorAdapter mAdapter;
    private String TAG = GreenDaoActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();


        String textColumn = NoteDao.Properties.Text.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = db.query(noteDao.getTablename(), noteDao.getAllColumns(), null, null, null, null, orderBy);
        String[] from = { textColumn, NoteDao.Properties.Comment.columnName };
        int[] to = { android.R.id.text1, android.R.id.text2 };

         mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(mAdapter);

        lvNoteDisplay.setOnItemClickListener(this);
        registerForContextMenu(lvNoteDisplay);//为ListView添加上下文菜单
    }

    private void setListAdapter(SimpleCursorAdapter adapter) {
            synchronized (this) {
                mAdapter = adapter;
                lvNoteDisplay.setAdapter(adapter);
            }
        }

    /**
     * 添加日志
     */
    private void addNote() {
        String noteText = etAdd.getText().toString();
        etAdd.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        Note note = new Note(null, noteText, comment, new Date());
        noteDao.insert(note);
        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());

        cursor.requery();
    }

    /**
     * 删除Note
     * @param id
     */
    private void deleteNote(long id) {
        noteDao.deleteByKey(id);
        cursor.requery();
    }

    /**
     * 更新记录
     * @param id
     */
    private void updateNote(long  id){
        Note note = noteDao.load(id);
        note.setComment("---" + id + "--");
        noteDao.update(note);
        cursor.requery();
    }


    @OnClick({R.id.btn_add})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_add:
                 addNote();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("数据更新");
        //添加菜单项
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "更新");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.v(TAG, "context item seleted ID="+ menuInfo.id);
        long id = menuInfo.id;
        switch(item.getItemId()){
            case 0:
               deleteNote(id);
                break;
            case 1:
                updateNote(id);
                break;
        }
        return true;
    }
}
